package exercicio02;

import java.util.concurrent.Callable;

/**
 * Thread class than implements callable and return Integer case 
 * 
 * @author Murilo
 *
 */
public class Pesquisador implements Callable<Integer>  {
	/**
	 * Part of vetor, set on class constructor.
	 */
	private int[] parteVetor;
	/**
	 * Value to search, set on class constructor.
	 */
	private int valorPesquisado;

	/**
	 * Class constructor to search in array part.
	 * 
	 * @param parteVetor part of array to search in.
	 * @param valorPesquisado search value.
	 */
	Pesquisador(int[] parteVetor, int valorPesquisado) {
		this.parteVetor = parteVetor;
		this.valorPesquisado = valorPesquisado;
	}

	/* (non-Javadoc)
	 * @see java.util.concurrent.Callable#call()
	 */
	public Integer call() throws Exception {
		
		for (int i = 0; i < parteVetor.length; i++) {
			if (parteVetor[i] == valorPesquisado) {
				System.out.println("Current Thread: "
                        + Thread.currentThread().getName());
				return i;
			}
		}
		return -1;	
	}
}
