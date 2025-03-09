using System.Collections.Generic;

namespace Network.Stomp.Packets
{
    public class DisconnectPacket : Packet, SendablePacket
    {
        private static readonly string FRAME = "DISCONNECT";
        private Dictionary<string, string> headers = new Dictionary<string, string>();

        public DisconnectPacket()
        {
            
        }


        public string GetFrame()
        {
            return FRAME;
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
