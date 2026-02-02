# API de Vendas

## Sobre o Projeto

Este projeto foi desenvolvido como parte de um processo seletivo. O desafio consistia em criar uma API REST utilizando Spring Boot para gerenciamento de vendas.

### Requisitos do Desafio

- Criar uma API utilizando Spring Boot
- Utilizar banco de dados em memoria (H2)
- Desenvolver apenas o backend (sem frontend)
- Uma venda deve conter: id, data da venda, valor, id do vendedor e nome do vendedor

### Servicos Implementados

1. **Criar uma nova venda** - POST /vendas
2. **Listar vendedores com resumo** - GET /vendas/vendedores
   - Retorna: nome, total de vendas e media de vendas diarias
   - Filtrado por periodo (dataInicio e dataFim)

### O que foi entregue

- API funcional com os dois endpoints solicitados
- Banco de dados H2 em memoria
- Testes unitarios e de integracao
- Codigo organizado seguindo boas praticas
- Historico de commits documentando a evolucao do projeto

## Tecnologias

- Java 17
- Spring Boot 3.2.2
- Spring Data JPA
- H2 Database (banco em memoria)
- Maven
- JUnit 5 e Mockito (testes)

## Requisitos

- Java 17 ou superior instalado
- Maven (opcional, o projeto inclui Maven Wrapper)

## Como executar

1. Clone o repositorio:
```
git clone https://github.com/AdielsonMedeiros/Processo-Seletivo.git
```

2. Acesse a pasta do projeto:
```
cd Processo-Seletivo
```

3. Execute a aplicacao:
```
mvn spring-boot:run
```

4. A aplicacao estara disponivel em `http://localhost:8080`

## Endpoints

### Criar venda

**POST** `/vendas`

Request body:
```json
{
    "dataVenda": "2026-02-02",
    "valor": 150.50,
    "vendedorId": 1,
    "vendedorNome": "Joao"
}
```

Response (201 Created):
```json
{
    "id": 1,
    "dataVenda": "2026-02-02",
    "valor": 150.50,
    "vendedorId": 1,
    "vendedorNome": "Joao"
}
```

### Listar vendedores com resumo

**GET** `/vendas/vendedores?dataInicio=2026-02-01&dataFim=2026-02-02`

Parametros:
- `dataInicio` - Data inicial do periodo (formato: YYYY-MM-DD)
- `dataFim` - Data final do periodo (formato: YYYY-MM-DD)

Response (200 OK):
```json
[
    {
        "nome": "Joao",
        "totalVendas": 2,
        "mediaVendasDiarias": 1.00
    },
    {
        "nome": "Maria",
        "totalVendas": 1,
        "mediaVendasDiarias": 0.50
    }
]
```

## Banco de dados

O banco H2 pode ser acessado em `http://localhost:8080/h2-console` com as configuracoes:

- JDBC URL: `jdbc:h2:mem:vendasdb`
- User: `sa`
- Password: (vazio)

## Testes

Para executar os testes:
```
mvn test
```

O projeto inclui:
- Testes unitarios (VendaServiceTest)
- Testes de integracao (VendaControllerIntegrationTest)

## Estrutura do projeto

```
src/
├── main/java/com/exemplo/vendas/
│   ├── VendasApplication.java
│   ├── controller/
│   │   └── VendaController.java
│   ├── dto/
│   │   ├── VendaRequest.java
│   │   └── VendedorResumoDTO.java
│   ├── model/
│   │   └── Venda.java
│   ├── repository/
│   │   └── VendaRepository.java
│   └── service/
│       └── VendaService.java
└── test/java/com/exemplo/vendas/
    ├── VendaControllerIntegrationTest.java
    └── VendaServiceTest.java
```
