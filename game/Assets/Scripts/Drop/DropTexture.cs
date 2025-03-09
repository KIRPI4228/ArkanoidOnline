using System;
using UnityEngine;

namespace Game 
{
    [Serializable]
    public struct DropTexture
    {
        [SerializeField] public string name;
        [SerializeField] public Sprite sprite;
    }
}
