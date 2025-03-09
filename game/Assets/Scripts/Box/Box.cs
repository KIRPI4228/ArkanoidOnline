using UnityEngine;
using UnityEngine.UI;

namespace Game 
{
    [RequireComponent(typeof(Image))]
    public class Box : MonoBehaviour
    {

        private static int index = 0;

        private IBoxControll controll;
        private string id;

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
        }

        private void Init()
        {
            id = controll.GetId(index);
            transform.localPosition = controll.GetPosition(index);
            transform.localScale = controll.GetScale(index);

            Color color = Color.blue;

            ColorUtility.TryParseHtmlString(controll.GetColor(index), out color);

            GetComponent<Image>().color = color;

            string type = controll.GetType(index);

            controll.AddHitBoxCallback(id, OnHit);


            if (index < controll.GetLength() - 1)
            {
                Instantiate(this, transform.parent);
            }

            ++index;
        }

        private void OnHit()
        {
            Destroy(gameObject);
        }
    }
}
