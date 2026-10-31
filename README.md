# 🗺️ Central Inteligente de Frotas e Contratos Logísticos - NexusLog Matrix

## 🎯 Escopo do Sistema

O sistema simula um ecossistema digital corporativo para emissão, auditoria e encerramento de contratos de locação para frotas mistas de transporte comercial (NexusLog). Rompendo com designs rígidos de dados estáticos, a aplicação implementa uma arquitetura baseada em **Herança e Polimorfismo Dinâmico** associada a **Composição de Objetos Multinível**. O operador gerencia uma coleção elástica em memória RAM capaz de unificar o ciclo de vida de ativos distintos baseados em uma superclasse abstrata de domínio (`Veiculo`). A plataforma permite registrar contratos sob demanda, discernir regras físicas específicas para **veículos de passeio (portas)** ou **transporte pesado (capacidade de carga)**, processar buscas lineares otimizadas e chavear máquinas de estado de `ATIVO` para `CONCLUÍDO` com liquidação de caixa em tempo real.

### 🔄 Linha de Processamento de Ativos
```text
[ Input Cadastral ] ➔ [ Despacho Polimórfico (Carro/Caminhão) ] ➔ [ Composição da Locação ] ➔ [ Match de Chave Única ] ➔ [ Mutação de Estado ]
```

---

## 🎨 Arquitetura do Software (Hierarquia de Classes & Polimorfismo)

O design deste ecossistema demonstra maturidade técnica ao utilizar a herança para reaproveitar estruturas comuns e o polimorfismo para tratar objetos filhos de forma genérica na camada de contratos.

```text
========================================================================================
  DIAGRAMA DE HIERARQUIA DE MEMÓRIA E COMPOSIÇÃO (POO)
========================================================================================

              ┌──────────────────────────────────┐
              │             Veiculo              │ (Superclasse Mãe)
              ├──────────────────────────────────┤
              │ - modelo: String                 │
              │ - precoDiaria: double            │
              └────────────────┬─────────────────┘
                               │
            ┌──────────────────┴──────────────────┐
            ▼                                     ▼
┌───────────────────────┐             ┌───────────────────────┐
│         Carro         │             │       Caminhao        │ (Subclasses Filhas)
├───────────────────────┤             ├───────────────────────┤
│ - portas: int         │             │ - capacidadeCarga: dbl│
└───────────────────┬───┘             └───┬───────────────────┘
                    │                             │
                    └──────────────┬──────────────┘
                                   │ (Injeção de Referência Polimórfica)
                                   ▼
 ┌────────────────────────────────────────────────────────────────────────────────────┐
 │  Objeto: locacao (Agregador Core)                                                  │
 ├────────────────────────────────────────────────────────────────────────────────────┤
 │  - idContrato: int                                                                 │
 │  - cliente: String                                                                 │
 │  - dias: int                                                                       │
 │  - status: StatusLocacao (Enum) ───► [ ATIVO | CONCLUIDO ]                         │
 │                                                                                    │
 │  - veiculo: Veiculo (Ponteiro) ────► [ Aponta para Instância de Carro OU Caminhão ]│
 └────────────────────────────────────────────────────────────────────────────────────┘
```

### 📋 Mapeamento Funcional de Componentes

| Estrutura / Arquivo | Classificação | Responsabilidade Técnica no Ecossistema |
| :--- | :--- | :--- |
| 🚀 `Main.java` | Controlador | Interface de console via terminal. Gerencia as rotinas de captura de buffers, loops iterativos do menu e algoritmos de busca defensiva por ID. |
| 📄 `locacao.java` | Entidade Core | Agregadora principal do domínio. Detém a regra de composição, manipula o ciclo de vida do contrato e executa o faturamento líquido baseado no ponteiro polimórfico. |
| 👑 `Veiculo.java` | Superclasse | Classe base de abstração da frota. Centraliza os atributos comuns de mercado (modelo e preço de diária) compartilhados por todos os ativos. |
| 🚗 `Carro.java` | Subclasse | Extensão especializada da superclasse, adicionando propriedades físicas de volumetria de passageiros (portas). |
| 🚛 `Caminhao.java` | Subclasse | Extensão especializada da superclasse, incorporando metadados de capacidade de tração logística e peso bruto (carga). |
| ⚙️ `StatusLocacao.java` | Enum | Tipo enumerado estrutural responsável pelas travas lógicas e chaveamento de estados normativos do caixa. |

---

## 🧠 Conceitos de Engenharia Praticados

* **Hierarquia por Herança (`extends`):** Reutilização de código limpo e acoplamento estruturado onde subclasses herdam atributos corporativos e acionam construtores superiores através da diretiva `super()`.
* **Polimorfismo Dinâmico por Sobrescrita:** Customização de comportamentos específicos do método `.toString()` que estendem de forma incremental a resposta textual da classe mãe sem violar o encapsulamento dos atributos privados.
* **Composição Polimórfica de Domínio:** Configuração de um ponteiro agregador genérico (`Veiculo`) capaz de referenciar dinamicamente múltiplas formas de objetos filhos em tempo de execução, reduzindo a duplicidade de métodos de faturamento.
* **Barramento de Busca com Cláusula de Escape (`break`):** Implementação de buscas sequenciais otimizadas para isolar referências de memória RAM por ID exclusivo, interrompendo o processamento do hardware assim que o match lógico é estabelecido.

---

## 💻 Demonstração Operacional (Terminal)

Veja a simulação analítica do console realizando a emissão polimórfica de um contrato logístico para veículos pesados e seu respectivo encerramento fiscal:

```text
========== MENU ==========
1 - Novo Contrato 
2 - Listar Contratos 
3 - Encerrar Contrato 
4 - Sair
1
==== Novo Contrato ====
Insira o ID do contatro: 
9050
Insira o nome do cliente: 
Alpha Logística S.A.
1 - Cadastrar Carro || 2 - Cadastrar Caminhão
2
Insira o modelo do veículo: 
Volvo FH 540
Insira o preço da diária desse veículo: 
450.00
Insira a quantidade de dias: 
10
Insira a capacidade de carga do caminhão (em toneladas): 
74.5
Contrato Cadastrado

========== MENU ==========
1 - Novo Contrato 
2 - Listar Contratos 
3 - Encerrar Contrato 
4 - Sair
3
========== Cancelar Contrato ==========
Insira o ID do contrato: 
9050
ID Contrato: 9050 || Cliente: Alpha Logística S.A. || Status: CONCLUIDO || Modelo: Volvo FH 540 || Diária: R\$450.0 || Capacidade de carga: 74.5 || Total: R\$ 4500.0
Valor a ser pago: 4500.0\$ reais
