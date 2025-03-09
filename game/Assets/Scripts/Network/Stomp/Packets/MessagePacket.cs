using System;
using System.Collections.Generic;

namespace Network.Stomp.Packets
{
    public class MessagePacket<T> : JsonBodyPacket<T>, BodyReceivablePacket
    {
        private static readonly string FRAME = "MESSAGE";

        private Dictionary<string, string> headers = new Dictionary<string, string>();

        private Action callback;

        private string body = "";

        public MessagePacket(Action callback)
        {
            SetCallback(callback);
        }
        public MessagePacket() { }

        public string GetBody()
        {
            return body;
        }
        public void SetBody(string value)
        {
            body = value;
        }
        public void SetDestination(string destination)
        {
            ((Packet)this).SetHeader("destination", destination);
        }
        public void SetCallback(Action callback)
        {
            this.callback = callback;
        }

        public string GetFrame()
        {
            return FRAME;
        }

        public Dictionary<string, string> GetHeaders()
        {
            return headers;
        }

        BodyPacket BodyReceivablePacket.GetBodyPacket()
        {
            return this;
        }

        Packet ReceivablePacket.GetPacket()
        {
            return this;
        }

        public Action GetCallback()
        {
            return callback;
        }
    }
}
