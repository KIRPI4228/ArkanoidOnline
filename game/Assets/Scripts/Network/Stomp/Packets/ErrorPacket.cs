using System;
using System.Collections.Generic;
using UnityEngine;

namespace Network.Stomp.Packets
{
    public class ErrorPacket : BodyPacket, BodyReceivablePacket
    {
        private static readonly string FRAME = "ERROR";
        private Dictionary<string, string> headers = new Dictionary<string, string>();

        private string body;

        public string GetBody()
        {
            return body;
        }

        public string GetFrame()
        {
            return FRAME;
        }

        public Dictionary<string, string> GetHeaders()
        {
            return headers;
        }

        public void SetBody(string value)
        {
            body = value;
        }

        BodyPacket BodyReceivablePacket.GetBodyPacket()
        {
            return this;
        }

        Packet ReceivablePacket.GetPacket()
        {
            return this;
        }

        Action ReceivablePacket.GetCallback()
        {
            return OnCallbackHandler;
        }


        private void OnCallbackHandler()
        {
            Debug.Log("stomp socket error - " + headers["message"]);
        }
    }
}
