# Teste Prático Prothera

Projeto desenvolvido em Java para o teste prático do processo seletivo da Prothera.

## Tecnologias utilizadas

- Java 21
- IntelliJ IDEA
- Git
- GitHub

## Objetivo

O projeto simula o cadastro e o processamento de informações de funcionários de uma indústria.

Foram utilizados conceitos de:

- Programação Orientada a Objetos
- Herança
- Listas
- Map
- LocalDate
- BigDecimal
- Ordenação
- Formatação de datas e valores

## Funcionalidades implementadas

- Cadastro dos funcionários
- Remoção do funcionário João
- Impressão dos dados dos funcionários
- Formatação das datas no padrão `dd/MM/yyyy`
- Formatação dos salários no padrão brasileiro
- Reajuste salarial de 10%
- Agrupamento dos funcionários por função
- Impressão dos funcionários agrupados por função
- Busca dos aniversariantes dos meses 10 e 12
- Identificação do funcionário com maior idade
- Ordenação dos funcionários em ordem alfabética
- Cálculo do total dos salários
- Cálculo da quantidade de salários mínimos recebida por cada funcionário

## Estrutura do projeto

```text
src/
├── Pessoa.java
├── Funcionario.java
└── Principal.java
```

## Como executar

1. Clone o repositório.
2. Abra o projeto em uma IDE Java.
3. Utilize Java 21 ou superior.
4. Execute a classe `Principal`.

## Arquitetura

As operações foram mantidas na classe `Principal` para seguir o escopo proposto no teste.
Em uma aplicação maior, essas regras poderiam ser separadas em uma classe de serviço, 
como `FuncionarioService`, deixando a `Principal` apenas responsável pela execução do programa.

## Autor

Gustavo Marinho Prado