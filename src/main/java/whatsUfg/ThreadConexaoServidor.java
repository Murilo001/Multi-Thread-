package whatsUfg;

import java.io.InputStream;
import java.util.Scanner;

public class ThreadConexaoServidor implements Runnable {

    private InputStream cliente;
    private Servidor servidor;

    public ThreadConexaoServidor(InputStream cliente, Servidor servidor) {
        this.cliente = cliente;
        this.servidor = servidor;
    }

    public void run() {
        // quando chegar uma msg, distribui pra todos
        Scanner inputCliente = new Scanner(this.cliente);
        while (inputCliente.hasNextLine()) {
            servidor.distribuiMensagem(inputCliente.nextLine());
        }
        inputCliente.close();
    }
}