package exercicio02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * Class to parallel search on array of integer. Consumer can input a array,
 * search number and number of Threads. Output is a index of array with number
 * search and otherwise -1.
 * 
 * @author Murilo
 */
public class Paralelo {

	private static int vetors[];
	private static int searchValue;
	private static int numberOfThreads;

	/**
	 * Main method is only for test purposes.
	 * 
	 * @param args unnecessary
	 * @throws InterruptedException Exception throws when thread can't execute and
	 *                              interrupt execution.
	 * @throws ExecutionException   Another exception with ExecutorService.
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		System.out.println(parallelSearch(getSearchValue(), vetors, getNumberOfThreads()));
	}

	/**
	 * Only populate private values.
	 */
	public void testFunction() {
		setVetors(new int[100000]);
		setSearchValue(12121);
		setNumberOfThreads(5);
		for (int i = 0; i < 100000; i++) {
			vetors[i] = i + 1;
		}
	}

	/**
	 * Function to parallel a search on vetor, slice in multiple parts and assign to threads. 
	 * 
	 * @param searchValue Integer to search in array.
	 * @param vetors Array to slice.
	 * @param numberOfThreads Integer specifying number of threads to slice array and initialize.
	 * @return Integer IndexOf element in array or -1 case don't find.
	 * @throws InterruptedException Throws Exception if thread stop.
	 * @throws ExecutionException Throws Exception because a executorservice.
	 */
	public static int parallelSearch(int searchValue, int[] vetors, int numberOfThreads)
			throws InterruptedException, ExecutionException {
		int tamanhoPedacoVetor = vetors.length / numberOfThreads;
		ExecutorService executorService = Executors.newCachedThreadPool();
		List<Future<Integer>> futureList = new ArrayList<Future<Integer>>();
		int[] parte = new int[tamanhoPedacoVetor];
		for (int i = 0; i < numberOfThreads; i++) {
			System.arraycopy(vetors, 0, parte, 0, parte.length);
			Pesquisador threadPesquisador = new Pesquisador(vetors, searchValue);
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

	/**
	 * Init: Getters and Setters
	 */
	public static int[] getVetors() {
		return vetors;
	}

	public static void setVetors(int[] vetors) {
		Paralelo.vetors = vetors;
	}

	public static int getNumberOfThreads() {
		return numberOfThreads;
	}

	public static void setNumberOfThreads(int numberOfThreads) {
		Paralelo.numberOfThreads = numberOfThreads;
	}

	public static int getSearchValue() {
		return searchValue;
	}

	public static void setSearchValue(int searchValue) {
		Paralelo.searchValue = searchValue;
	}

	/**
	 * End: Getters and Setters
	 */
}
