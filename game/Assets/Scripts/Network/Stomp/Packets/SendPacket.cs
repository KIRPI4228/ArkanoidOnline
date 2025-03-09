using System.Collections.Generic;

namespace Network.Stomp.Packets
{
    public class SendPacket<T> : JsonBodyPacket<T>, BodySendablePacket
    {
        private static readonly string FRAME = "SEND";

        private Dictionary<string, string> headers = new Dictionary<string, string>();

        private string body = "";

        public SendPacket(T value, string destination)
        {
            ((JsonBodyPacket<T>)this).SetJsonBody(value);
            SetDestination(destination);
        }
        
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

        public string GetFrame()
        {
            return FRAME;
        }

        public Dictionary<string, string> GetHeaders()
        {
            return headers;
        }

        BodyPacket BodySendablePacket.GetBodyPacket()
        {
            return this;
        }

        Packet SendablePacket.GetPacket()
        {
            return this;
        }
    }
}
