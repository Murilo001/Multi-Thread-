package whatsUfg;

import java.io.InputStream;
import java.util.Scanner;

public class ThreadConexaoCliente implements Runnable {

    private InputStream servidor;
	private Scanner inputTeclado;

    public ThreadConexaoCliente(InputStream servidor) {
        this.servidor = servidor;
    }

    public void run() {
    	inputTeclado = new Scanner(this.servidor);
        while (inputTeclado.hasNextLine()) {
            System.out.println(inputTeclado.nextLine());
        }
    }
}