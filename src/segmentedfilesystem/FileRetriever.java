package segmentedfilesystem;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;

public class FileRetriever {

	private static InetAddress serverName;
	private static int portNum;
	private static FileOutputStream output;
	DatagramPacket packet;
	DatagramSocket socket = null;
	private static byte[] buf;
	public static ArrayList<ReceivedFile> receivedFiles = new ArrayList<>();
	HeaderPacket head;
	DataPacket data;
	int completeFiles = 0;
	ReceivedFile newFile;
	boolean IDFound;


	public FileRetriever(String server, int port) {
		// Save the server and port for use in `downloadFiles()`
		//...
		try {
			serverName = InetAddress.getByName(server);
		}catch(UnknownHostException e) {
			System.out.println(e);
		}
		portNum = port;
	}

	public void downloadFiles() {

		try {
			socket = new DatagramSocket();
			// send.connect(serverName, portNum);
			// socket = new DatagramSocket(portNum);

			buf = new byte[1028];
			DatagramPacket request = new DatagramPacket(buf, buf.length, serverName, portNum);
			socket.send(request);
		} catch(SocketException e) {
			System.out.println(e);
		} catch(IOException e) {
			System.out.println(e);
		}

		//runs as long as all of the maps arent full
		do{
			//counts how many of the maps are full
			for(ReceivedFile received : receivedFiles) {
				if(received.allPacketsReceived()) completeFiles++;
			}

			try{
				packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);
			} catch(IOException e){
				System.out.println("There was an unexpected error.");
			}
			Packet incomingPacket = new Packet(packet);

			boolean isHead = incomingPacket.isHeader();

			if(isHead) {
				head = new HeaderPacket(packet);
			} else {
				data = new DataPacket(packet);
			}
			for(ReceivedFile received : receivedFiles) {
				if(received.fileID == incomingPacket.fileID) {

					if(isHead) {
						received.addPacket(head);
					} else {
						received.addPacket(data);
						if(data.packetNumber > received.numberOfPackets) {
							received.numberOfPackets = data.packetNumber;
						}
					}
					IDFound = true;
					if(data.isLastPacket() || incomingPacket.statusByte == 3){
						received.numberOfPackets = data.packetNumber;
					}
					break;
				} else {
					IDFound = false;
				}
			}
			if(!IDFound) {
				newFile = new ReceivedFile();
				receivedFiles.add(newFile);
				if(isHead) {
					newFile.addPacket(head);
				} else {
					newFile.addPacket(data);
				}
				newFile.fileID = incomingPacket.fileID;
			}

			if(receivedFiles.size() == 0) {
				newFile = new ReceivedFile();
				if(isHead) {
					newFile.addPacket(head);
				} else {
					newFile.addPacket(data);
				}
				receivedFiles.add(newFile);
				newFile.fileID = incomingPacket.fileID;
			}
		} while(completeFiles != receivedFiles.size());

		// Do all the heavy lifting here.
		// This should
		//   * Connect to the server
		//   * Download packets in some sort of loop
		//   * Handle the packets as they come in by, e.g.,
		//     handing them to some PacketManager class
		// Your loop will need to be able to ask someone
		// if you've received all the packets, and can thus
		// terminate. You might have a method like
		// PacketManager.allPacketsReceived() that you could
		// call for that, but there are a bunch of possible
		// ways.

	}

	public static void writeToFiles() throws IOException, FileNotFoundException {
		for(ReceivedFile received : receivedFiles) {
			//creates an output stream with the given file name
			output = new FileOutputStream(received.getHeaderPacket().fileName);
			//goes through each packet and writes the data
			for(int i = 0; i < received.files.size(); i++) {
				Packet currentPacket = received.files.get(i);
				output.write(currentPacket.data);
			}
		}
		output.close();
	}

}
