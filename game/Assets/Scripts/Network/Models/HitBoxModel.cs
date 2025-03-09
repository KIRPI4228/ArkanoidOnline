using Newtonsoft.Json;

namespace Network.Models
{
    public class HitBoxModel
    {
        [JsonProperty("id")] public string id;
        [JsonProperty("score")] public int score;
    }
}
