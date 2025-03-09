using Newtonsoft.Json;

namespace Network.Models
{
    public class SessionUpdateModel
    {
        [JsonProperty("ball")] public GameObjectUpdateModel ball;
        [JsonProperty("platform")] public GameObjectUpdateModel platform;
        [JsonProperty("drops")] public DropGameObjectUpdateModel[] drops;
    }
}
