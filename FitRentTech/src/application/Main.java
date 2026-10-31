package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Caminhao;
import entities.Carro;
import entities.StatusLocacao;
import entities.Veiculo;
import entities.locacao;

public class Main {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<locacao> contratos = new ArrayList<>();

		int menu = 0;
		while (menu != 4) {
			System.out.println("========== MENU ==========");
			System.out.println("1 - Novo Contrato \n2 - Listar Contratos \n3 - Encerrar Contrato \n4 - Sair");
			menu = sc.nextInt();
			sc.nextLine();

			switch (menu) {
			case 1:
				System.out.println("==== Novo Contrato ====");

				System.out.println("Insira o ID do contatro: ");
				int Id = sc.nextInt();
				sc.nextLine();
				System.out.println("Insira o nome do cliente: ");
				String nome = sc.nextLine();
				System.out.println("1 - Cadastrar Carro || 2 - Cadastrar Caminhão");
				int tipo = sc.nextInt();
				sc.nextLine();

				System.out.println("Insira o modelo do veículo: ");
				String modelo = sc.nextLine();
				System.out.println("Insira o preço da diária desse veículo: ");
				double diaria = sc.nextDouble();
				sc.nextLine();
				System.out.println("Insira a quantidade de dias: ");
				int dias = sc.nextInt();
				sc.nextLine();

				Veiculo veiculoEscolhido = null;
				if (tipo == 1) {
					System.out.println("Insira a quantidade de portas do carro: ");
					int QuantidadePortas = sc.nextInt();
					sc.nextLine();

					veiculoEscolhido = new Carro(modelo, diaria, QuantidadePortas);
				} else {
					System.out.println("Insira a capacidade de carga do caminhão (em toneladas): ");
					double CapacidadeCarga = sc.nextDouble();
					sc.nextLine();

					veiculoEscolhido = new Caminhao(modelo, diaria, CapacidadeCarga);
				}

				locacao locacaoGeral = new locacao(Id, nome, dias, veiculoEscolhido, StatusLocacao.ATIVO);

				contratos.add(locacaoGeral);
				System.out.println("Contrato Cadastrado");

				break;
			case 2:
				System.out.println("========== Listar Contratos ==========");
				if (contratos.isEmpty()) {
					System.out.println("Nenhum contrato cadastrado");
				} else {
					for (locacao c : contratos) {
						System.out.println(c);
					}
				}
				break;
			case 3:
				System.out.println("========== Cancelar Contrato ==========");
				if (contratos.isEmpty()) {
					System.out.println("Nenhum contrato cadastrado");
				} else {
					System.out.println("Insira o ID do contrato: ");
					int buscarID = sc.nextInt();
					sc.nextLine();

					locacao IdEncontrado = null;
					for (locacao c : contratos) {
						if (c.getIdContrato() == buscarID) {
							IdEncontrado = c;
							break;
						}
					}

					if (IdEncontrado == null) {
						System.out.println("ID não encontrado");
					} else {
						IdEncontrado.setStatus(StatusLocacao.CONCLUIDO);
						System.out.println(IdEncontrado);
						System.err.println("Valor a ser pago: " + IdEncontrado.calcularTotal() + "$ reais");
					}

				}
				break;
			case 4:
				System.out.println("Programa Finalizado ");
				break;
			}
		}

		sc.close();

	}

}
