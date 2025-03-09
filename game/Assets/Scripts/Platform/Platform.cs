using Network.Models;
using UnityEngine;

namespace Game 
{
    public class Platform : MonoBehaviour
    {
        [SerializeField] private GameButton leftButton;
        [SerializeField] private GameButton rightButton;

        private bool isLeftDown = false;
        private bool isRightDown = false;
        private bool isLeftUp = false;
        private bool isRightUp = false;

        private float downAxis = 0;
        private float upAxis = 0;
        private float axis = 0;

        private IPlatformControll controll;

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
            controll.SetPlatformUpdatedCallback(OnUpdatedHandler);


            leftButton.ButtonDownkHandler += (s, o) => isLeftDown = true;
            leftButton.ButtonUpHandler += (s, o) => isLeftUp = true;
            rightButton.ButtonDownkHandler += (s, o) => isRightDown = true;
            rightButton.ButtonUpHandler += (s, o) => isRightUp = true;
        }

        private void Update()
        {
            if (!controll.CanMove())
            {
                return;
            }


            downAxis = Inputs.GetDownAxis(KeyCode.A, KeyCode.D);
            upAxis = Inputs.GetUpAxis(KeyCode.A, KeyCode.D);
            axis = Inputs.GetAxis(KeyCode.A, KeyCode.D);
            
            if (!UpdateMove(downAxis, upAxis, axis))
            {
                downAxis = Inputs.GetAxis(isLeftDown, isRightDown);
                upAxis = Inputs.GetAxis(isLeftUp, isRightUp);
                axis = Inputs.GetAxis(leftButton.IsButtonDown, rightButton.IsButtonDown);
                UpdateMove(downAxis, upAxis, axis);
            }
			
            isLeftDown = false;
            isRightDown = false;
            isLeftUp = false;
            isRightUp = false;
        }

        private bool UpdateMove(float downAxis, float upAxis, float axis)
        {
            if (downAxis != 0 || (upAxis != 0 && axis != 0))
            {
                Move(axis);
            }
            else if (upAxis != 0)
            {
                controll.StopPlatformMove();
            }
            else
            {
                return false;
            }

            return true;
        }

        private void Move(float direction)
        {
            controll.StartPlatformMove(direction);
        }

        private void OnUpdatedHandler(GameObjectUpdateModel model)
        {
            transform.localScale = model.scale.ToVector();

            transform.localPosition = model.position.ToVector();
        }
		
		private void Init() 
		{
			transform.localPosition = controll.GetPosition();
            transform.localScale = controll.GetScale();
		}
    }
}
