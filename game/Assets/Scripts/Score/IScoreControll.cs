using System;

namespace Game 
{
    public interface IScoreControll : IControll
    {
        void SetScoreUpdateHandler(Action<int> handler);
    }
}
