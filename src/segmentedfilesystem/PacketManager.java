package segmentedfilesystem;

import java.net.DatagramPacket;

/**
 * Manages incoming packets.
 */
public class PacketManager
{
    Packet newPacket;

    // Constructor
    public void PacketManager(DatagramPacket packet)
    {
        this.newPacket = new Packet(packet.getData(), packet.getLength());
    }

    public void sendPacketToFile()
    {
        if(newPacket.getStatus()) // if the packet is a header packet
        {
            HeaderPacket headerPacket = new HeaderPacket(newPacket.data, newPacket.packetLength);
        }
        else
        {
            DataPacket dataPacket = new DataPacket(newPacket.data, newPacket.packetLength);
        }



    }

}
