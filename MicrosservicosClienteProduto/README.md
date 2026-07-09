# Microsserviços de Cliente e Produto

Este trabalho tem 2 microsserviços independentes, seguindo o mesmo modelo visto em
aula (Spring Boot + MongoDB), só que de forma bem mais simples: sem Config Server,
sem comunicação entre serviços (Feign) e sem Swagger — só o essencial pra mostrar
o conceito de arquitetura de microsserviços.

```
ClienteService/   -> microsserviço de Cliente (porta 8081, banco cliente_db)
ProdutoService/   -> microsserviço de Produto (porta 8082, banco produto_db)
```

Cada um é um projeto Maven/Spring Boot **totalmente independente**: tem seu próprio
`pom.xml`, roda em uma porta diferente, e tem seu próprio banco de dados. Isso é
justamente a ideia de microsserviços: cada serviço cuida só da sua própria
funcionalidade (cliente cuida só de cliente, produto cuida só de produto) e pode
ser feito o deploy, escalado e alterado sem afetar o outro.

## Estrutura de cada microsserviço

```
src/main/java/br/com/aluno/<servico>/
 ├── domain/       -> Entidade mapeada para o MongoDB (@Document)
 ├── repository/   -> Interface MongoRepository (JPA/Mongo)
 ├── service/       -> Regras de negócio (validações, checagem de duplicidade)
 ├── controller/    -> Controller REST (recebe as requisições HTTP)
 └── exception/     -> Exceções customizadas + handler global
```

### Endpoints (iguais nos dois serviços, trocando `/clientes` por `/produtos`)

| Ação        | Método | URL              |
|-------------|--------|------------------|
| Cadastrar   | POST   | `/clientes`      |
| Alterar     | PUT    | `/clientes/{id}` |
| Pesquisar   | GET    | `/clientes`      |
| Buscar por id | GET  | `/clientes/{id}` |




