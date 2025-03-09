using System;

namespace Network.Stomp.Packets
{
    public interface BodyPacket : Packet
    {
        string GetBody();

        void SetBody(string value);

        public void SetLength(int value)
        {
            SetHeader("content-length", value.ToString());
        }
        public void SetType(string type)
        {
            SetHeader("content-type", type);
        }
        public string GetType()
        {
            return GetHeaders()["content-type"];
        }
        public int GetLength()
        {
            return Convert.ToInt32(GetHeaders()["content-length"]);
        }
    }
}
