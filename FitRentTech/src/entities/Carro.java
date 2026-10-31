package entities;

public class Carro extends Veiculo {

	private int portas;

	public Carro(String modelo, double precoDiaria, int portas) {
		super(modelo, precoDiaria);
		this.portas = portas;
	}
	@Override
	public String toString() {
		return super.toString() + " || Portas: " + portas;
	}
	
	
}
