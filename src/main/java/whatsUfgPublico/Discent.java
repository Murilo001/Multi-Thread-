package whatsUfgPublico;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Discent {
	
	private static final String IPSERVER = "192.168.40.247";
	private static final int PORTSERVER = 23456;
	private static Socket servidor;
	private static Scanner teclado;
	private static Scanner ouvirServidor;
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		servidor = new Socket(IPSERVER, PORTSERVER);
		teclado = new Scanner(System.in);
		System.out.println("Digite seu Nick Name: ");
		String user = teclado.nextLine();
		System.out.println("Digite sua mensagem: ");
		String message = teclado.nextLine();
		
		PrintStream entradaServidor = new PrintStream(servidor.getOutputStream());
		entradaServidor.println(user);
		entradaServidor.println(message);
		
		ouvirServidor = new Scanner(servidor.getInputStream());
		String resposta = ouvirServidor.nextLine();
		
		System.out.println(resposta);
	}
}
