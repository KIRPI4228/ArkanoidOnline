using Newtonsoft.Json;

namespace Network.Models
{
    public class LevelModel
    {
        [JsonProperty("boxesPositions")] public PositionModel[] boxesPositions;
        [JsonProperty("boxesScales")] public ScaleModel[] boxesScales;
        [JsonProperty("boxesTypes")] public string[] boxesTypes;
        [JsonProperty("boxesColors")] public string[] boxesColors;
        [JsonProperty("boxesIds")] public string[] boxesIds;
        [JsonProperty("platformPosition")] public PositionModel platformPosition;
        [JsonProperty("ballPosition")] public PositionModel ballPosition;
        [JsonProperty("platformScale")] public ScaleModel platformScale;
        [JsonProperty("ballRadius")] public float ballRadius;
        [JsonProperty("width")] public float width;
        [JsonProperty("height")] public float height;

        public int GetBoxIndex(string id)
        {
            for (int i = 0; i < boxesIds.Length; i++)
            {
                if (boxesIds[i].Equals(id))
                {
                    return i;
                }
            }

            return -1;
        }
    }
}
