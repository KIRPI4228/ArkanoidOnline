using System.Text;

namespace Network.Stomp.Packets
{
    public interface BodySendablePacket : SendablePacket
    {
        protected BodyPacket GetBodyPacket();

        public new string GetText()
        {
            var builder = new StringBuilder();

            builder.Append(GetTextHeader());
            builder.Append(GetBodyPacket().GetBody());
            builder.Append("\0");

            return builder.ToString();
        }
    }
}
