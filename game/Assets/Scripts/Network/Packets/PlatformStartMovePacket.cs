using Network.Models;
using Network.Stomp.Packets;
using UnityEngine;

namespace Network.Packets
{
    public class PlatformStartMovePacket : SendPacket<PositionModel>
    {
        public PlatformStartMovePacket(Vector3 direction) : base(new PositionModel(direction), "/app/platform/move/start")
        {
        }
    }
}
