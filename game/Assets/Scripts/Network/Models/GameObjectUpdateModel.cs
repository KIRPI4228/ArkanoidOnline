using Newtonsoft.Json;

namespace Network.Models
{
    public class GameObjectUpdateModel
    {
        [JsonProperty("position")] public PositionModel position;
        [JsonProperty("scale")] public ScaleModel scale;
    }
}
