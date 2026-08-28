# Evidências
Tela Inicial:
<img width="1915" height="938" alt="image" src="https://github.com/user-attachments/assets/c41d3bcf-9324-4416-bf3e-572ff2d4a14e" />
Tela de Cadastro:
<img width="1895" height="986" alt="image" src="https://github.com/user-attachments/assets/6ecc8d36-5db6-4800-92d0-fe2a5a123677" />
Pop-up com idade calculada:
<img width="1919" height="992" alt="image" src="https://github.com/user-attachments/assets/7f18c86f-a39f-4b93-8d5b-bff300fd5b7c" />

# Resumo do Serviço PatientService
**1. Objetivo**
O serviço PatientService foi desenvolvido como parte de um projeto maior de migração de um módulo legado de registro de pacientes, originalmente escrito em Struts 2 e Hibernate, para uma nova stack padrão da empresa utilizando tecnologias modernas como Java 11, WildFly 24, JSF 3.0 e PrimeFaces 12.

**2. Funcionalidade**
O PatientService é responsável por gerenciar as operações relacionadas a pacientes, encapsulando a lógica de negócios e interagindo com o PatientDao para acessar os dados dos pacientes. As principais funcionalidades incluem:

Listar Pacientes: O método findAll() retorna uma lista de todos os pacientes registrados.
Buscar Paciente por ID: O método findById(Long id) busca um paciente específico com base em seu identificador único.
Salvar Paciente: O método save(Patient patient) persiste um novo paciente ou atualiza um existente no banco de dados.
Calcular Idade: O método calculateAge(Long patientId) invoca uma stored procedure para calcular a idade de um paciente com base no seu ID.

## 📋 Features
- JSF pages with Facelets templating
- PrimeFaces UI components
- Example managed bean (`IndexBean`) with `@ViewScoped` and CDI
- Template-based layout (`template-sistema.xhtml`)
- Basic CSS styling via WebJars and custom stylesheet

## 🚀 Requirements
- **Java: 11**
- **WildFly: 24 (Jakarta EE 9.1)**
- **JSF: 3.0 (Jakarta Faces)**
- **PrimeFaces: 12**
- **JPA: 3.0 (Hibernate)**
- **Banco de Dados: Oracle 11g/19c ou H2 (modo Oracle)**

## 📦 Instruções para Execução do Projeto
**Pré-requisitos**
- **Java Development Kit (JDK):** Certifique-se de ter o JDK 11 instalado.
- **WildFly:** Instale o servidor de aplicação WildFly 24.
- **Maven:** Tenha o Apache Maven instalado para gerenciar dependências e construir o projeto.

# Passos para Execução
1. Clone o Repositório:

```bash
git clone <URL_DO_REPOSITORIO>
cd medicamento
```

2. Configurar o Banco de Dados:

Certifique-se de que o banco de dados H2 ou Oracle está configurado e acessível.
Caso use o H2, as configurações estão no arquivo de migração src/main/resources/db/migration.

3. Construir o Projeto:

Execute o seguinte comando para compilar o projeto e gerar o arquivo WAR:

```bash
mvn clean package
```

4. Deploy no WildFly:

Copie o arquivo WAR gerado (medicamentos-app.war) da pasta target para o diretório de deploy do WildFly, que geralmente é WILDFLY_HOME/standalone/deployments/.
Alternativamente, você pode iniciar o WildFly e usar a interface de gerenciamento para fazer o upload do WAR.
Iniciar o WildFly:

5. Inicie o servidor WildFly:

```bash
WILDFLY_HOME/bin/standalone.sh (Linux/Mac)
WILDFLY_HOME\bin\standalone.bat (Windows)
```

7. Acessar a Aplicação:

Abra um navegador e acesse a aplicação pelo endereço:
http://localhost:8080/medicamentos-app

📍 Considerações: 
Verifique os logs do WildFly para identificar quaisquer problemas que possam ocorrer durante a inicialização ou execução da aplicação.

Para executar testes, use o comando:
```bash
Copy code
mvn test
```

## 💻 Acessar contexto
- http://localhost:8080/medicamentos-app



