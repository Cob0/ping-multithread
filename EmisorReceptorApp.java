package ut3.socketsUDP.p1.hora;

import java.net.SocketException;
import java.net.UnknownHostException;

public class EmisorReceptorApp {

	private EmisorReceptor emisorReceptor;
	
	public EmisorReceptorApp() {
		try {
			this.emisorReceptor = new EmisorReceptor();
		} catch (SocketException | UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public EmisorReceptor getEmisorReceptor() {
		return emisorReceptor;
	}

	public void setEmisorReceptor(EmisorReceptor emisorReceptor) {
		this.emisorReceptor = emisorReceptor;
	}
	
	public void start() {
		this.emisorReceptor.run();
	}
	
	public static void main(String[] args) {
		EmisorReceptorApp app = new EmisorReceptorApp();
		app.start();
	}
	
}