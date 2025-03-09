using Network.Models;
using System;
using UnityEngine;

namespace Game
{
    public interface IPlatformControll : IControll
    {
        void StartPlatformMove(float direction);
        void StopPlatformMove();


        void SetPlatformUpdatedCallback(Action<GameObjectUpdateModel> callback);

        bool CanMove();

        Vector3 GetPosition();
        Vector3 GetScale();
    }
}
