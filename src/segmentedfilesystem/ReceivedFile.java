package segmentedfilesystem;

import java.sql.Array;
import java.util.ArrayList;
import java.util.SortedMap;

public class ReceivedFile
{
    int fileID;
    int numberOfPackets;
    ArrayList<Packet> file;

    public void ReceivedFile(int fileID)
    {
        this.fileID = fileID;
        file = new ArrayList<Packet>();
    }

    public int getFileID()
    {
        return fileID;
    }

    public void addPacket(Packet p)
    {
        file.add(p);
    }

    public void setNumberOfPackets(int numberOfPackets)
    {
        this.numberOfPackets = numberOfPackets;
    }



}
