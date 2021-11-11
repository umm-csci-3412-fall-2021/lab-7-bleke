package segmentedfilesystem;

public class HeaderPacket extends Packet
{
    int fileName;

    public void HeaderPacket(int fileID, int status, int fileName)
    {
        this.fileID = fileID;
        this.status = status;
        this.fileName = fileName;
    }

    public int getFileName()
    {
        return fileName;
    }

}
