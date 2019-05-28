package whatsUfg;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {

    public static void main(String[] args) throws IOException {
        new Servidor(12345).executa();
    }

    private int porta;
    private List<PrintStream> clientes;
	private ServerSocket servidor;

    public Servidor (int porta) {
        this.porta = porta;
        this.clientes = new ArrayList<PrintStream>();
    }

    public void executa () throws IOException {
        servidor = new ServerSocket(this.porta);
        System.out.println("Porta 12345 aberta!");

        while (true) {
            Socket cliente = servidor.accept();
            System.out.println("Nova conex�o com o cliente " +     
                cliente.getInetAddress().getHostAddress()
            );

            PrintStream ps = new PrintStream(cliente.getOutputStream());
            this.clientes.add(ps);

            ThreadConexaoServidor tc = 
                    new ThreadConexaoServidor(cliente.getInputStream(), this);
            new Thread(tc).start();
        }

    }

    public void distribuiMensagem(String msg) {
        // envia msg para todo mundo
        for (PrintStream cliente : this.clientes) {
            cliente.println(msg);
        }
    }
}