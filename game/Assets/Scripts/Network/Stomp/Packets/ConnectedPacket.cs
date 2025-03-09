using System;
using System.Collections.Generic;
using UnityEngine;

namespace Network.Stomp.Packets
{
    public class ConnectedPacket : Packet, ReceivablePacket
    {
        private static readonly string FRAME = "CONNECTED";
        private Dictionary<string, string> headers = new Dictionary<string, string>();

        public string GetFrame()
        {
            return FRAME;
        }

        public Dictionary<string, string> GetHeaders()
        {
            return headers;
        }

        Action ReceivablePacket.GetCallback()
        {
            return OnConnectedHandler;
        }

        Packet ReceivablePacket.GetPacket()
        {
            return this;
        }

        private void OnConnectedHandler()
        {
            Debug.Log("stomp socket connected");
        }
    }
}
