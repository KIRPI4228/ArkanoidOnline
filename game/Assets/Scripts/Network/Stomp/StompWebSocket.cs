using NativeWebSocket;
using Network.Packets;
using Network.Stomp.Packets;
using System;
using System.Collections.Generic;
using UnityEngine;

namespace Network.Stomp
{
    public class StompWebSocket : WebSocket
    {
        public bool IsConnected { get; private set; }

        public Action OnConnected = () => { };

        private static readonly ReceivablePacket ERROR_PACKET = new ErrorPacket();
        private static readonly ReceivablePacket CONNECTED_PACKET = new ConnectedPacket();

        private Dictionary<string, SubscribePacket> subscribedPackets = new Dictionary<string, SubscribePacket>();

        private string host;
        private int port;
        private bool isSecured;
        private string endPoint;
        private string token = "Asdfasefasdf";
        private string key;

        public StompWebSocket(string host, int port, bool isSecured, string endPoint, string key, string token) : base("ws" + (isSecured ? "s" : "") + "://" + host + ":" + port + "/" + endPoint, new List<string>() { "soap", token, key})
        {
            IsConnected = false;

            this.host = host;
            this.port = port;
            this.isSecured = isSecured;
            this.endPoint = endPoint;
            this.key = key;
            this.token = token;

            //SetHeader("Token", token);
            //SetHeader("Key", key);


            OnOpen += OnOpenHandler;
            OnMessage += OnMessageHandler;
            OnError += OnErrorHandler;
            OnClose += OnCloseHandler;
        }



        public void SendPacket(SendablePacket packet)
        {
            SendText(packet.GetText());
        }

        public void SendPacket(BodySendablePacket packet)
        {
            SendText(packet.GetText());
        }

        public void Disconnect()
        {
            SendPacket(new DisconnectPacket());
        }

        public void Subscribe(string destination, BodyReceivablePacket receivablePacket)
        {
            destination = "/topic/" + key + "/" + destination;

            SubscribePacket packet = new SubscribePacket(destination, receivablePacket);

            subscribedPackets.Add(destination, packet);

            SendPacket(packet);
        }



        private void OnCloseHandler(WebSocketCloseCode closeCode)
        {
            Disconnect();
        }

        private void OnErrorHandler(string errorMsg)
        {
            Debug.Log(errorMsg);
        }

        private void OnMessageHandler(byte[] data)
        {
            var message = System.Text.Encoding.ASCII.GetString(data);

            var destination = ReceivablePacket.GetHeader("destination", message);

            if (destination != null && subscribedPackets[destination].GetPacket().Serialize(message))
            {
                subscribedPackets[destination].GetPacket().GetCallback().Invoke();
            }
            else if (CONNECTED_PACKET.Serialize(message))
            {
                CONNECTED_PACKET.GetCallback().Invoke();
                OnConnected.Invoke();
                IsConnected = true;
            }
            else if (ERROR_PACKET.Serialize(message))
            {
                ERROR_PACKET.GetCallback().Invoke();
                IsConnected = false;
            }
        }

        private void OnOpenHandler()
        {
            SendPacket(new ConnectPacket(host, "1.1"));
        }
    }
}