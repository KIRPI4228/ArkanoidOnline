using Network.Models;
using System;

namespace Game 
{
    public interface IDropControll : IControll
    {
        void SetDropUpdatedCallback(Action<DropGameObjectUpdateModel> callback, string id);
    }
}
