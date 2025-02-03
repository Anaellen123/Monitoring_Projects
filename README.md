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

### Passo 2: Configurar as Variáveis de Ambiente

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

### Passo 3: Verificar a Instalação

1. Abra um terminal (ou prompt de comando no Windows) e digite:
   ```bash
   mvn -version
   ```
   **Se a instalação estiver correta, você verá a versão do Maven e detalhes do Java configurados.**

---

### Passo 4: Criar um Projeto Maven

1. **Criar um novo projeto Maven:**
   - No terminal, use o comando:
     ```bash
     mvn archetype:generate -DgroupId=com.exemplo -DartifactId=meu-projeto -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
     ```
     **Isso cria um novo projeto com um modelo básico de aplicação Java.**

2. Abra o projeto no editor de sua preferência.

3. Inicialmente, os arquivos Java estarão denominados como `App.java`. Se preferir, renomeie-os (no meu caso, renomeei para `ProjectManagementSystem.java`).

---

### Passo 3 : Instalar o SQLite (se necessário)
- O SQLite é embutido no código, então não é necessário instalar nada além do **driver JDBC**, mas certifique-se de ter acesso ao banco de dados `projects.db`.

---

## Configuração do Projeto


