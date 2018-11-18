package ut3.socketsUDP.p1.hora;

import java.net.SocketException;
import java.net.UnknownHostException;

public class ReceptorEmisorApp {
	
	private ReceptorEmisor receptorEmisor;
	
	public ReceptorEmisorApp() {
		try {
			this.receptorEmisor = new ReceptorEmisor();
		} catch (SocketException | UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public ReceptorEmisor getReceptorEmisor() {
		return receptorEmisor;
	}

	public void setReceptorEmisor(ReceptorEmisor receptorEmisor) {
		this.receptorEmisor = receptorEmisor;
	}
	
	public void start() {
		this.receptorEmisor.run();
	}
	
	public static void main(String[] args) {
		ReceptorEmisorApp app = new ReceptorEmisorApp();
		app.start();
	}
}

