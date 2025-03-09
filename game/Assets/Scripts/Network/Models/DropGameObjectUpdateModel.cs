using Newtonsoft.Json;

namespace Network.Models
{
    public class DropGameObjectUpdateModel : GameObjectUpdateModel
    {
        [JsonProperty("id")] public string id;
        [JsonProperty("type")] public string type;
    }
}
