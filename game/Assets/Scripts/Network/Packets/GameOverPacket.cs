using Network.Models;
using Network.Stomp.Packets;
using System;

namespace Network.Packets
{
    public class GameOverPacket : MessagePacket<GameOverModel>
    {
        private Action<GameOverModel> onGameOver;

        public GameOverPacket(Action<GameOverModel> onGameOver)
        {
            this.onGameOver = onGameOver;
            SetCallback(OnGameOverSendPacketHandler);
        }

        private void OnGameOverSendPacketHandler()
        {
            onGameOver.Invoke(((JsonBodyPacket<GameOverModel>)this).GetJsonBody());
        }
    }
}
