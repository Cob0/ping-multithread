package ut3.socketsUDP.p1.hora;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Reply implements Runnable {
	
	private DatagramSocket socket;
	private DatagramPacket packet;
	
	public Reply(DatagramSocket socket, DatagramPacket packet) {
		this.socket = socket;
		this.packet = packet;
	}

	@Override
	public void run() {
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
