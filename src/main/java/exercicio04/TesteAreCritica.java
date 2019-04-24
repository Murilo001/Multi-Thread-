package exercicio04;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TesteAreCritica {

	class Mailbox {
		private String message = null;

		public void storeMessage(String newMessage) throws InterruptedException {

			if (this.message == null) {
				this.message = newMessage;
				System.out.println("Store:" + this.message);
				notifyAll();
				wait();
			} else {
				wait();
				storeMessage(newMessage);
			}
		}

		public String retrieveMessage() throws InterruptedException {
			System.out.println("Retrive:" + this.message);
			if (this.message != null) {
				String grabMessage = this.message;
				this.message = null;
				notifyAll();
				return grabMessage;
			} else {
				wait();
				return retrieveMessage();
			}
		}

	}

	final Mailbox email = new Mailbox();

	class Producer implements Runnable {
		private int identificador;

		Producer(int id) {
			this.identificador = id;
		}

		public void run() {
			while (true) {
				System.out.println("Sou um produtor");
				try {
					email.storeMessage("Mensagem " + this.identificador);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	class Consumer implements Runnable {
		private int identificador;

		Consumer(int id) {
			this.identificador = id;
		}

		public void run() {
			while (true) {
				System.out.println("Sou um consumidor " + this.identificador );
				try {
					System.out.println(email.retrieveMessage() + this.identificador);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		TesteAreCritica area = new TesteAreCritica();

		Consumer threadConsumidor1 = area.new Consumer(1);
		Consumer threadConsumidor2 = area.new Consumer(2);
		Consumer threadConsumidor3 = area.new Consumer(3);
		
		Producer threadProdutor1 = area.new Producer(12);
		Producer threadProdutor2 = area.new Producer(13);
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(threadProdutor1);
		executorService.execute(threadProdutor2);
		executorService.execute(threadConsumidor1);
		executorService.execute(threadConsumidor2);
		executorService.execute(threadConsumidor3);
		
	}
}
