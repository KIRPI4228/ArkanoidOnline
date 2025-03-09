using System.Text;

namespace Network.Stomp.Packets
{
    public interface SendablePacket
    {
        protected Packet GetPacket();


        public string GetText()
        {
            return GetTextHeader() + "\0";
        }

        public string GetTextHeader()
        {
            var buffer = new StringBuilder();

            buffer.Append(GetPacket().GetFrame() + "\n");

            if (GetPacket().GetHeaders() != null)
            {
                foreach (var header in GetPacket().GetHeaders())
                {
                    buffer.Append(header.Key + ":" + header.Value + "\n");
                }
            }

            buffer.Append("\n");

            return buffer.ToString();
        }
    }
}
