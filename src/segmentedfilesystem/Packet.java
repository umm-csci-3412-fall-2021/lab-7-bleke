package segmentedfilesystem;

public class Packet
{
    byte[] data;
    boolean isHeader;
    byte fileID;
    int packetLength;

    // Constructor
    public Packet(byte[] data, int packetLength) {
        this.data = data;
        this.packetLength = packetLength;
    }

    public byte getFileID()
    {
        fileID = data[1];
        return fileID;
    }

    public boolean isHeader()
    {
        return isHeader;
    }
    
    public int getPacketLength()
    {
        return packetLength;
    }

    public byte[] getData()
    {
        return data;
    }
}
