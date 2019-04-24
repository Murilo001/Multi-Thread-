package exercicio03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TesteSharedVariable 	{
	class Control {
		public volatile boolean flag = false;
	}

	final Control control = new Control();

	class Lebre implements Callable<String> {

		private int distanciaTotal = 0;
		private int distanciaAtual;
		private String Lebre = "";

		Lebre(int distanciaTotal, String Lebre) {
			this.distanciaTotal = distanciaTotal;
			this.distanciaAtual = 0;
			this.Lebre = Lebre;
		}

		public String call() {
			String retorno = "";
			int quantidadePulos = 0;
			while ((!control.flag) && (distanciaAtual < distanciaTotal)) {
				Random gerador = new Random();
				int pulo = gerador.nextInt(3) + 1;
				quantidadePulos++;
				distanciaAtual += pulo;
				// Garante que apenas uma lebre vença.
				synchronized (this) {
					if (!control.flag && distanciaAtual >= 20) {
						retorno = "Vencedor: ";
						control.flag = true;
					}
				}
				Thread.yield();
			}
			retorno += "O Lebre " + Lebre + " deu " + quantidadePulos + " pulos. A distância percorrida foi:"
					+ distanciaAtual;
			return retorno;
		}
	}

	private void test() throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		int distanciaTotal = 20;

		Lebre threadLebre1 = new Lebre(distanciaTotal, String.valueOf("Amarelo"));
		Lebre threadLebre2 = new Lebre(distanciaTotal, String.valueOf("Azul"));
		Lebre threadLebre3 = new Lebre(distanciaTotal, String.valueOf("Verde"));
		Lebre threadLebre4 = new Lebre(distanciaTotal, String.valueOf("Vermelho"));
		Lebre threadLebre5 = new Lebre(distanciaTotal, String.valueOf("Preto"));

		List<Callable<String>> callableTasks = new ArrayList<Callable<String>>();
		callableTasks.add(threadLebre1);
		callableTasks.add(threadLebre2);
		callableTasks.add(threadLebre3);
		callableTasks.add(threadLebre4);
		callableTasks.add(threadLebre5);
		List<Future<String>> futures = executorService.invokeAll(callableTasks);
		// List<Integer> colocacao = new ArrayList<Integer>();
		HashMap<Integer, String> colocacao;
		colocacao = new HashMap<Integer, String>();

		for (Future<String> future : futures) {
			String[] split = future.get().split(":");
			String[] stringCorPulosTratado = future.get().trim().split("Lebre")[1].split("deu");
			colocacao.put(Integer.parseInt(split[split.length - 1]),
					stringCorPulosTratado[0] + "-" + stringCorPulosTratado[1].substring(1, 2));
		}

		List<Integer> sortedKeys = new ArrayList<Integer>(colocacao.keySet());
		Collections.sort(sortedKeys, Collections.reverseOrder());
		boolean vencedor = true;
		int posicao = 2;

		for (Object key : sortedKeys) {
			String[] stringCorPulos = colocacao.get(key).split("-");
			if (vencedor) {
				System.out.println("Vencedor: a Lebre " + stringCorPulos[0] + ", deu " + stringCorPulos[1] +
						" pulos.");
				vencedor = false;
			} else {
				System.out.println("A Lebre " + stringCorPulos[0] + " deu " + stringCorPulos[1]
						+ " pulos. Sua colcoação é: " + posicao);
				posicao++;
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		try {
			TesteSharedVariable test = new TesteSharedVariable();
			test.test();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}