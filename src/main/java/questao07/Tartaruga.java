package questao07;

import java.util.Random;

/**
 * 
 * @author Murilo
 */
public class Tartaruga extends Thread {
	private int idTartaruga;
	private double currentTime = 0;
	private double completedTime = 0;

	private int distanciaTotal = 200;
	private int steps = 0;
	private int distanciaAtual;
	private Corrida corrida;

	/**
	 * Constructor for create a runner turtle.
	 * 
	 * @param distanciaTotal int Value of entire distance of race route.
	 */
	Tartaruga(int distanciaTotal, Corrida corrida, int id) {
		this.idTartaruga = id;
		this.distanciaTotal = distanciaTotal;
		this.distanciaAtual = 0;
		this.corrida = corrida;
	}

	/**
	 * Turtle can jump one time and need to rest (wait.(0~3)), each jump walks 0, 1,
	 * 2, 3, 4 or 5 centimeters in the distance of race.
	 * 
	 * (non-Javadoc) Run method don't have javadoc.
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		try {
			currentTime = System.currentTimeMillis();
			while (distanciaAtual < distanciaTotal) {
				steps++;
				Random gerador = new Random();
				int passo = gerador.nextInt(6);
				distanciaAtual += passo;
				if (steps % 10 == 0) {
					System.out.println("A Tartaruga Número " + idTartaruga + " deu: " + steps + " passos. E andou: " + distanciaAtual + " centímetros.");
				}
				Thread.sleep(gerador.nextInt(4));
			}
			double completedTime =  System.currentTimeMillis() - currentTime;
			corrida.setTempoPassosGasto(idTartaruga, completedTime, steps);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Method to get time the turtle took to go through the route.
	 * 
	 * @return Double value of entire time of turtle.
	 */
	public double getTime() {
		return this.completedTime;
	}
	
	/**
	 * Method to get time the rabbit took to go through the route.
	 * 
	 * @return Integer value of entire steps of turtle.
	 */
	public int getSteps() {
		return this.steps;
	}
}