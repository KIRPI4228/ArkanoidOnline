using Network.Stomp.Packets;

namespace Network.Packets
{
    public class PlatformStopMovePacket : SendPacket<bool>
    {
        public PlatformStopMovePacket() : base(true, "/app/platform/move/stop")
        {
        }
    }
}
