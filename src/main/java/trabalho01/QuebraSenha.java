package trabalho01;

public class QuebraSenha implements Runnable {
	private String senhaCriptografada;
	public String nova;
	
	public QuebraSenha(String senha) {
		this.senhaCriptografada = senha;
		this.nova = "";
	}
	
	public void run() {
		Util utilitarios = new Util();
		nova = utilitarios.md5(senhaCriptografada);
	}
	
}
