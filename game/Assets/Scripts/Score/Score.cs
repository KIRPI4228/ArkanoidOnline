using UnityEngine;
using UnityEngine.UI;

namespace Game 
{
    public class Score : MonoBehaviour
    {
        public int Counter { get; private set; }

        [SerializeField] private Text text;

        private IScoreControll controll;

        private void Start()
        {
            controll = NetworkControlls.instance;

            controll.AddOnLevelGotCallback(Init);
        }

        private void Init()
        {
            controll.SetScoreUpdateHandler(OnScoreUpdate);
            text.text = 0.ToString();
        }

        private void OnScoreUpdate(int score)
        {
            Counter = score;
            text.text = Counter.ToString();
        }
    }
}
