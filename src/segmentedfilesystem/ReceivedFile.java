package segmentedfilesystem;

import java.util.ArrayList;
import java.util.TreeMap;

public class ReceivedFile
{
    int fileID;
    byte[] filename;
    int numberOfPackets;
    TreeMap<Integer, byte[]> file;

    public void ReceivedFile(int fileID)
    {
        this.fileID = fileID;
        file = new TreeMap<Integer, byte[]>();
    }

    public int getFileID()
    {
        return fileID;
    }

    public void addPacket(DataPacket p)
    {
        file.put(p.getPacketNumber(), p.getRestOfData());
    }

    public void setNumberOfPackets(int numberOfPackets)
    {
        this.numberOfPackets = numberOfPackets;
    }

    public boolean isComplete()
    {
        return file.size() == numberOfPackets;
    }

    public void setFileName(byte[] filename)
    {
        this.filename = filename;
    }

}
