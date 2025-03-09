using Network.Models;
using UnityEngine;
using UnityEngine.UI;

namespace Game 
{
    [RequireComponent(typeof(Image))]
    public class Drop : MonoBehaviour
    {
        public string id;

        private Image image;
        private IDropControll controll = NetworkControlls.instance;

        public static void Create(DropGameObjectUpdateModel model)
        {
            Drop drop = Instantiate(DropsTextures.instance.dropExample, DropsTextures.instance.gameParent).GetComponent<Drop>();

            drop.id = model.id;
            drop.image.sprite = DropsTextures.instance.Get(model.type);

            drop.DropUpdatedCallback(model);
        }

        private void Awake()
        {
            image = GetComponent<Image>();
        }

        private void Start()
        {
            if (id.Equals(".example"))
            {
                return;
            }

            if (!controll.IsConnected())
            {
                controll.AddOnLevelGotCallback(Init);
            }
            else
            {
                Init();
            }
        }

        private void Init()
        {
            controll.SetDropUpdatedCallback(DropUpdatedCallback, id);
        }

        private void DropUpdatedCallback(DropGameObjectUpdateModel model)
        {
            if (model.position.x == -100)
            {
                controll.SetDropUpdatedCallback((model) => { }, id);
                Destroy(gameObject);
                return;
            }

            transform.localScale = model.scale.ToVector();
            transform.localPosition = model.position.ToVector();
        }
    }
}
