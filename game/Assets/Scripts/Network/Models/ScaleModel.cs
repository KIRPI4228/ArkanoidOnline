using Newtonsoft.Json;
using UnityEngine;

namespace Network.Models
{
    public class ScaleModel
    {
        [JsonProperty("x")] public float x;
        [JsonProperty("y")] public float y;

        public ScaleModel(Vector3 position)
        {
            x = position.x;
            y = position.y;
        }

        public Vector3 ToVector()
        {
            return new Vector3(x, y, 1);
        }
    }
}
