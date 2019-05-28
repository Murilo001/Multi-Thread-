package questao08;

/**
 * Matrícula e nome do aluno: 201602507 - Murilo de Oliveira Silva
 */
public class Deposito {

	public static void main(String[] args) {
		Deposito dep = new Deposito();
		Produtor p = new Produtor(dep);
		Consumidor c = new Consumidor(dep);
		p.start();
		c.start();
		System.out.println("Execução do main terminada!");
	}

	private int itens = 0;
	private final int capacidade = 10;

	public synchronized int retirar() {
		if (itens > 0) {
			itens--;
			System.out.println("Caixa retirada: Sobram " + itens + " caixas");
			notifyAll();
			return 1;
		} else {
			try {
				wait();
			} catch (InterruptedException e) {
			} finally {
			}
		}
		return 0;
	}

	public synchronized int colocar() {
		if (itens < capacidade) {
			itens++;
			System.out.println("Caixa armazenada: passaram a ser " + itens + " caixas");
			notifyAll();
			return 1;
		} else {
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("ioa");
			} finally {
			}
		}

		return 0;
	}
}

class Produtor extends Thread {
	private Deposito dep;

	Produtor(Deposito dep) {
		this.dep = dep;
	}

	public void run() {
		while (true) {
			dep.colocar();
		}

	}

}

class Consumidor extends Thread {
	private Deposito dep;

	Consumidor(Deposito dep) {
		this.dep = dep;
	}

	public void run() {
		while (true) {
			dep.retirar();
		}
	}

}
