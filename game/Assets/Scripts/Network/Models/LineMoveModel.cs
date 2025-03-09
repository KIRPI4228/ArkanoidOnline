using Newtonsoft.Json;

namespace Network.Models
{
    public class LineMoveModel
    {
        [JsonProperty("position")] public PositionModel position;
        [JsonProperty("direction")] public PositionModel direction;
        [JsonProperty("speed")] public float speed;
    }
}
