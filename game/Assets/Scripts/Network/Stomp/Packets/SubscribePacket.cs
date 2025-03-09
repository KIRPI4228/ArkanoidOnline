using System.Collections.Generic;
using UnityEngine;

namespace Network.Stomp.Packets
{
    public class SubscribePacket : Packet, SendablePacket
    {
        private static readonly string FRAME = "SUBSCRIBE";

        private static int id = 0;


        private Dictionary<string, string> headers = new Dictionary<string, string>();
        private BodyReceivablePacket packet;

        public SubscribePacket(string destination, BodyReceivablePacket packet)
        {
            ((Packet)this).SetHeader("id", id++.ToString());
            SetDestination(destination);
            SetPacket(packet);
        }

        public void SetDestination(string destination)
        {
            ((Packet)this).SetHeader("destination", destination);
        }

        public void SetPacket(BodyReceivablePacket packet)
        {
            this.packet = packet;
        }

        public BodyReceivablePacket GetPacket()
        {
            return packet;
        }

        public string GetFrame()
        {
            return FRAME;
        }

        public Dictionary<string, string> GetHeaders()
        {
            return headers;
        }

        Packet SendablePacket.GetPacket()
        {
            return this;
        }
    }
}