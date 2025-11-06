# Spring Boot Project – Autoescola - Checkpoint 1 - 6º Semestre

## 🚀 3ESPV - Engenharia de Software 3º Ano
### 🧑‍💻 Guilherme Barreto Santos - RM97674
### 🧑‍💻 Henrique Parra - RM551973
### 🧑‍💻 Nicolas Oliveira da Silva - RM98939
### 🧑‍💻 Roberto Oliveira - RM551460
### 🧑‍💻 Tony Willian - RM550667

## Descrição do Projeto
Este projeto implementa uma **API REST** para gerenciar o cadastro de **instrutores** e **alunos** de uma autoescola, bem como o agendamento de instruções.  
Ele foi desenvolvido em **Java** usando **Spring Boot**, com **MySQL** como banco de dados e **Flyway** para controle de migrations.  

O sistema implementa funcionalidades de:

- Cadastro, listagem, atualização e exclusão de instrutores;
- Cadastro, listagem, atualização e exclusão de alunos;
- Agendamento e cancelamento de instruções (planejado para próximas etapas do projeto);
- Controle de dados ativos/inativos (exclusão lógica).

---

## Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **MySQL 8**
- **Flyway** (controle de migrations)
- **Jakarta Validation** (validação de dados)
- **Lombok** (redução de boilerplate)
- **Maven** (gerenciamento de dependências e build)

---

## Estrutura do Projeto

```bash
src/main/java/br/com/fiap3espv/spring_boot_project
│
├─ SpringBootProjectApplication.java # Classe principal do Spring Boot
├─ controller/
│ ├─ HealthCheckController.java # Endpoint de verificação do sistema
│ └─ InstrutorController.java # Endpoints de instrutores
├─ instrutor/
│ ├─ DadosCadastroInstrutor.java # DTO de cadastro
│ ├─ DadosAtualizacaoInstrutor.java # DTO de atualização
│ ├─ DadosListagemInstrutor.java # DTO de listagem
│ ├─ Especialidade.java # Enum de especialidades
│ ├─ Instrutor.java # Entidade Instrutor
│ └─ InstrutorRepository.java # Repositório JPA
├─ endereco/
│ ├─ DadosEndereco.java # DTO de endereço
│ └─ Endereco.java # Embeddable para endereço
```

> Observação: A implementação de **alunos** seguirá a mesma estrutura dos instrutores.

---

## Banco de Dados

O projeto utiliza **MySQL** e o controle de migrations é feito com **Flyway**.  

### Migrations implementadas:

1. `V1__create-table-instrutores.sql` – Criação da tabela `instrutores`
2. `V2__alter-table-instrutores-add-column-telefone.sql` – Adição do campo `telefone`
3. `V3__alter-table-instrutores-add-column-ativo.sql` – Adição do campo `ativo`
4. `V4__create-table-alunos.sql` – Criação da tabela `alunos`

> Todas as migrations são aplicadas automaticamente pelo Spring Boot ao iniciar o projeto.

---

## Configuração do Banco de Dados

No arquivo `application.properties`:

```properties
spring.application.name=SpringBootProject

spring.datasource.url=jdbc:mysql://localhost/springboot3espv
spring.datasource.username=root
spring.datasource.password=fiap
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
