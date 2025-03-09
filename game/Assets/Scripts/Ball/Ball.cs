using Network.Models;
using System;
using UnityEngine;
using UnityEngine.UI;

namespace Game 
{
    public class Ball : MonoBehaviour
    {
        [SerializeField] private GameObject controlls;
        [SerializeField] private GameButton startButton;
        [SerializeField] private Text pressSpaceToStartText;

        private IBallControll controll;
        private bool isStarted = false;

        private void Start()
        {
            controll = NetworkControlls.instance;
			
			if (!controll.IsLevelGot())
            {
                controll.AddOnLevelGotCallback(Init);
            }
            else
            {
                Init();
            }
			controll.SetBallUpdatedCallback(OnUpdatedHandler);

            startButton.ButtonDownkHandler += OnStartGame;
        }

        private void OnStartGame(object sender, EventArgs e)
        {
            pressSpaceToStartText.text = "";
            startButton.gameObject.SetActive(false);
            controlls.SetActive(true);
            controll.StartBall();
            isStarted = true;
            startButton.ButtonClickHandler -= OnStartGame;
        }

        private void OnUpdatedHandler(GameObjectUpdateModel model)
        {
            float raduis = model.scale.ToVector().x;

            transform.localScale = new Vector3(raduis, raduis, 1);

            transform.localPosition = model.position.ToVector();
        }
		
		private void Init()
		{
            transform.localPosition = controll.GetPosition();
            transform.localScale = controll.GetScale();
		}
    }
}
