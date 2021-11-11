package segmentedfilesystem;

public class DataPacket extends Packet
{
    int packetNumber;
    int data;

    public void DataPacket(int fileID, int status, int packetNumber, int data)
    {
        this.fileID = fileID;
        this.status = status;
        this.packetNumber = packetNumber;
        this.data = data;
    }

    public int getPacketNumber()
    {
        return packetNumber;
    }

    public int getData()
    {
        return data;
    }

}
