## Tela de Login com Spring Boot e Angular

### Objetivo
O objetivo deste projeto é aprender Spring Boot e Angular através de um projeto prático e simples. O projeto consiste em criar uma aplicação de login com autenticação de usuário e senha, além de uma tela de cadastro para novos usuários, incluindo dados como nome, senha e um papel (role) de usuário. O backend utilizará PostgreSQL junto com Spring Boot para criar um servidor HTTP, enquanto o frontend será desenvolvido utilizando Angular. Após a autenticação bem-sucedida, a aplicação retornará um token JWT (JSON Web Token) Bearer para o usuário.

### Tecnologias Utilizadas
- **Backend:**
  - Spring Boot: Para criar a aplicação Java que servirá como backend.
  - PostgreSQL: Para armazenamento dos dados dos usuários.
  - JWT (JSON Web Tokens): Para autenticação e autorização dos usuários.
  
- **Frontend:**
  - Angular: Para desenvolver a interface do usuário (UI).
  - Angular CLI: Ferramenta de linha de comando para criar e gerenciar projetos Angular.
  - Bootstrap (opcional): Para estilização básica e responsividade.

### Detalhamento do Projeto

1. **Backend com Spring Boot:**
   - **Spring Security:** Configuração para autenticação baseada em JWT.
   - **JWT:** Implementação para geração, validação e renovação de tokens JWT.
   - **Serviços RESTful:** APIs para realizar operações de login, registro de usuários e gestão de perfis.
   - **Persistência de Dados:** Integração com PostgreSQL para armazenar dados de usuários e roles.

2. **Frontend com Angular:**
   - **Componentes:** Desenvolvimento dos componentes para login, registro de usuário e outras funcionalidades necessárias.
   - **Serviços:** Integração com APIs RESTful do backend para realizar operações como login e registro.
   - **Guardas de Rotas:** Implementação de guardas para proteger rotas baseadas na autenticação do usuário.
   - **Interceptadores HTTP:** Para incluir o token JWT nas requisições HTTP.

3. **Fluxo da Aplicação:**
   - **Login:** Usuário preenche nome de usuário (ou email) e senha. Os dados são enviados para o backend que valida as credenciais. Em caso de sucesso, um token JWT é gerado e retornado para o frontend.
   - **Registro de Usuário:** Usuário preenche informações necessárias (nome, email, senha, etc.), que são enviadas ao backend para criação de uma nova conta de usuário no banco de dados.
   - **Autenticação JWT:** O token JWT é armazenado localmente (normalmente em localStorage) e incluído nas requisições subsequentes para autenticação do usuário.
   - **Autorização:** Backend verifica se o token JWT é válido e autoriza o acesso às rotas protegidas com base nas roles associadas ao usuário.

### Conclusão
Este projeto oferece uma ótima oportunidade para aprender sobre desenvolvimento full-stack utilizando Spring Boot e Angular, além de entender conceitos fundamentais como autenticação JWT e persistência de dados. É recomendável seguir tutoriais específicos de cada tecnologia para um aprendizado mais detalhado e eficiente.