using Newtonsoft.Json;

namespace Network.Stomp.Packets
{
    public interface JsonBodyPacket<T> : BodyPacket
    {
        public void SetJsonBody(T value)
        {
            SetType("application/json");
            SetBody(JsonConvert.SerializeObject(value));
        }

        public T GetJsonBody()
        {
            return (T)JsonConvert.DeserializeObject(GetBody(), typeof(T));
        }
    }
}
