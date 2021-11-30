package segmentedfilesystem;

import java.util.SortedMap;
import java.util.TreeMap;

public class ReceivedFile{

    public TreeMap<Integer, Packet> files;
    public int fileID;
    private HeaderPacket headerPacket;
    public int numberOfPackets;

    public ReceivedFile(){
        this.files = new TreeMap<Integer, Packet>();
    }

    public void addPacket(HeaderPacket headerPacket) {
        this.headerPacket = headerPacket;
    }

    public void addPacket(DataPacket dataPacket) {
        this.files.put(dataPacket.packetNumber, dataPacket);
    }

    public HeaderPacket getHeaderPacket() {
        return this.headerPacket;
    }

    public boolean allPacketsReceived(){
        if(files.size() == 0) {
            return false;
        }
        return this.numberOfPackets == this.files.size();
    }
}