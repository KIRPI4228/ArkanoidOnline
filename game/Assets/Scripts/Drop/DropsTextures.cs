using UnityEngine;

namespace Game 
{
    public class DropsTextures : MonoBehaviour
    {
        public static DropsTextures instance;

        public Drop dropExample;
        public Transform gameParent;

        public DropTexture[] textures;



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
        }

        public Sprite Get(string name)
        {
            foreach (DropTexture texture in textures)
            {
                if (texture.name.Equals(name))
                {
                    return texture.sprite;
                }
            }

            return null;
        }
    }
}
