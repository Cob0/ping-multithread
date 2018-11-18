package ut3.socketsUDP.p1.hora;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class EmisorReceptor implements Runnable {
	
	private DatagramSocket socket;
	private static final String HOSTNAME = "localhost";
	private static final int PORT = 5556; // PUERTO CLIENTE
	private static final int MAX_DATAGRAM_BYTES_LENGTH = 1024;
	
	public EmisorReceptor() throws UnknownHostException, SocketException {
		this(new DatagramSocket());
	}
	
	public EmisorReceptor(DatagramSocket socket) throws UnknownHostException {
		this.socket = socket;
	}

	public DatagramSocket getSocket() {
		return socket;
	}

	public void setSocket(DatagramSocket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		boolean respondido = false;
		byte[] messageToSend = null;
		byte[] messageToReceive = null;
		InetSocketAddress address = null; // dirección de envío
		DatagramPacket packetToSend = null;
		DatagramPacket packetToReceive = null;;
		
		while(!respondido) {
			messageToSend = new String("localtime").getBytes();
			address = new InetSocketAddress(HOSTNAME, PORT-1);
			packetToSend = new DatagramPacket(messageToSend, messageToSend.length, address);
			new Thread(new Reply(this.socket, packetToSend)).start();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			messageToReceive = new byte[MAX_DATAGRAM_BYTES_LENGTH];
			packetToReceive = new DatagramPacket(messageToReceive, messageToReceive.length);
			try {
				socket.receive(packetToReceive);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(new String(messageToReceive).trim());
			respondido = true;
		}
		
	}
}
