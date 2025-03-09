using Network.Models;
using Network.Packets;
using Network.Stomp;
using System;
using System.Collections.Generic;
using System.Runtime.InteropServices;
using System.Threading.Tasks;
using UnityEngine;
using UnityEngine.SceneManagement;

namespace Game 
{
    public class NetworkControlls : MonoBehaviour, IPlatformControll, IBallControll, IBoxControll, IScoreControll, ISizeControll, IGameOverControll, IDropControll
    {
        public static NetworkControlls instance;


        [SerializeField] private GameObject canvas;

        private LevelModel level;

        private bool isLevelGot = false;
        private bool isStarted = false;

        private Dictionary<string, Action> onHitBoxCallbacks = new Dictionary<string, Action>();
        private Dictionary<string, Action<DropGameObjectUpdateModel>> onDropUpdateCallbacks = new Dictionary<string, Action<DropGameObjectUpdateModel>>();

        private StompWebSocket webSocket;
        private Action<GameObjectUpdateModel> onBallUpdatedCallback = (v) => { };
        private Action<GameObjectUpdateModel> onPlatformUpdatedCallback = (v) => { };
        private Action<GameOverModel> onGameOverCallback = (v) => { };
        private Action<int> onScoreUpdateHandler = (v) => { };
        private Action onConnectedCallback = () => { };
        private Action onLevelGotCallback = () => { };


        [DllImport("__Internal")]
        private static extern string GetLocalStorageValue(string name);


        private void Awake()
        {
            if (instance == null)
            {
                instance = this;
                DontDestroyOnLoad(gameObject);
            }
            else
            {
                Destroy(gameObject);
            }
            string ip = "127.0.0.1";
            string token = "test_token";
            int port = 5555;
            bool isSecured = false;

#if UNITY_WEBGL && !UNITY_EDITOR
            Debug.Log(GetLocalStorageValue("port"));
            ip = GetLocalStorageValue("ip");
            token = GetLocalStorageValue("token");
            port = Int32.Parse(GetLocalStorageValue("port"));
            isSecured = Convert.ToBoolean(GetLocalStorageValue("secured"));
#endif


            webSocket = new StompWebSocket(ip, port, isSecured, "play", System.Guid.NewGuid().ToString(), token);
        }

        private async void Start()
        {
            webSocket.OnConnected += () =>
            {
                webSocket.Subscribe("session/update", new SessionUpdatePacket(OnSessionUpdatedHandler));
                webSocket.Subscribe("session/gameover", new GameOverPacket(OnGameOverHandler));
                webSocket.Subscribe("box/hit", new HitBoxPacket(OnHitBoxHandler));
                webSocket.Subscribe("level", new LevelPacket(OnLevelGotHandler));

                webSocket.SendPacket(new GetLevelPacket());

                onConnectedCallback.Invoke();

                Instantiate(canvas);
            };

            await webSocket.Connect();
        }

        private void OnApplicationQuit()
        {
            Debug.Log("1");
            webSocket.SendPacket(new CloseSessionPacket());
        }

        private void Update()
        {
#if !UNITY_WEBGL || UNITY_EDITOR
            webSocket.DispatchMessageQueue();
#endif
        }

        public void SetPlatformUpdatedCallback(Action<GameObjectUpdateModel> callback)
        {
            onPlatformUpdatedCallback = callback;
        }

        public void SetBallUpdatedCallback(Action<GameObjectUpdateModel> callback)
        {
            onBallUpdatedCallback = callback;
        }

        public void SetGameOverCallback(Action<GameOverModel> callback)
        {
            onGameOverCallback = callback;
        }

        public void SetDropUpdatedCallback(Action<DropGameObjectUpdateModel> callback, string id)
        {
            if (onDropUpdateCallbacks.ContainsKey(id))
            {
                onDropUpdateCallbacks.Remove(id);
            }

            onDropUpdateCallbacks.Add(id, callback);
        }

        public void SetScoreUpdateHandler(Action<int> handler)
        {
            onScoreUpdateHandler = handler;
        }

        public void AddOnConnectedCallback(Action callback)
        {
            onConnectedCallback += callback;
        }

        public void AddOnLevelGotCallback(Action callback)
        {
            onLevelGotCallback += callback;
        }

        public void AddHitBoxCallback(string id, Action callback)
        {
            onHitBoxCallbacks.Add(id, callback);
        }

        public void StartPlatformMove(float direction)
        {
            webSocket.SendPacket(new PlatformStartMovePacket(new Vector3(direction, 0, 0)));
        }

        public void StopPlatformMove()
        {
            webSocket.SendPacket(new PlatformStopMovePacket());
        }

        public void StartBall()
        {
            webSocket.SendPacket(new BallStartPacket());
            isStarted = true;
        }

        public bool IsConnected()
        {
            return webSocket.IsConnected;
        }

        public bool IsLevelGot()
        {
            return isLevelGot;
        }



        Vector3 IPlatformControll.GetPosition()
        {
            return level.platformPosition.ToVector();
        }

        Vector3 IPlatformControll.GetScale()
        {
            return level.platformScale.ToVector();
        }

        bool IPlatformControll.CanMove()
        {
            return isStarted; 
        }

        Vector3 IBallControll.GetPosition()
        {
            return level.ballPosition.ToVector();
        }

        Vector3 IBallControll.GetScale()
        {
            float diameter = level.ballRadius * 2;

            return new Vector3(diameter, diameter, 1);
        }

        Vector3 IBoxControll.GetPosition(int index)
        {
            return level.boxesPositions[index].ToVector();
        }

        Vector3 IBoxControll.GetScale(int index)
        {
            return level.boxesScales[index].ToVector();
        }

        string IBoxControll.GetId(int index)
        {
            return level.boxesIds[index];
        }
        string IBoxControll.GetType(int index)
        {
            return level.boxesTypes[index];
        }
        string IBoxControll.GetColor(int index)
        {
            return level.boxesColors[index];
        }

        int IBoxControll.GetLength()
        {
            return level.boxesIds.Length;
        }

        float ISizeControll.GetWidth()
        {
            return level.width;
        }

        float ISizeControll.GetHeight()
        {
            return level.height;
        }

        private void OnLevelGotHandler(LevelModel level)
        {
            this.level = level;
            onLevelGotCallback.Invoke();
            isLevelGot = true;
        }

        private void OnSessionUpdatedHandler(SessionUpdateModel model)
        {
            onBallUpdatedCallback.Invoke(model.ball);
            onPlatformUpdatedCallback.Invoke(model.platform);

            if (model.drops == null)
            {
                return;
            }

            foreach(DropGameObjectUpdateModel dropModel in model.drops)
            {
                Action<DropGameObjectUpdateModel> callback;
                onDropUpdateCallbacks.TryGetValue(dropModel.id, out callback);



                if (callback == null)
                {
                    callback = Drop.Create;
                }

                callback.Invoke(dropModel);
            }
        }

        private void OnGameOverHandler(GameOverModel model)
        {
            onGameOverCallback.Invoke(model);
        }

        private void OnHitBoxHandler(HitBoxModel model)
        {
            onScoreUpdateHandler.Invoke(model.score);

            if (onHitBoxCallbacks.ContainsKey(model.id))
            {
                onHitBoxCallbacks[model.id].Invoke();
            }
        }
    }
}
