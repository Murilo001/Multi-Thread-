package exercicio01;

/**
 * 1.Faça um programa em Java que identifique e escreva quantos e quais os números primos:
 *  a) entre 1.000.000 e 30.000.000
 *  b) entre 90.000.000 e 120.000.000
 *  c) marque o tempo total gasto para realizar os cálculo e escreva este tempo no final

 */
public class Primo {

	public static void main(String[] args) {
		double tempoInicial = System.currentTimeMillis();
		primoLetraA();
		primoLetraB();
		double tempoFinal = System.currentTimeMillis();
		System.out.println(tempoFinal - tempoInicial);
		
	}
	
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

	private static boolean verificaPrimo(int numero) {
	    for (int j = 2; j < numero; j++) {
	        if (numero % j == 0)
	            return false;   
	    }
	    return true;
	}
}
