using System.Collections.Generic;
using System.Text;

namespace Network.Stomp.Packets
{
    public interface Packet
    {
        Dictionary<string, string> GetHeaders();

        public void SetHeader(string key, string value)
        {
            GetHeaders().Add(key, value);
        }
        
        string GetFrame();
    }
}
