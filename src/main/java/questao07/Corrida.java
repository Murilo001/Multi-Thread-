package questao07;

public class Corrida {
	private double placar[][];
	
	Corrida(int quantidadeCompetidor) {
		this.placar = new double[quantidadeCompetidor][2];
	}
	
	public synchronized void setTempoPassosGasto(int id, double tempo, int passos) {
		placar[id - 1][0] = tempo;
		placar[id - 1][1] = passos;
	}
	
	public synchronized double[][] getPlacar() {
		return placar;
	}
	
}