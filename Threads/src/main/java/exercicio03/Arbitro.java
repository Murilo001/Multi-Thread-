package exercicio03;

import java.util.ArrayList;
import java.util.List;

public class Arbitro implements Runnable {

	List<Thread> threadArray = new ArrayList<Thread>();
	Runnable servidorCorrida;

	Arbitro(List<Thread> threadArray, Runnable servidorCorrida) {
	    this.threadArray = threadArray;
	    this.servidorCorrida = servidorCorrida;
    }
	
	public void run() {
		while (true) {
			for (Thread t : threadArray) {       
				if (!t.isAlive()) {
					System.out.println("Morreu um");
					for (Thread threadAtual : threadArray) {
						try {
							//threadAtual.paraLebre();
							threadAtual.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					return;
				}
			    System.out.println("Ativo: "+t.isAlive());
			}
			
			
		}
	}
	
}
