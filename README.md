**PROJETO DE VOTAÇÃO - SPRING BOOT**

**Descrição**  
Este é um projeto simples de votação desenvolvido com Spring Boot, permitindo que usuários votem em candidatos registrados no sistema.  

**Tecnologias Utilizadas**  
- Java 21  
- Spring Boot  
- Spring Data JPA  
- Banco de Dados  MySQL  

**Como Executar o Projeto**  
1. Clone o repositório  
2. Configure as propriedades no `application.properties`  
3. Execute o projeto com `mvn spring-boot:run` ou através da IDE  

**Endpoints da API**  
- `GET /api/candidates` - Lista todos os candidatos  
- `GET /api/candidates/{id}` - Busca um candidato pelo ID  
- `POST /api/candidates` - Adiciona um novo candidato  
- `PUT /api/candidates/{id}` - Atualiza um candidato existente  
- `DELETE /api/candidates/{id}` - Remove um candidato  

- `POST /api/votes/{userId}/{candidateId}` - Registra um voto  
- `GET /api/votes` - Lista todos os votos  

**Tratamento de Erros**  
- Retorno `404` para recursos não encontrados  
- Retorno `400` para requisições inválidas  
- Retorno `500` para erros internos  

**Melhorias Futuras**  
- Implementar autenticação e autorização para garantir segurança  
- Adicionar paginação nos endpoints de listagem  
- Melhorar o tratamento de erros para mensagens mais detalhadas  
- Melhorar a estrutura do banco de dados para suportar alta demanda

- Testes Unitários Implementados

Adicionei testes unitários para garantir o funcionamento adequado das funcionalidades do sistema. Os testes abrangem:

Testes para o endpoint de candidatos

Teste de criação, atualização, remoção e listagem de candidatos.
Verificação se o candidato foi corretamente adicionado e retornado pelos endpoints.
Testes para o endpoint de votos

Verificação se o voto é registrado corretamente.
Testes para verificar se a quantidade de votos de um candidato é atualizada após o voto.
Testes para tratamento de erros

Testes para garantir que erros como 404 e 400 são retornados corretamente quando um recurso não é encontrado ou quando a requisição é inválida.

**Autor**  
LeandroBryto
