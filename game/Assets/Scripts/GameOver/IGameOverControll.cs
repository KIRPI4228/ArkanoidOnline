using Network.Models;
using System;

namespace Game
{
    public interface IGameOverControll : IControll
    {
        void SetGameOverCallback(Action<GameOverModel> callback);
    }
}
