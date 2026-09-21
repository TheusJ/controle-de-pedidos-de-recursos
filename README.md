# Controle de Pedidos de Recursos

Olá, pessoal!

Este projeto foi desenvolvido com o objetivo de aplicar conceitos fundamentais do ecossistema Java e Spring Boot, simulando uma solução para um problema real do setor de logística.

## Contexto e motivação

Como trabalho no setor de Logística, identifiquei a necessidade recorrente de um sistema para a solicitação e gestão de recursos internos entre os diferentes departamentos da empresa.

A partir dessa demanda do dia a dia, decidi projetar e desenvolver esta aplicação backend como um estudo de caso prático.

O foco principal é construir uma solução funcional que tenha:

1. Relacionamentos e regras de negócio
2. Controle de permissões e perfis de acesso
3. Fluxo de status dos pedidos
4. Geração e consulta de relatórios

## O que o projeto já tem?

* **Gestão de Funcionários:** Cadastro de colaboradores com validação de dados de entrada (como CPF, e-mail e campos obrigatórios).
* **Abertura de Solicitações:** Mapeamento do pedido de recursos atribuindo a solicitação diretamente ao funcionário solicitante.
* **Trava de Regra de Negócio por Setor:** Restrição que impede a abertura de uma nova solicitação caso o setor já possua uma solicitação ativa com status **PENDENTE**.
* **Tratamento Global de Exceções:** Manipulação centralizada de erros com exceções personalizadas (`GlobalExceptionHandler`) e retornos estruturados com status HTTP adequados.
* **Padronização REST:** Controllers estruturados com `ResponseEntity`, seguindo boas práticas de códigos de resposta HTTP (`200 OK`, `201 Created`, `400 Bad Request`, `404 Not Found`, etc.).

## 📖 Documentação da API (Swagger)

A API é documentada com **OpenAPI 3** usando o [springdoc-openapi](https://springdoc.org/) (v3.0.3, compatível com Spring Boot 4). Com a aplicação rodando, a documentação interativa fica disponível em:

| Recurso | URL |
|---|---|
| Swagger UI (testar os endpoints) | `http://localhost:8080/swagger-ui/index.html` |
| Especificação OpenAPI (JSON) | `http://localhost:8080/v3/api-docs` |

### Endpoints

| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/api/v1/funcionarios` | Cadastrar Funcionário |
| `POST` | `/api/v1/solicitacao/{funcionarioId}` | Solicitar Recursos |
| `GET` | `/api/v1/solicitacao?idSolicitacao=&idFuncionario=` | Buscar Solicitação por ID |
| `GET` | `/api/v1/solicitacao/{idFuncionario}` | Buscar todas as Solicitações |

### Regras de negócio documentadas

**Cadastrar Funcionário**
- Apenas funcionários com cargo **RH** podem cadastrar novos funcionários.
- Os dados são validados (CPF, e-mail, campos obrigatórios); dados inválidos geram exceção.
- Não é permitido cadastrar dois funcionários com o mesmo CPF ou e-mail.
- Retorna `201 Created` em caso de sucesso.

**Solicitar Recursos**
- Verifica se o funcionário informado existe.
- Se o setor já tiver uma solicitação com status **PENDENTE**, novas solicitações são recusadas até que ela seja concluída.
- Retorna `201 Created` em caso de sucesso.

**Buscar Solicitação**
- Verifica se a solicitação existe; caso contrário, gera exceção.
- Funcionários comuns só visualizam as solicitações feitas por eles mesmos.
- Cargos **COMPRADOR** e **RH** podem visualizar qualquer solicitação.

**Buscar todas as Solicitações**
- Cargos **COMPRADOR** e **RH** visualizam todas as solicitações do sistema.
- Os demais cargos visualizam apenas as suas próprias solicitações.

> Erros de validação e de regra de negócio são tratados globalmente (`GlobalExceptionHandler`) e retornam uma resposta de erro padronizada.
