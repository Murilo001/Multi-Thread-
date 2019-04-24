package exercicio03;

import java.util.Random;

public class Lebre implements Runnable {
	
	private volatile boolean exit = false;
	
	private int distanciaTotal = 0;
	private int distanciaAtual;
	
	Lebre(int distanciaTotal) {
	    this.distanciaTotal = distanciaTotal;
        this.distanciaAtual = 0;
    }
	
	public int getDistanciaAtual() {
		return this.distanciaAtual;
	}

    public void run() {
    	while ((!exit) && (distanciaAtual < distanciaTotal)) {		
    		Random gerador = new Random();
    		int pulo = gerador.nextInt(3) + 1;
    		
    		
    		// Objeto Conclusão
    		
    		//
    		
    		
    		distanciaAtual += pulo;
    		System.out.println("O coelho deu um pulo: " + pulo);	
    	}
    }

	public void paraLebre() {
		this.exit = true;
	}
}