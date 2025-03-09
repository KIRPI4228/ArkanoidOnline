using UnityEngine;
using UnityEngine.UI;

namespace Game 
{
    [RequireComponent(typeof(RectTransform))]
    public class SizeAdapter : MonoBehaviour
    {
        [SerializeField] private CanvasScaler scaler;
        private RectTransform rectTransform;

        private ISizeControll controll;

        private void Awake()
        {
            rectTransform = GetComponent<RectTransform>();
        }

        private void Start()
        {
            controll = NetworkControlls.instance;
            controll.AddOnLevelGotCallback(Init);
        }

        private void Init()
        {
            Vector2 size = new Vector2(controll.GetWidth() - 1f, controll.GetHeight() - 1f);

            rectTransform.sizeDelta = size;
            scaler.referenceResolution = size;
        }
    }
}
