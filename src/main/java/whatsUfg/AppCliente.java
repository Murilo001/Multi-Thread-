package whatsUfg;

import java.io.IOException;
import java.net.UnknownHostException;

public class AppCliente {
	public static void main(String[] args) throws UnknownHostException, IOException {
		new Cliente("127.0.0.1", 12345).executa();
	}
}
