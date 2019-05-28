package trabalho01;

public class AppQuebraSenha {
	
	public static void main(String[] args) {
		String possibilidades = "abcdefghijklmnopqrstuvwxyz0123456789";
		char arrayPossibilidades[] = possibilidades.toCharArray();
		String senhaCriptografada1 = "17a0a00212dde12b063af7dc22fdf02b";
		String senhaCriptografada2 = "75abfe3020804dd73a2a6040da9df96c";
		String senhaCriptografada3 = "c77aeec24015ad7e6e0b1db9d9deed68";

		System.out.println("Senha:" + senhaCriptografada1);
		double tempoInicial = System.currentTimeMillis();
		System.out.println(loop(arrayPossibilidades, senhaCriptografada1));
		System.out.println("Tempo final:" + ((System.currentTimeMillis() - tempoInicial) / 1000F) + " segundos");
		
		System.out.println("Senha:" + senhaCriptografada2);
		tempoInicial = System.currentTimeMillis();
		System.out.println(loop(arrayPossibilidades, senhaCriptografada2));
		System.out.println("Tempo final:" + ((System.currentTimeMillis() - tempoInicial) / 1000F) + " segundos");
		
		System.out.println("Senha:" + senhaCriptografada3);
		tempoInicial = System.currentTimeMillis();
		System.out.println(loop(arrayPossibilidades, senhaCriptografada3));
		System.out.println("Tempo final:" + ((System.currentTimeMillis() - tempoInicial) / 1000F) + " segundos");
	}
	
	public static String loop(char[] arrayPossibilidades, String senhaCriptografada) {
		Util utilitarios = new Util();
		String senhaCriptografadaAtual = senhaCriptografada;
		String senhaDescriptogradaAtual = "";
		for (int i = 0; i < arrayPossibilidades.length; i++) {
			for (int j = 0; j < arrayPossibilidades.length; j++) {
				for (int k = 0; k < arrayPossibilidades.length; k++) {
					for (int l = 0; l < arrayPossibilidades.length; l++) {
						for (int m = 0; m < arrayPossibilidades.length; m++) {
							senhaDescriptogradaAtual = String.valueOf(arrayPossibilidades[i]) + arrayPossibilidades[j]
									+ arrayPossibilidades[k] + arrayPossibilidades[l] + arrayPossibilidades[m];
							senhaCriptografada = utilitarios.md5(senhaDescriptogradaAtual);
							// System.out.println(senhaCorrespondente);
							if (senhaCriptografada.contains(senhaCriptografadaAtual)) {
								return "A senha é: " + senhaDescriptogradaAtual;
							}
						}
					}
				}
			}
		}
		return "I don't know :/";
	}	
}
