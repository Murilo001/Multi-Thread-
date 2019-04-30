package exercicio03;

import java.util.Random;

/**
 * Class than implements a logic to incapsulate one rabbit in the race. Each
 * rabbit can jump one time and need to rest (yield.()), each jump walks 1, 2 or
 * 3 unit measure in the distance of race.
 * 
 * @author Murilo
 */
public class Lebre implements Runnable {

	/**
	 * Entire distance of race route.
	 */
	private int distanciaTotal = 0;

	/**
	 * Current distance that the rabbit is.
	 */
	private int distanciaAtual;

	/**
	 * Constructor for create a runner rabbit.
	 * 
	 * @param distanciaTotal int Value of entire distance of race route.
	 */
	Lebre(int distanciaTotal) {
		this.distanciaTotal = distanciaTotal;
		this.distanciaAtual = 0;
	}

	/**
	 * Rabbit can jump one time and need to rest (yield.()), each jump walks 1, 2 or
	 * 3 unit measure in the distance of race.
	 * 
	 * (non-Javadoc) Run method don't have javadoc.
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		while (distanciaAtual < distanciaTotal) {
			Random gerador = new Random();
			int pulo = gerador.nextInt(3) + 1;
			distanciaAtual += pulo;
			Thread.yield();
			System.out.println("O coelho deu um pulo: " + pulo);
		}
	}
	
	/**
	 * Method to get time the rabbit took to go through the route.
	 * 
	 * @return Integer value of entire time of rabbit.
	 */
	public int getTime() {
		return 1;
	}
}