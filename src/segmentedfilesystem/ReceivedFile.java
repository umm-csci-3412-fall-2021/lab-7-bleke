package segmentedfilesystem;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class ReceivedFile
{
    int fileID;
    String filename;
    int numberOfPackets;
    TreeMap<Integer, byte[]> file;

    public ReceivedFile(int fileID)
    {
        this.fileID = fileID;
        file = new TreeMap<Integer, byte[]>();
    }

    public int getFileID()
    {
        return fileID;
    }

    public boolean hasFilename()
    {
        if(filename != null)
            return true;
        else
            return false;
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
        return (file.size() == numberOfPackets) && hasFilename();
    }

    public void setFileName(String filename)
    {
        this.filename = filename;
    }


    public void createFile()
    {
        if(isComplete()) {
            try{
                FileOutputStream out = new FileOutputStream(filename);
                for(Map.Entry<Integer, byte[]> k : file.entrySet())
                {
                    out.write(k.getValue());
                }
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
