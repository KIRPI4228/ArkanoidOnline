using System;
using System.Collections.Generic;
using System.IO;

namespace Network.Stomp.Packets
{
    public interface ReceivablePacket
    {
        protected Packet GetPacket();

        Action GetCallback();

        bool Serialize(string text)
        {
            return SerializeHeader(text) != null;
        }

        protected string SerializeHeader(string text)
        {
            GetPacket().GetHeaders().Clear();

            var reader = new StringReader(text);

            try
            {
                var frame = reader.ReadLine();
                if (!frame.Equals(GetPacket().GetFrame()))
                {
                    return null;
                }

                var header = reader.ReadLine();
                while (!string.IsNullOrEmpty(header))
                {
                    var split = header.Split(':');
                    if (split.Length == 2)
                    {
                        GetPacket().SetHeader(split[0].Trim(), split[1].Trim());
                    }
                    header = reader.ReadLine() ?? string.Empty;
                }
            } 
            catch
            {
                return null;
            }

            

            return reader.ReadToEnd();
        }

        public static string GetFrame(string text)
        {
            var reader = new StringReader(text);

            try
            {
                return reader.ReadLine();
            }
            catch
            {
                return null;
            }
        }

        public static Dictionary<string, string> GetHeaders(string text)
        {
            var reader = new StringReader(text);

            try
            {
                reader.ReadLine();

                var header = reader.ReadLine();
                var headers = new Dictionary<string, string>();

                while (!string.IsNullOrEmpty(header))
                {
                    var split = header.Split(':');
                    if (split.Length == 2)
                    {
                        headers[split[0].Trim()] = split[1].Trim();
                    }
                    header = reader.ReadLine() ?? string.Empty;
                }

                return headers;
            }
            catch
            {
                return null;
            }
        }

        public static string GetHeader(string name, string text)
        {
            var reader = new StringReader(text);

            try
            {
                reader.ReadLine();

                var header = reader.ReadLine();

                while (!string.IsNullOrEmpty(header))
                {
                    var split = header.Split(':');
                    if (split.Length == 2 && split[0].Equals(name))
                    {
                        return split[1].Trim();
                    }
                    header = reader.ReadLine() ?? string.Empty;
                }
            }
            catch { }

            return null;
        }
    }
}
