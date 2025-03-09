using Network.Stomp.Packets;

namespace Network.Packets
{
    public class CloseSessionPacket : SendPacket<bool>
    {
        public CloseSessionPacket() : base(true, "/app/session/close")
        {
        }
    }
}
