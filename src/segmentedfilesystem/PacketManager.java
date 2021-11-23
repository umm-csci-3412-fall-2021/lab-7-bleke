package segmentedfilesystem;

import java.net.DatagramPacket;
import java.util.ArrayList;

/**
 * Manages incoming packets.
 */
public class PacketManager
{
    Packet newPacket;
    static ArrayList<ReceivedFile> receivedFiles;

    // Constructor
    public PacketManager(DatagramPacket packet)
    {
        this.newPacket = new Packet(packet.getData(), packet.getLength());
    }

    public void setStatus() {
        if (newPacket.data[0] % 2 == 0)
            newPacket.isHeader = true;
    }

    /**
     * Creates a header or data packet and then adds that packet to a ReceivedFile object.
     */
    public void createPacket()
    {
        if(newPacket.isHeader())
        {
            HeaderPacket headerPacket = new HeaderPacket(newPacket.getData(), newPacket.getPacketLength());
            setReceivedFileName(headerPacket);
        }
        else
        {
            DataPacket dataPacket = new DataPacket(newPacket.getData(), newPacket.getPacketLength());
            dataPacket.setLastPacket();
            sendPacketToFile(dataPacket);
        }
    }

    public static void setReceivedFileName(HeaderPacket p)
    {
        int fileID = p.getFileID();
        for(int i = 0; i < receivedFiles.size(); i++)
        {
            ReceivedFile file = receivedFiles.get(i);
            if(file.getFileID() == fileID)
                // check to see if there's already a RecievedFile object with a
                // given fileID, if there is, add the new packet to that file
            {
                file.setFileName(p.fileNameToString());
            }
            else
                // if there is not an existing RecievedFile object with a given fileID,
                // then create a new one.
            {
                ReceivedFile newFile = new ReceivedFile();
                newFile.setFileName(p.fileNameToString());
                receivedFiles.add(newFile);
            }
        }
    }

    public static void sendPacketToFile(DataPacket p)
    {
        int fileID = p.getFileID();
        for(int i = 0; i < receivedFiles.size(); i++)
        {
            ReceivedFile file = receivedFiles.get(i);
            if(file.fileID == fileID)
            // check to see if there's already a RecievedFile object with a
            // given fileID, if there is, add the new packet to that file
            {
                if(p.isLastPacket)
                {
                    file.setNumberOfPackets(p.getPacketNumber());
                    // if the datapacket is the last packet, we set the numberOfPackets in the ReceivedFile
                    // as it's packet number
                }
                file.addPacket(p);  // then we add the packet to the ReceivedFile
            }
            else
            // if there is not an existing RecievedFile object with a given fileID,
            // then create a new one.
            {
                ReceivedFile newFile = new ReceivedFile();
                receivedFiles.add(newFile);
            }
        }
    }

}
