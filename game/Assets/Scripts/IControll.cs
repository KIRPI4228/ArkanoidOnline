using System;

namespace Game
{
    public interface IControll
    {
        void AddOnConnectedCallback(Action callback);
        void AddOnLevelGotCallback(Action callback);

        bool IsConnected();
        bool IsLevelGot();
    }
}
