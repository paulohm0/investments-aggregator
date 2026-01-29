<div align="center">

  <h1>Investments Aggregator</h1>

  <p>
    API desenvolvida para agregar e gerenciar carteiras de investimentos. O sistema permite cadastrar usuários, contas e ativos, além de calcular o valor total da carteira em tempo real consultando cotações externas.
  </p>
  <p>
    Este projeto foi feito seguindo as aulas do canal <a href="https://www.youtube.com/playlist?list=PLxCh3SsamNs62j6T7bv6f1_1j9H9pEzkC">Build&Run</a> e ao finalizar as aulas, refatorei algumas partes para seguir os princípios de Clean Architecture e Domain-Driven Design (DDD).
  </p>

</div>

## Tecnologias

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Cloud OpenFeign** (Integração com API Brapi)
- **SpringDoc OpenAPI 3** (Swagger)
- **H2 Database / PostgreSQL** (via Docker)
- **Maven**

## Práticas Adotadas

- **Clean Architecture**: Separação clara entre Camada de Aplicação, Domínio e Infraestrutura.
- **DDD (Domain-Driven Design)**: Utilização de Entidades, Agregados, Value Objects e Domain Exceptions.
- **SOLID**: Princípios de design aplicados.
- **Global Exception Handling**: Tratamento centralizado de erros.
- **DTO Pattern**: Isolamento da API.

<h2 align="center">Como executar</h2>

1. Clonar repositório:
```
git clone https://github.com/paulohm0/investments-aggregator.git

```

2. Construir o projeto:
```
mvnw.cmd clean package

```

3. Executar a aplicação:
```
java -jar target/investments-aggregator-0.0.1-SNAPSHOT.jar

```

A API poderá ser acessada em localhost:8080. 

A documentação estará disponível no [swagger-ui](http://localhost:8080/swagger-ui/index.html)

Abaixo estão alguns exemplos de requisições. Para documentação completa, consulte o Swagger.

<h2 align="center">API Endpoints</h2>

1. Criar Usuário
```
HTTP
POST :8080/users
Content-Type: application/json

{
    "username": "paulodev",
    "email": "paulo@dev.com",
    "password": "123"
}
```
2. Criar Ação (Stock)
```
HTTP
POST :8080/stocks
Content-Type: application/json

{
    "stockId": "PETR4",
    "description": "Petrobras PN"
}
```
3. Associar Ação à Carteira
```
HTTP
POST :8080/accounts/{accountId}/stocks
Content-Type: application/json

{
    "stockId": "PETR4",
    "quantity": 100
}
```
4. Listar Carteira (Com Cotação em Tempo Real).
Retorna os ativos da conta com o valor total calculado com base no preço atual de mercado.
```
HTTP
GET :8080/accounts/{accountId}/stocks
Response:
JSON
[
  {
    "stockId": "PETR4",
    "quantity": 100,
    "total": "R$ 3.850,00"
  }
]
```
