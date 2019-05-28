package whatsUfgPublico;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

public class MessageServer {
	
	public static Map<String, String> messageRecieved = new HashMap<String, String>();

	public static void main(String[] args) throws IOException {
		ServerSocket fabrica = new ServerSocket(23456);

		while (true) {

			//this.lista.add(new PrintStream(cliente.getOutputStream()));

			new DiscentConnected(fabrica.accept()).start();
		}

	}
}
