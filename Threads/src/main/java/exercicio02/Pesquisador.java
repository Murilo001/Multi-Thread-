package exercicio02;

import java.util.concurrent.Callable;

public class Pesquisador implements Callable<Integer>  {
	private int[] parteVetor;
	private int valorPesquisado;

	Pesquisador(int[] parteVetor, int valorPesquisado) {
		this.parteVetor = parteVetor;
		this.valorPesquisado = valorPesquisado;
	}

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
