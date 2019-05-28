package whatsUfgPublico;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;


public class DiscentConnected extends Thread {

	private Socket discent;
	private Scanner ouvirDiscente;

	public DiscentConnected(Socket discent) {
		this.discent = discent;
	}

	@Override
	public void run() {
		try {
			ouvirDiscente = new Scanner(discent.getInputStream());
			PrintStream entradaSaidaDiscente = new PrintStream(discent.getOutputStream());
			
			String nickName = null, mensagem = null;
			nickName = ouvirDiscente.nextLine();
			mensagem = ouvirDiscente.nextLine();
			
			MessageServer.messageRecieved.put(nickName, mensagem);

			entradaSaidaDiscente.println(mensagem);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
