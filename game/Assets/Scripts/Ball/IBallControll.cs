using Network.Models;
using System;
using UnityEngine;

namespace Game 
{
    public interface IBallControll : IControll
    {
        void StartBall();

        void SetBallUpdatedCallback(Action<GameObjectUpdateModel> callback);

        Vector3 GetPosition();
        Vector3 GetScale();
    }
}
