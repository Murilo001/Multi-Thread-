package exercicio03;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;

public class ThreadStopCorrida {

	private final static int TAMANHO_CIRCUITO = 20;

	public static void main(String args[]) throws InterruptedException, ExecutionException {
		System.out.println(corridaDasLebras(TAMANHO_CIRCUITO));
	}

	public static int corridaDasLebras(int distanciaTotal) throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newCachedThreadPool();

		Coelho threadLebre1 = new Coelho(distanciaTotal, String.valueOf("Amarelo"), executorService);
		Coelho threadLebre2 = new Coelho(distanciaTotal, String.valueOf("Azul"), executorService);
		Coelho threadLebre3 = new Coelho(distanciaTotal, String.valueOf("Verde"), executorService);
		Coelho threadLebre4 = new Coelho(distanciaTotal, String.valueOf("Vermelho"), executorService);
		Coelho threadLebre5 = new Coelho(distanciaTotal, String.valueOf("Preto"), executorService);

		List<Callable<String>> callableTasks = new ArrayList<Callable<String>>();
		callableTasks.add(threadLebre1);
		callableTasks.add(threadLebre2);
		callableTasks.add(threadLebre3);
		callableTasks.add(threadLebre4);
		callableTasks.add(threadLebre5);

		List<Future<String>> futures = executorService.invokeAll(callableTasks);
		int retorno = -1;
		boolean executando = true;
		while (executando) {
			for (Future<String> future : futures) {
				if (future.get().contains("venceu")) {
					executando = false;
					System.out.println("A lebre " + future.get());
				}
			}
		}
		return retorno;
	}
}

class Coelho implements Callable<String> {

	private volatile boolean exit = false;

	private int distanciaTotal = 0;
	private int distanciaAtual;
	private String lebre = "";
	private volatile ExecutorService servidorThreads;

	Coelho(int distanciaTotal, String lebre, ExecutorService servidorThreads) {
		this.distanciaTotal = distanciaTotal;
		this.distanciaAtual = 0;
		this.lebre = lebre;
		this.servidorThreads = servidorThreads;
	}

	public String call() throws Exception {
		String retorno = "";
		boolean localSituacaoCorrida = this.exit;
		while ((!exit) && (distanciaAtual < distanciaTotal)) {
			System.out.println(exit);
			Random gerador = new Random();
			int pulo = gerador.nextInt(3) + 1;
			distanciaAtual += pulo;
			synchronized (this) {
				if (distanciaAtual >= 20) {
					retorno = lebre + " venceu.";
					paraLebre();
					servidorThreads.shutdown();
				}
			}
			localSituacaoCorrida = getSituacaoCorrida();
			Thread.yield();
			System.out.println(
					"O coelho " + lebre + " deu um pulo: " + pulo + ", a distância percorrida é:" + distanciaAtual);
		}
		return retorno;
	}

	public int getDistanciaAtual() {
		return this.distanciaAtual;
	}

	public synchronized boolean getSituacaoCorrida() {
		return this.exit;
	}

	public synchronized void paraLebre() {
		this.exit = true;
	}
}
