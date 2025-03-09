using System.Runtime.InteropServices;
using UnityEngine;

namespace Game 
{
    public class ExternalEventHandler : MonoBehaviour
    {
        [DllImport("__Internal")]
        private static extern void OnStarted();
        

        private void Start()
        {
#if UNITY_WEBGL && !UNITY_EDITOR
        OnStarted();
#endif
        }
    }
}
