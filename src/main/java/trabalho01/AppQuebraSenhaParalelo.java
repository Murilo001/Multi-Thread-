/*package trabalho01;

public class AppQuebraSenhaParalelo {

	public static void main(String[] args) {
		String senha1 = "17a0a00212dde12b063af7dc22fdf02b";
		String senha2 = "75abfe3020804dd73a2a6040da9df96c";
		String senha3 = "c77aeec24015ad7e6e0b1db9d9deed68";
		
		quebrarSenhaParalelo(senha1);
		quebrarSenhaParalelo(senha2);
		quebrarSenhaParalelo(senha3);
	}
	
	public static void quebrarSenhaParalelo(String senhaCriptografada) {
		String possibilidades = "abcdefghijklmnopqrstuvwxyz0123456789";
		int quantidadeThreads = 4;
		int tamanhoParte = possibilidades.length() / quantidadeThreads;
		
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
		
		boolean flag = true;
		while () {
			
		}
	}
}
*/