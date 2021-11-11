package segmentedfilesystem;

public class Packet
{
    int fileID;
    int status;

    public void Packet(int fileID, int status)
    {
        this.fileID = fileID;
        this.status = status;
    }

    public int getFileID()
    {
        return this.fileID;
    }

    public int getStatus()
    {
        return this.status;
    }
}
