using Network.Models;
using Network.Stomp.Packets;
using System;

namespace Network.Packets
{
    public class HitBoxPacket : MessagePacket<HitBoxModel>
    {
        private Action<HitBoxModel> onHitBox;

        public HitBoxPacket(Action<HitBoxModel> onHitBox)
        {
            this.onHitBox = onHitBox;
            SetCallback(OnHitBoxSendPacketHandler);
        }

        private void OnHitBoxSendPacketHandler()
        {
            onHitBox.Invoke(((JsonBodyPacket<HitBoxModel>)this).GetJsonBody());
        }
    }
}
