using Network.Models;
using Network.Stomp.Packets;
using System;

namespace Network.Packets
{
    public class SessionUpdatePacket : MessagePacket<SessionUpdateModel>
    {
        private Action<SessionUpdateModel> onUpdated;

        public SessionUpdatePacket(Action<SessionUpdateModel> onUpdated)
        {
            this.onUpdated = onUpdated;
            SetCallback(OnSessionUpdateSendPacketHandler);
        }

        private void OnSessionUpdateSendPacketHandler()
        {
            onUpdated.Invoke(((JsonBodyPacket<SessionUpdateModel>)this).GetJsonBody());
        }
    }
}
