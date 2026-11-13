# Lista de Tarefas 04 — Programação Orientada a Objetos em Java

Repositório com as atividades práticas da disciplina de programação em **Java**, organizadas em cinco partes. Cada parte aborda um conjunto de conceitos, do básico de classes e objetos até interfaces gráficas e programação concorrente.

\---

## Sumário

* [Estrutura do projeto](#estrutura-do-projeto)
* [Conteúdo de cada parte](#conteúdo-de-cada-parte)
* [Tecnologias e conceitos](#tecnologias-e-conceitos)
* [Como executar](#como-executar)
* [Capturas de tela](#capturas-de-tela)

\---

## Estrutura do projeto

```
Lista-de-tarefas-04/
├── Parte01/   # Classes, objetos e entrada de dados
├── Parte02/   # Enums
├── Parte03/   # Tratamento de exceções
├── Parte04/   # Threads e sincronização
└── Parte05/   # Interface gráfica (Swing) e sistema de pedidos
```

Cada pasta corresponde a um *package* Java com o mesmo nome.

\---

## Conteúdo de cada parte

### Parte 01 — Classes e objetos

|Arquivo|Descrição|
|-|-|
|`CadastroAluno`|Lê nome, idade, altura, situação da matrícula, nota final e curso pelo console, exibe os dados e informa se o aluno está **aprovado** (nota ≥ 6) ou **reprovado**.|
|`Produto`|Classe com atributos privados (código, descrição, preço, estoque e categoria), construtor, *getters* e métodos para calcular o valor total em estoque e exibir os dados.|
|`Principal`|Cria dois produtos e exibe suas informações.|

### Parte 02 — Enums

|Arquivo|Descrição|
|-|-|
|`DiaSemana`|Enum com os dias da semana.|
|`CategoriaProduto`|Enum de categorias, usado na classe `Produto`.|
|`StatusPedido`|Ciclo de vida de um pedido: `PENDENTE`, `APROVADO`, `EM\_PREPARACAO`, `ENVIADO`, `ENTREGUE`.|
|`NivelAcesso`|Enum com atributo e *getter* que descreve as permissões de cada nível (administrador, gerente e usuário).|
|`Prioridade`|Prioridades de um chamado, usadas em `Chamado` com `switch` para gerar mensagens diferentes.|
|`Principal`|Executa os cinco exercícios em sequência.|

### Parte 03 — Tratamento de exceções

|Arquivo|Descrição|
|-|-|
|`Calculadora`|Calculadora de console com `try / catch / finally`, tratando entrada inválida, divisão por zero e operação desconhecida.|
|`ContaBancaria`|Saque com validação de valor e lançamento de `SaldoInsuficienteException`.|
|`Usuario`|Cadastro com validação de nome, idade e CPF, lançando exceções personalizadas.|
|`SistemaNotas`|Lê uma quantidade de notas, valida o intervalo de 0 a 10 com `NotaInvalidaException` e calcula a média.|
|`\*Exception`|Exceções personalizadas: `SaldoInsuficiente`, `NomeInvalido`, `IdadeInvalida`, `CpfInvalido` e `NotaInvalida`.|
|`Principal` / `PrincipalConta`|Classes de teste de conta bancária e de cadastro de usuário.|

### Parte 04 — Threads e sincronização

|Arquivo|Descrição|
|-|-|
|`ThreadNumeros` / `ThreadLetras`|Threads criadas com `extends Thread`, executadas em paralelo por `PrincipalThread`.|
|`Tarefa`|Tarefa criada com `implements Runnable`, executada por três threads em `PrincipalTarefa`.|
|`ProcessadorPedido`|Simula o processamento de pedidos com tempos aleatórios, usando `PrincipalPedido`.|
|`ContaBancaria`|Saque `synchronized`, evitando que duas threads debitem o mesmo saldo ao mesmo tempo (`PrincipalConta`).|
|`Contador`|Compara um contador **sem** sincronização com outro **com** `synchronized`, mostrando o problema de condição de corrida.|

### Parte 05 — Interface gráfica e sistema de pedidos

|Arquivo|Descrição|
|-|-|
|`TelaCadastroAluno`|Tela Swing de cadastro de alunos, com campo de texto, lista de cursos e área de resultado.|
|`CalculadoraGrafica`|Calculadora com as quatro operações e mensagens de erro com `JOptionPane`.|
|`ControleEstoque`|Cadastro de produtos em uma tabela, com consulta de preço/quantidade e cálculo do valor total do estoque.|
|`TelaPrincipal`|Sistema de gerenciamento de pedidos com abas de **Clientes**, **Produtos** e **Pedidos**.|
|`SistemaPedidos`|Regras de negócio do sistema; os pedidos pendentes são processados em paralelo com um *pool* de 3 threads (`ExecutorService`).|
|`Cliente`, `Produto`, `ProdutoPedido`, `ItemPedido`, `Pedido`, `StatusPedido`|Classes de domínio, com validações que lançam `DadosInvalidosException`.|
|`ProcessadorPedido`|`Runnable` que faz o pedido avançar de `PENDENTE` até `ENTREGUE`, atualizando a tela.|

\---

## Tecnologias e conceitos

* **Linguagem:** Java
* **IDE:** Eclipse
* **Interface gráfica:** Java Swing (`JFrame`, `JTable`, `JTabbedPane`, `JOptionPane`)
* **Conceitos abordados:**

  * Classes, objetos, encapsulamento e construtores
  * Enumerações (`enum`) com atributos e métodos
  * Exceções verificadas, exceções personalizadas e `try / catch / finally`
  * Threads (`Thread`, `Runnable`), `join`, `synchronized` e `ExecutorService`
  * Interfaces gráficas e tratamento de eventos

\---

## Capturas de tela

As capturas da execução dos programas no console estão na pasta `Capturas do Console`.

\---

*Projeto desenvolvido para fins acadêmicos.*

