package questao07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppTartaruga {

	public static void main(String[] args) {
		int quantidadeTartaruga = 7;
		System.out.println(Runtime.getRuntime().availableProcessors());
		
		List<Thread> threadArray = new ArrayList<Thread>();
		
		Corrida corridaTartaruga = new Corrida(quantidadeTartaruga);
		
		Tartaruga tartarugaUm = new Tartaruga(200, corridaTartaruga, 1);
		threadArray.add(tartarugaUm);
		Tartaruga tartarugaDois = new Tartaruga(200, corridaTartaruga, 2);
		threadArray.add(tartarugaDois);
		Tartaruga tartarugaTres = new Tartaruga(200, corridaTartaruga, 3);
		threadArray.add(tartarugaTres);
		Tartaruga tartarugaQuatro = new Tartaruga(200, corridaTartaruga, 4);
		threadArray.add(tartarugaQuatro);
		Tartaruga tartarugaCinco = new Tartaruga(200, corridaTartaruga, 5);
		threadArray.add(tartarugaCinco);
		Tartaruga tartarugaSeis = new Tartaruga(200, corridaTartaruga, 6);
		threadArray.add(tartarugaSeis);
		Tartaruga tartarugaSete = new Tartaruga(200, corridaTartaruga, 7);
		threadArray.add(tartarugaSete);

		for (Thread thread : threadArray) {
			thread.start();
		}

		try {
			for (Thread thread : threadArray) {
				thread.join();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Double> tempos = new ArrayList<Double>();
		
		for (int i = 0; i < quantidadeTartaruga; i++) {
			tempos.add(corridaTartaruga.getPlacar()[i][0]);
		}
		 
		Collections.sort(tempos);   

		System.out.println("----------------------------------------------------------------");
		int j = 0;
		for (Double tempo : tempos) {
			j++;
			for (int i = 0; i < quantidadeTartaruga; i++) {
				if (tempo ==  corridaTartaruga.getPlacar()[i][0]) {
					System.out.println(j + " Lugar:");
					System.out.println("Competidor " + (i + 1) + " levou o tempo: " + corridaTartaruga.getPlacar()[i][0] + " e deu: " + (int)corridaTartaruga.getPlacar()[i][1] + " passos.");
				}
			}
		}
		
		
		
	}
	
	
	

}
