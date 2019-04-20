package exercicio03;

import java.util.ArrayList;
import java.util.List;

public class Corrida {
	
	public static void main(String[] args) {
		System.out.println(Runtime.getRuntime().availableProcessors());
		
		List<Thread> threadArray = new ArrayList<Thread>();
		
		Runnable myServer = new Lebre(20);
		
		Thread lebreUm = new Thread(myServer, "LebreUm");
		threadArray.add(lebreUm);
		Thread lebreDois = new Thread(myServer, "lebreDois");
		threadArray.add(lebreDois);
		Thread lebreTres = new Thread(myServer, "lebreTres");
		threadArray.add(lebreTres);
		Thread lebreQuatro = new Thread(myServer, "lebreQuatro");
		threadArray.add(lebreQuatro);
		Thread lebreCinco = new Thread(myServer, "lebreCinco");
		threadArray.add(lebreCinco);
		
		Runnable servidorArbitro = new Arbitro(threadArray, myServer);
		Thread apitando = new Thread(servidorArbitro, "id");
		apitando.start();
		
		lebreUm.start();	
		lebreDois.start();	
		lebreTres.start();	
		lebreQuatro.start();	
		lebreCinco.start();	
	}
	
}

