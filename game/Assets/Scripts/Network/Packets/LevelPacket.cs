using Network.Models;
using Network.Stomp.Packets;
using System;

namespace Network.Packets
{
    public class LevelPacket : MessagePacket<LevelModel>
    {
        private Action<LevelModel> callback;

        public LevelPacket(Action<LevelModel> callback)
        {
            this.callback = callback;

            SetCallback(OnLevelGot);
        }

        private void OnLevelGot()
        {
            callback.Invoke(((JsonBodyPacket<LevelModel>)this).GetJsonBody());
        }
    }
}
