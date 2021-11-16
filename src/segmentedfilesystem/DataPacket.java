package segmentedfilesystem;

import java.util.Arrays;

public class DataPacket extends Packet
{
    int packetNumber;
    boolean isLastPacket;

    public int getPacketNumber()
    {
        int x = data[2];  // most significant byte
        int y = data[3];  // least significant byte

        if(x < 0)
            x += 256;
        if(y < 0)
            y += 256;

        return (256 * x) + y;
    }

    public byte[] getData()
    {
        return Arrays.copyOfRange(data, 4, packetLength);
    }

    public boolean isLastPacket()
    {
        if(data[0] % 4 == 3)  // might have to use Byte.toUnassignedInt
        {
            isLastPacket = true;
            return true;
        }
        else
            return false;
    }

}
