package segmentedfilesystem;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class HeaderPacket extends Packet
{
    String fileNameString;

    public HeaderPacket(byte[] data, int packetLength) {
        super(data, packetLength);
    }

    byte[] fileName = Arrays.copyOfRange(data, 2, packetLength);

    public String fileNameToString()
    {
        fileNameString = new String(fileName, StandardCharsets.UTF_8);
        return fileNameString;
    }
}
