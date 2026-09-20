# Controle de Pedidos de Recursos

Olá, pessoal!

Este projeto foi desenvolvido com o objetivo de aplicar
conceitos fundamentais do ecossistema Java e Spring Boot,
simulando uma solução para um problema real do setor de logística.

# Contexto e motivação

Atualmente, como trabalho no setor de Logística, identifiquei 
a necessidade recorrente de um sistema para a solicitação
e gestão de recursos internos entre os diferentes departamentos
da empresa.

Atraves dessa demanda do dia a dia, decidi projetar e desenvolver
esta aplicação backend como um estudo prático caso. 

O foco principal é fazer uma solução funcional que tenha:

1° Relacionamentos e regras de negócio
2° Controle de permissões e perfis de acesso
3° Fluxo de status dos pedidos
4° Gerar relatorios e consulta de relatórios.

# O que o projeto já tem?

* **Gestão de Funcionários:** Cadastro de colaboradores com validação de dados de entrada (como CPF, e-mail e campos obrigatórios).
* **Abertura de Solicitações:** Mapeamento do pedido de recursos atribuindo a solicitação diretamente ao funcionário solicitante.
* **Trava de Regra de Negócio por Setor:** Restrição que impede a abertura de uma nova solicitação caso o setor já possua uma solicitação ativa com status **PENDENTE**.
* **Tratamento Global de Exceções:** Manipulação centralizada de erros com exceções personalizadas (`GlobalExceptionHandler`) e retornos estruturados com status HTTP adequados.
* **Padronização REST:** Controllers estruturados com `ResponseEntity`, garantindo boas práticas de códigos de resposta HTTP (`200 OK`, `201 Created`, `400 Bad Request`, `404 Not Found`, etc.).





