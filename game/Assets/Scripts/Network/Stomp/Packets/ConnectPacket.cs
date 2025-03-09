using System.Collections.Generic;

namespace Network.Stomp.Packets
{
    public class ConnectPacket : Packet, SendablePacket
    {
        private static readonly string FRAME = "CONNECT";
       
        private Dictionary<string, string> headers = new Dictionary<string, string>();

        public ConnectPacket(string host, string version)
        {
            SetHost(host);
            SetVersion(version);
        }


        public string GetFrame()
        {
            return FRAME;
        }

        public void SetHost(string host)
        {
            ((Packet)this).SetHeader("host", host);
        }

        public void SetVersion(string version)
        {
            ((Packet)this).SetHeader("accept-version", version);
        }

        public void SetKey(string key)
        {
            ((Packet)this).SetHeader("login", key);
        }

        public void SetToken(string token)
        {
            ((Packet)this).SetHeader("passcode", token);
        }

        public Dictionary<string, string> GetHeaders()
        {
            return headers;
        }

        Packet SendablePacket.GetPacket()
        {
            return this;
        }
    }
}
