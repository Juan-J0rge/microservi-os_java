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

### Regras de negócio

- **Cliente**: nome, CPF, email e telefone são obrigatórios; não permite cadastrar
  dois clientes com o mesmo CPF.
- **Produto**: nome, preço e quantidade são obrigatórios; preço deve ser maior que
  zero; quantidade não pode ser negativa; não permite dois produtos com o mesmo nome.

## Como rodar

### 1. Subir o MongoDB

Se você tiver Docker, o jeito mais simples é:
```bash
docker run -d --name mongo -p 27017:27017 mongo
```
(Sem usuário/senha, só pra facilitar o teste local. Se preferir usar autenticação,
é só ajustar a `spring.data.mongodb.uri` no `application.properties` de cada serviço.)

### 2. Rodar cada microsserviço

Em dois terminais separados (um pra cada projeto):
```bash
cd ClienteService
mvn spring-boot:run
```
```bash
cd ProdutoService
mvn spring-boot:run
```

O ClienteService sobe em `http://localhost:8081` e o ProdutoService em
`http://localhost:8082`.

### 3. Testar com curl

Cadastrar cliente:
```bash
curl -X POST http://localhost:8081/clientes \
  -H "Content-Type: application/json" \
  -d '{"nome":"Maria Silva","cpf":12345678900,"email":"maria@email.com","telefone":11999999999}'
```

Pesquisar clientes:
```bash
curl http://localhost:8081/clientes
```

Cadastrar produto:
```bash
curl -X POST http://localhost:8082/produtos \
  -H "Content-Type: application/json" \
  -d '{"nome":"Notebook","descricao":"Notebook 15 polegadas","preco":3500.00,"quantidade":10}'
```

Pesquisar produtos:
```bash
curl http://localhost:8082/produtos
```

Alterar (troca `{id}` pelo id retornado no cadastro):
```bash
curl -X PUT http://localhost:8081/clientes/{id} \
  -H "Content-Type: application/json" \
  -d '{"nome":"Maria Silva Souza","cpf":12345678900,"email":"maria@email.com","telefone":11999999999}'
```

## Observação

Este trabalho foi feito de forma simplificada em relação ao modelo apresentado em
aula: não tem Config Server, não tem comunicação entre os serviços (como o
VendaService que consome ClienteService e ProdutoService via Feign) e não tem
documentação Swagger/OpenAPI. A ideia foi manter só os conceitos centrais pedidos
na atividade: 2 microsserviços independentes, cada um com cadastrar, alterar e
pesquisar, seu próprio banco de dados e suas próprias regras de negócio.
