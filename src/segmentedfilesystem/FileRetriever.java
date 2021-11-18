package segmentedfilesystem;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

public class FileRetriever {

	String server;
	int portNumber;

	public FileRetriever(String server, int port) {
        // Save the server and port for use in `downloadFiles()`
        //...
		this.server = server;
		this.portNumber = port;
	}

	public void downloadFiles() {
		int i;
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
		try {
			InetAddress address = InetAddress.getByName(server);
			DatagramSocket datagramSocket = new DatagramSocket(portNumber, address);

			byte[] buf = new byte[1028];

			// create and send empty packet to initiate exchange
			DatagramPacket packet = new DatagramPacket(buf, buf.length, address, portNumber);
			datagramSocket.send(packet);

			packet = new DatagramPacket(buf, buf.length);

			while(true)  // need to change to stop when we receive all of the packets.
			{
				datagramSocket.receive(packet);

				PacketManager newPacket = new PacketManager(packet);

				newPacket.setStatus();
				newPacket.createPacket();
			}

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



}
