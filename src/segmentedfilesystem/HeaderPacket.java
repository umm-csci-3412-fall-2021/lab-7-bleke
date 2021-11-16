package segmentedfilesystem;

import java.util.Arrays;

public class HeaderPacket extends Packet
{

    public HeaderPacket(byte[] data, int packetLength) {
        super(data, packetLength);
    }

    byte[] fileName = Arrays.copyOfRange(data, 2, packetLength);

    public byte[] getFileName()
    {
        return fileName;
    }
}
