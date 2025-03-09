namespace Network.Stomp.Packets
{
    public interface BodyReceivablePacket : ReceivablePacket
    {
        protected BodyPacket GetBodyPacket();

        new bool Serialize(string text)
        {
            GetBodyPacket().SetBody("");

            string body = SerializeHeader(text);
            if (body == null)
            {
                return false;
            }

            GetBodyPacket().SetBody(body.TrimEnd('\r', '\n', '\0'));

            return true;
        }
    }
}
