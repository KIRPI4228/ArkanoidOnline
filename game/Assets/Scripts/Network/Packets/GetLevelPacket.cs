using Network.Stomp.Packets;

namespace Network.Packets
{
    public class GetLevelPacket : SendPacket<bool>
    {
        public GetLevelPacket() : base(true, "/app/level/get")
        {
        }
    }
}
