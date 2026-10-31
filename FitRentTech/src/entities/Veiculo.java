package entities;

public class Veiculo {

	private String modelo;
	private double precoDiaria;
	

	public Veiculo(String modelo, double precoDiaria) {
		this.modelo = modelo;
		this.precoDiaria = precoDiaria;
	}
	
	public String getModelo() {
		return modelo;
	}
	public double getPrecoDiaria() {
		return precoDiaria;
	}
	public void setPrecoDiaria(double precoDiaria) {
		this.precoDiaria = precoDiaria;
	}

	public String toString() {
		return " || Modelo: " +  modelo 
				+ " || Diária: R$" + precoDiaria;
				
	}
	

}
