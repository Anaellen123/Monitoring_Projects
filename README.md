# Passo a Passo - Sistema de Gerenciamento de Projetos

---

## Configuração do Ambiente

---
### Passo1: Instalar o JDK (Java Development Kit)
- Certifique-se de ter o JDK 8 ou superior instalado em seu sistema.
- Para verificar se o Java está instalado corretamente, abra o terminal e digite:

  ```bash
  java -version
  ```

- Caso não tenha o Java instalado, você pode baixar o JDK [aqui](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html).

---

### Passo 2: Instalar o Maven

1. **Baixar o Apache Maven:**
   - Acesse o site oficial do Maven:  
     [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)
   - Baixe a versão mais recente do Maven em formato zip ou tar.gz para o seu sistema operacional.

2. **Descompactar o arquivo:**
   - Descompacte o arquivo baixado em um diretório de sua escolha. Por exemplo:
     - **Windows:** C:\maven
     - **Linux/Mac:** /usr/local/maven

---

### Passo 3: Configurar as Variáveis de Ambiente

#### **No Windows:**

1. **Definir a variável `MAVEN_HOME`:**
   - Vá para **Painel de Controle** > **Sistema e Segurança** > **Sistema** > **Configurações Avançadas do Sistema**.
   - Clique em **Variáveis de Ambiente**.
   - Adicione uma nova variável de sistema:
     - **Nome:** `MAVEN_HOME`
     - **Valor:** o caminho onde você descompactou o Maven (por exemplo, `C:\maven`).

2. **Adicionar o bin do Maven ao `PATH`:**
   - Na mesma janela de variáveis de ambiente, edite a variável `Path` e adicione o seguinte ao final:
     - `C:\maven\bin`

#### **No Linux/Mac:**

1. **Definir a variável `MAVEN_HOME`:**
   - Abra o terminal e edite o arquivo `.bashrc` ou `.zshrc`:
     ```bash
     nano ~/.bashrc   # ou nano ~/.zshrc, dependendo do shell que você usa
     ```
   - Adicione a linha:
     ```bash
     export MAVEN_HOME=/usr/local/maven
     export PATH=$MAVEN_HOME/bin:$PATH
     ```

2. **Salvar e fechar o arquivo, então execute:**
   ```bash
   source ~/.bashrc   # ou source ~/.zshrc
   ```

---

### Passo 4: Verificar a Instalação

1. Abra um terminal (ou prompt de comando no Windows) e digite:
   ```bash
   mvn -version
   ```
   **Se a instalação estiver correta, você verá a versão do Maven e detalhes do Java configurados.**

---

### Passo 5: Criar um Projeto Maven

1. **Criar um novo projeto Maven:**
   - No terminal, use o comando:
     ```bash
     mvn archetype:generate -DgroupId=com.exemplo -DartifactId=meu-projeto -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
     ```
     **Isso cria um novo projeto com um modelo básico de aplicação Java.**

2. Abra o projeto no editor de sua preferência.

3. Inicialmente, os arquivos Java estarão denominados como `App.java`. Se preferir, renomeie-os (no meu caso, renomeei para `ProjectManagementSystem.java`).

---

### Passo 6: Instalar o SQLite (se necessário)
- O SQLite é embutido no código, então não é necessário instalar nada além do **driver JDBC**, mas certifique-se de ter acesso ao banco de dados `projects.db`.

---

## Configuração do Projeto

### Configuração do Maven (pom.xml)

- O arquivo `pom.xml` contém todas as dependências necessárias para o projeto. Ele deve incluir o driver JDBC para SQLite.
- Um exemplo básico de dependência do SQLite no Maven:

  ```xml
  <dependencies>
      <dependency>
          <groupId>org.xerial</groupId>
          <artifactId>sqlite-jdbc</artifactId>
          <version>3.34.0</version>
      </dependency>
  </dependencies>
  ```

---

## Executar o Back-End (Java)

### a. Compilar o Projeto com Maven
- Navegue até a pasta do projeto no terminal.
- Execute o seguinte comando para compilar o projeto:

  ```bash
  mvn clean install
  ```

### b. Executar a Aplicação Java
- Após a compilação, execute o sistema com o Maven:

  ```bash
  mvn exec:java
  ```

- O banco de dados SQLite será gerado automaticamente com o nome `projects.db` na pasta `backend/src/main/resources/db/`.

### c. Testar o Back-End
- Se você configurou corretamente a aplicação, a execução do comando anterior deverá criar as tabelas no banco de dados e permitir adicionar projetos e tarefas.
- O sistema irá exibir os projetos e tarefas no terminal.

---

## 4. Executar o Front-End (HTML e CSS)

### a. Abrir o Arquivo HTML
- O front-end está em formato estático, ou seja, não há necessidade de compilação ou execução de servidores.
- Basta abrir o arquivo `frontend/index.html` em um navegador de sua escolha.
- **Observação:** O front-end estará com dados estáticos (não dinâmicos), a menos que você integre o back-end com o front-end usando AJAX, Fetch API ou uma framework como Spring Boot para criar endpoints RESTful.

---

## 5. Integrar Front-End e Back-End (Opcional)

### a. Criar API RESTful
- Para integrar o front-end ao back-end de maneira dinâmica, é necessário criar uma API no back-end (Java). Você pode usar o Spring Boot ou outra framework para isso.
- Por exemplo, crie um endpoint para obter projetos:

  ```java
  @GetMapping("/projects")
  public List<Project> getProjects() {
      // lógica para retornar os projetos
  }
  ```

### b. Consumir a API no Front-End
- No arquivo `frontend/index.html`, você pode usar `fetch()` ou AJAX para pegar os dados da API e mostrar na página. Exemplo de código JavaScript usando fetch:

  ```javascript
  fetch('http://localhost:8080/projects')
      .then(response => response.json())
      .then(data => {
          // Processa os dados e exibe no HTML
      })
      .catch(error => console.error('Erro:', error));
  ```

---

## 6. Rodar o Sistema Localmente (Em um ambiente real)

### a. Rodando o Back-End
- Execute os comandos Maven (como mencionado antes) para rodar o back-end localmente, o que permitirá que os dados sejam manipulados via terminal ou API RESTful (se você implementou).

### b. Rodando o Front-End
- Como o front-end é apenas uma página HTML estática, não é necessário rodar nenhum servidor. Basta abrir o arquivo `index.html` em qualquer navegador.

---

## 7. Finalizando e Testando o Sistema

### Teste o Back-End:
- Adicione projetos e tarefas e verifique se os dados estão sendo salvos corretamente no banco de dados `projects.db`.

### Teste o Front-End:
- Verifique se os dados estão sendo exibidos corretamente no navegador (no caso de uma implementação estática).

### Integração:
- Caso tenha implementado a integração entre back-end e front-end (API + Fetch), certifique-se de que a comunicação está ocorrendo corretamente e que as informações dos projetos e tarefas estão sendo exibidas dinamicamente.
