using Network.Models;
using UnityEngine;

namespace Game 
{
    public class GameOver : MonoBehaviour
    {
        [SerializeField] private GameObject gameOverGameObject;

        private IGameOverControll controll;

        private void Start ()
        {
            gameOverGameObject.SetActive(false);

            controll = NetworkControlls.instance;

            controll.AddOnLevelGotCallback(Init);
        }

        private void Init()
        {
            controll.SetGameOverCallback(OnGameOverCallback);
        }

        private void OnGameOverCallback(GameOverModel model)
        {
            gameOverGameObject.SetActive(true);
        }
    }
}
