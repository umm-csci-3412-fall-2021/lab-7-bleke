package segmentedfilesystem;

import java.util.Arrays;

public class DataPacket extends Packet
{
    int packetNumber;
    boolean isLastPacket;
    byte[] restOfData;  // bytes that are not the status, fileID, or packetNumber

    public DataPacket(byte[] data, int packetLength) {
        super(data, packetLength);
    }

    public void calculatePacketNumber()
    {
        int x = data[2];  // most significant byte
        int y = data[3];  // least significant byte

        if(x < 0)
            x += 256;
        if(y < 0)
            y += 256;

        packetNumber = (256 * x) + y;
    }

    public int getPacketNumber()
    {
        return packetNumber;
    }

    public void setRestOfData()
    {
        restOfData = Arrays.copyOfRange(data, 4, packetLength);
    }

    public byte[] getRestOfData() { return restOfData;}

    public void setLastPacket()
    {
        if(data[0] % 4 == 3)  // might have to use Byte.toUnassignedInt
            isLastPacket = true;
    }

}
