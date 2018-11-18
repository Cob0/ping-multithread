package ut3.socketsUDP.p1.hora;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ReceptorEmisor implements Runnable {

	private DatagramSocket socket;
	private static final String HOSTNAME = "localhost";
	private static final int PORT = 5555;
	private static final int MAX_DATAGRAM_BYTES_LENGTH = 1024;
	private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
	
	public ReceptorEmisor() throws SocketException, UnknownHostException {
		this(new DatagramSocket(new InetSocketAddress(HOSTNAME, PORT)));
	}
	
	public ReceptorEmisor(DatagramSocket socket) {
		this.socket = socket;
	}

	public DatagramSocket getSocket() {
		return socket;
	}

	public void setSocket(DatagramSocket socket) {
		this.socket = socket;
	}
	
	public String getDateServer() {
		return new SimpleDateFormat(DATE_FORMAT).format(new Date());
	}

	@Override
	public void run() {
		byte[] messageToReceive = null;
		byte[] messageToSend = null;
		InetSocketAddress address = null;
		DatagramPacket packetToReceive = null;
		DatagramPacket packetToSend = null;
		
		while(true) {
			messageToReceive = new byte[MAX_DATAGRAM_BYTES_LENGTH];
			packetToReceive = new DatagramPacket(messageToReceive, messageToReceive.length);
			try {
				socket.receive(packetToReceive);
				System.out.println("Mensaje desde cliente: " + new String(messageToReceive));
				if(new String(messageToReceive).trim().equalsIgnoreCase("localtime")) {
					messageToSend = getDateServer().getBytes();
					address = new InetSocketAddress(packetToReceive.getAddress(), packetToReceive.getPort());
					packetToSend = new DatagramPacket(messageToSend, messageToSend.length, address);
					new Thread(new Reply(socket, packetToSend)).start();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
