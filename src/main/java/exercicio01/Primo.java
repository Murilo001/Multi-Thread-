package exercicio01;

/**
 * Simple program to calculate two prime numbers.
 * 
 * @author Murilo
 *
 */
public class Primo {

	public static void main(String[] args) {
		double tempoInicial = System.currentTimeMillis();
		primoLetraA();
		primoLetraB();
		double tempoFinal = System.currentTimeMillis();
		System.out.println(tempoFinal - tempoInicial);
		
	}
	
	/**
	 * First method to calculate prime, calcule primes between 1000000 and 3000000.
	 */
	private static void primoLetraA() {
		int min = 1000000;
		int max = 3000000;
		int quantidadePrimos = 0;			
		for (int i = min + 1; i < max; i++) {
			if (verificaPrimo(i)) {
				quantidadePrimos++;
				
				System.out.println(i);
			}
		}
		System.out.println("Quantidade de números primos:" + quantidadePrimos);
	}
	
	/**
	 * Second method to calculate prime, calcule primes between 9000000 and 1200000.
	 */
	private static void primoLetraB() {
		int min = 9000000;
		int max = 1200000;
		int quantidadePrimos = 0;			
		for (int i = min + 1; i < max; i++) {
			if (verificaPrimo(i)) {
				quantidadePrimos++;
				System.out.println(i);
			}
		}
		System.out.println("Quantidade de números primos:" + quantidadePrimos);
	}

	/**
	 * Function auxiliar to check if current number is prime.
	 * @param int number to check.
	 * @return boolean if number is prime or not.
	 */
	private static boolean verificaPrimo(int numero) {
	    for (int j = 2; j < numero; j++) {
	        if (numero % j == 0)
	            return false;   
	    }
	    return true;
	}
}
