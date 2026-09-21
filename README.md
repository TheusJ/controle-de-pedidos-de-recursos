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

Este projeto está em constante evolução, então a documentação pode receber atualizações de tempos em tempos.

A API é documentada com **OpenAPI 3** usando o [springdoc-openapi](https://springdoc.org/) (v3.0.3, compatível com Spring Boot 4). Com a aplicação rodando, a documentação interativa fica disponível em:
