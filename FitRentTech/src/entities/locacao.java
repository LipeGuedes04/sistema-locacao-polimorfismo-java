package entities;

public class locacao {

	private int idContrato;
	private String cliente;
	private int dias;
	private Veiculo veiculo;
	private StatusLocacao status;

	public locacao(int idContrato, String cliente, int dias, Veiculo veiculo, StatusLocacao status) {
		super();

		this.idContrato = idContrato;
		this.cliente = cliente;
		this.dias = dias;
		this.veiculo = veiculo;
		this.status = StatusLocacao.ATIVO;
	}

	public int getIdContrato() {
		return idContrato;
	}

	public String getCliente() {
		return cliente;
	}

	public int getDias() {
		return dias;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public StatusLocacao getStatus() {
		return status;
	}

	public void setStatus(StatusLocacao status) {
		this.status = status;
	}
	
	public double calcularTotal() {
		double valorTotal = dias * veiculo.getPrecoDiaria();
		return valorTotal;
	}

	public String toString() {
	    return "ID Contrato: " + idContrato 
	            + " || Cliente: " + cliente 
	            + " || Status: " + status 
	            + veiculo 
	            + " || Total: R$ " + calcularTotal();
	}
	
	
	
}
