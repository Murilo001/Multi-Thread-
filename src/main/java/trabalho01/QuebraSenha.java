package trabalho01;

public class QuebraSenha implements Runnable {
	private String senhaCriptografada;
	private char[] arrayPossibilidades; 
	private int intervaloInicial;
	private int intervaloFinal;
	volatile static boolean encontrou = false;
	volatile static double tempoFinal = 0;
	volatile static String senhaDescriptografada = ""; 
	
	public QuebraSenha(String senhaCriptografada, char[] arrayPossibilidades, int intervaloInicial, int intervaloFinal) {
		this.intervaloInicial = intervaloInicial;
		this.intervaloFinal = intervaloFinal;
		this.senhaCriptografada = senhaCriptografada;
		this.arrayPossibilidades = arrayPossibilidades;
	}
		
	public void run() {
		Util utilitarios = new Util();
		String senhaCriptografadaAtual = "";
		String senhaDescriptografadaAtual = "";
		for (int i = intervaloInicial; i < intervaloFinal; i++) {
			for (int j = 0; j < arrayPossibilidades.length; j++) {
				for (int k = 0; k < arrayPossibilidades.length; k++) {
					for (int l = 0; l < arrayPossibilidades.length; l++) {
						for (int m = 0; m < arrayPossibilidades.length; m++) {
							senhaDescriptografadaAtual = String.valueOf(arrayPossibilidades[i]) + arrayPossibilidades[j]
									+ arrayPossibilidades[k] + arrayPossibilidades[l] + arrayPossibilidades[m];
							senhaCriptografadaAtual = utilitarios.md5(senhaDescriptografadaAtual);
							if (senhaCriptografadaAtual.contains(senhaCriptografada)) {
								senhaDescriptografada = senhaDescriptografadaAtual;
								tempoFinal = System.currentTimeMillis();
								encontrou = true;
							}
						}
					}
				}
			}
		}
	}
	

	public static void main(String[] args) {
		String senhaCriptografada1 = "17a0a00212dde12b063af7dc22fdf02b";
		String senhaCriptografada2 = "75abfe3020804dd73a2a6040da9df96c";
		String senhaCriptografada3 = "c77aeec24015ad7e6e0b1db9d9deed68";
		
		quebrarSenhaParalelo(senhaCriptografada1);
		quebrarSenhaParalelo(senhaCriptografada2);
		quebrarSenhaParalelo(senhaCriptografada3);
	}
	
	public static void quebrarSenhaParalelo(String senhaCriptografada) {
		String possibilidades = "abcdefghijklmnopqrstuvwxyz0123456789";
		int quantidadeThreads = 4;
		int tamanhoParte = possibilidades.length() / quantidadeThreads;
		
		long tempoInicial = System.currentTimeMillis();
		
		Runnable runnableQuebrador = new QuebraSenha(senhaCriptografada, possibilidades.toCharArray(), 0, tamanhoParte);
		Thread quebrador1 = new Thread(runnableQuebrador, "thread1"); 
        
		Runnable runnableQuebrador2 = new QuebraSenha(senhaCriptografada, possibilidades.toCharArray(), tamanhoParte, tamanhoParte * 2);
		Thread quebrador2 = new Thread(runnableQuebrador2, "thread2"); 
        
		Runnable runnableQuebrador3 = new QuebraSenha(senhaCriptografada, possibilidades.toCharArray(), tamanhoParte * 2, tamanhoParte * 3);
		Thread quebrador3 = new Thread(runnableQuebrador3, "thread3"); 
       
		Runnable runnableQuebrador4 = new QuebraSenha(senhaCriptografada, possibilidades.toCharArray(), tamanhoParte * 3, tamanhoParte * 4);
		Thread quebrador4 = new Thread(runnableQuebrador4, "thread4"); 
         
		System.out.println("Senha Criptografada: " + senhaCriptografada);
		quebrador1.start();
		quebrador2.start();
		quebrador3.start();
		quebrador4.start();
		
		while (!encontrou) {	
		}
		System.out.println("A senha é: " + senhaDescriptografada);
		System.out.println("Tempo final: " + ((tempoFinal - tempoInicial) / 1000));
		encontrou = false;
		
	}

	
}
