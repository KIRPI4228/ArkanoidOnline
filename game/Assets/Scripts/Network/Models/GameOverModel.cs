using Newtonsoft.Json;
using System;

namespace Network.Models
{
    public class GameOverModel
    {
        [JsonProperty("state")] public bool state;
    }
}
