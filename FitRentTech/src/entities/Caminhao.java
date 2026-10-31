package entities;

public class Caminhao extends Veiculo{

	private double capacidadeCarga;
	
	public Caminhao(String modelo, double precoDiaria, double capacidadeCarga) {
		super(modelo, precoDiaria);
		this.capacidadeCarga = capacidadeCarga;
	}
	@Override
	public String toString() {
		return super.toString() + " || Capacidade de carga: " + capacidadeCarga;
	}
	
}
	