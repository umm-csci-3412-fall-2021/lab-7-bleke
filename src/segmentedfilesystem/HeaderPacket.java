package segmentedfilesystem;

public class HeaderPacket extends Packet
{
    byte[] fileName;

    public void HeaderPacket(byte fileID, byte[] fileName)
    {
        this.fileID = fileID;
        this.fileName = fileName;
    }

    public byte[] getFileName()
    {
        return fileName;
    }

}
