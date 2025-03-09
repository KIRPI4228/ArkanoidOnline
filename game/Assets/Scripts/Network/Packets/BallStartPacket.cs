using Network.Stomp.Packets;

namespace Network.Packets
{
    public class BallStartPacket : SendPacket<bool>
    {
        public BallStartPacket() : base(true, "/app/ball/start")
        {
        }
    }
}
