using Network.Models;
using Network.Stomp.Packets;
using System;

namespace Network.Packets
{
    public class GameObjectUpdatePacket : MessagePacket<GameObjectUpdateModel>
    {
        private Action<GameObjectUpdateModel> onUpdated;

        public GameObjectUpdatePacket(Action<GameObjectUpdateModel> onUpdated)
        {
            this.onUpdated = onUpdated;
            SetCallback(OnGameObjectUpdateSendPacketHandler);
        }

        private void OnGameObjectUpdateSendPacketHandler()
        {
            onUpdated.Invoke(((JsonBodyPacket<GameObjectUpdateModel>)this).GetJsonBody());
        }
    }
}
