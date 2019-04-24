package exercicio02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 2.Escreva uma classe que permita paralelizar uma pesquisa em um array de
 * inteiros. Isso deve ser feito com o seguinte método: public static int
 * parallelSearch(int x, int[] A, int numThreads). a)Este método cria tantas
 * threads quanto especificadas em numThreads, divide o array A em muitas partes
 * e dá a cada thread parte do array para procurar sequencialmente pelo valor x.
 * b)Se uma thread encontrar o valor x, então é retornado o índice i (A[i]=x),
 * ao contrário -1.
 */
public class Paralelo {

	private static int vetors[] = new int[100000];

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// Inicio Preenchendo vetor
		// Desconsidere essa parte caso queira usar outros valores.
		// setando número procurado e número de threads.
		int valorProcurado = 12121;
		int numThreads = 5;
		for (int i = 0; i < 100000; i++) {
			vetors[i] = i + 1;
		}
		// Fim
		
		System.out.println(parallelSearch(valorProcurado, vetors, numThreads));
		
	}
	
	public static int parallelSearch(int valorProcurado, int[] vetorInteiros, int numThreads) throws InterruptedException, ExecutionException {
		int tamanhoPedacoVetor = vetorInteiros.length / numThreads;
		ExecutorService executorService = Executors.newCachedThreadPool();
		List<Future<Integer>> futureList = new ArrayList<Future<Integer>>();
		int[] parte = new int[tamanhoPedacoVetor];
		
		for (int i = 0; i < numThreads; i++) {
			System.arraycopy(vetorInteiros, 0, parte, 0, parte.length);
			Pesquisador threadPesquisador = new Pesquisador(vetorInteiros, valorProcurado);
			futureList.add(executorService.submit(threadPesquisador));	
		}
		
		int retorno = -1;
		for (Future<Integer> future : futureList) {
			if (future.get().intValue() != -1) {
				retorno = future.get().intValue();
				break;
			}
		}
	    executorService.shutdown();
		return retorno;
	}
}
