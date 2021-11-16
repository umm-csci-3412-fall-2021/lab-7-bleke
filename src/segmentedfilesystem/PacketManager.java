package segmentedfilesystem;

import java.net.DatagramPacket;

/**
 * Manages incoming packets.
 */
public class PacketManager
{
    byte[] data;
    int packetLength;

    // Constructor
    public void PacketManager(DatagramPacket packet)
    {
        this.data = packet.getData();
        this.packetLength = packet.getLength();
    }

    // Checks to see if a packet is a header or not
    public boolean isHeader()
    {
        if ((data[0] % 2) == 1)
            return false;
        else
            return true;
    }

    public int getPacketLength()
    {
        return packetLength;
    }

}
