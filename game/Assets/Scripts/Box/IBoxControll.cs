using System;
using UnityEngine;

namespace Game 
{
    public interface IBoxControll : IControll
    {
        void AddHitBoxCallback(string id, Action callback);

        Vector3 GetPosition(int index);
        Vector3 GetScale(int index);
        string GetId(int index);
        string GetType(int index);
        string GetColor(int index);
        int GetLength();
    }
}
