# LuizaLabs - Desafio técnico

## 🎯 Objetivo do desafio


Criar um sistema para transformar arquivos de entrada em um novo formato de saída. O sistema deve ser capaz de ler
arquivos de texto e gerar um arquivo de saída com base em regras de negócios específicas.

---
## 📄 Overview


Este projeto é um desafio técnico do LuizaLabs. O objetivo é criar uma API para upload, processamento e gerenciamento de
arquivos. A API suporta upload de múltiplos arquivos de texto e os processa de acordo com a lógica de negócios definida
na aplicação. Também é possível utilizar o sistema de arquivos via diretório com programação automática de leitura e
processamento.

---
## 📚 Table of Contents

* [LuizaLabs - Desafio técnico](#luizalabs---desafio-técnico)
  * [🎯 Objetivo do desafio](#-objetivo-do-desafio)
  * [📄 Overview](#-overview)
  * [📚 Table of Contents](#-table-of-contents)
  * [🚀 Features (características)](#-features-características)
  * [🛠 Getting Started](#-getting-started)
    * [Prerequisites](#prerequisites)
    * [Installation](#installation)
    * [Running the Application](#running-the-application)
    * [⏱️ Scheduler](#-scheduler)
    * [Propriedades](#propriedades)
    * [Mapeamento de Colunas](#mapeamento-de-colunas)
  * [📡 API Endpoints](#-api-endpoints)
    * [Upload Files](#upload-files)
    * [Execução manual](#execução-manual)
  * [💡 Sugestões de Melhoria](#-sugestões-de-melhoria)
  * [🌳 Estrutura do Projeto](#-estrutura-do-projeto)


--- 
## 🚀 Features (características)

- Agendar processamento de arquivo
- Parametrização de mapeamento de colunas
- Carregar vários arquivos de texto
- Processar arquivos carregados
- Mover arquivos para diretórios específicos
- Documentação do Swagger para endpoints de API
- Logs de aplicação com SLF4J e Logback
- Testes de unidade com JUnit e Mockito
- Testes de integração com Spring Boot Test
---
## 🛠 Getting Started

### Prerequisites

- Java 21 or higher
- Maven 3.6.0 or higher
- Git

### Installation

1. Clone o repositório:
    ```sh
    git clone https://github.com/mauriciopfl/labs-desafio.git
    cd labs-desafio
    ```
2. Configure o arquivo application.properties conforme necessário.:
    ```properties
    spring.application.name=challenge
    #folder for input files
    input.files.path=src/files/input
    
    #folder for files already processed
    processed.files.path=src/files/processed
    
    #folder for files the converted output files
    output.files.path=src/files/output
    
    #folder for files with errors
    error.files.path=src/files/error
    
    #folder to uploaded files
    upload.dir=src/files/upload
    
    #time for automated execution in ms, if you want to disable it, enter 0
    interval.to.execute=0
    
    #column mapping file
    columns.file.path=src/main/resources/column-mapping.properties
    spring.config.additional-location=classpath:column-mapping.properties


    ```
3. Configure o arquivo column-mapping.properties conforme necessário.:
    ```properties
   user.id.start=0
    user.id.end=10
    
    user.name.start=11
    user.name.end=55
    
    order.id.start=55
    order.id.end=65
    
    product.id.start=65
    product.id.end=75
    
    order.value.start=75
    order.value.end=87
    
    order.date.start=87
    order.date.end=95

    ```
4. Execute o build do project utilizando Maven:
    ```sh
    mvnw clean install
    ```

### Running the Application

1. Execute o aplicativo Spring Boot:
    ```sh
    mvnw spring-boot:run
    ```

2. O aplicativo iniciará em `http://localhost:8080`.
3. Acesse a documentação do Swagger em `http://localhost:8080/swagger-ui.html`.
4. Logs da aplicação são exibidos no console ou no arquivo `logs/application.log`.
---
### ⏱️ Scheduler

A aplicação está configurada para mover os arquivos automáticamente de acordo com o parametro `ìnterval.to.execute`.
Você pode ajustar a expressão cron no arquivo `application.properties` conforme necessário.

### Propriedades

- `upload.dir`: Diretório onde os arquivos são enviados ao realizar upload via API.
- `input.files.path`: Diretório onde os arquivos de entrada que serão convertidos.
- `processed.files.path`: Diretório onde os arquivos processados são movidos após processamento.
- `output.files.path`: Diretório onde os arquivos de saída já convertidos são salvos após o processamento.
- `error.files.path`: Diretório onde os arquivos com erros são movidos após o processamento.
- `interval.to.execute`: Intervalo de tempo em milissegundos para executar o scheduler. Defina como 0 para desativar o
  scheduler (processamento automático).

### Mapeamento de Colunas

O arquivo `column-mapping.properties` deve conter o mapeamento das colunas dos arquivos de texto para os campos do
modelo. Exemplo:

```properties
user.id.start=0
user.id.end=10
user.name.start=11
user.name.end=55
order.id.start=55
order.id.end=65
product.id.start=65
product.id.end=75
order.value.start=75
order.value.end=87
order.date.start=87
order.date.end=95
```
---
## 📡 API Endpoints

### Upload Files

- **URL:** `/api/files/upload`
- **Method:** `POST`
- **Consumes:** `multipart/form-data`
- **Description:** Upload multiplos arquivos de texto.
- **Request:**
    - `files` (required): Lista de arquivos á enviar.
- **Response:**
    - `200 OK`:  Retorna a lista de arquivos processados.
    - `400 Bad Request`: Formato de arquivo inválido ou outros erros.

### Execução manual

- **URL:** `/api/manual/execute`
- **Method:** `GET`
- **Description:** Executa o processo de transformação manual para todos os arquivos que estiverem na pasta de entrada (
  input). Geralmente usado caso o Scheduler esteja desativado.
- **Response:**
    - `200 OK`:  Retorna a lista de arquivos processados. 
---
## 💡 Sugestões de Melhoria
 - Finalização e melhorias do CI/CD (Workflow Github Actions)
 - Adicionar camada de segurança (Autenticação e Autorização)
 - Endpoint para consultar os LOG`s da aplicação
 - Endpoint para consultar e download de arquivos processados
 - Endpoint para consultar e download de arquivos com erro
 - Endpoint para criar arquivos a partir de um array de objetos
 - Endpoint para deletar arquivos processados
 

---
## 🌳 Estrutura do Projeto

```plaintext
src
├── main
│   ├── java
│   │   └── com
│   │       └── challenge
│   │           ├── ChallengeApplication.java
│   │           └── parsers
│   │               ├── Application
│   │               │   └── UseCases
│   │               │       ├── Files
│   │               │       │   ├── MoveFileToPath.java
│   │               │       │   ├── MoveFileToPathWithDateSubFolder.java
│   │               │       │   ├── MoveUploadFiles.java
│   │               │       │   ├── ReadFileAsFileContentDTO.java
│   │               │       │   ├── UploadFiles.java
│   │               │       │   └── WriteObjectAsJsonFile.java
│   │               │       └── Parsing
│   │               │           ├── ProcessOrder.java
│   │               │           ├── ProcessProduct.java
│   │               │           └── ProcessUser.java
│   │               ├── DTO
│   │               │   └── FileContentDTO.java
│   │               ├── Infraestructure
│   │               │   ├── Services
│   │               │   │   ├── FileProcessingScheduler.java
│   │               │   │   └── ProcessingService.java
│   │               │   └── Web
│   │               │       └── Controllers
│   │               │           ├── FileUploadController.java
│   │               │           └── ManualExecutionController.java
│   │               ├── config
│   │               │   ├── AppConfig.java
│   │               │   ├── ColumnMappingConfig.java
│   │               │   └── SchedulerCondition.java
│   │               └── core
│   │                   └── Domain
│   │                       ├── Entities
│   │                       │   ├── Order.java
│   │                       │   ├── Product.java
│   │                       │   └── User.java
│   │                       ├── Exceptions
│   │                       │   ├── GenericDateException.java
│   │                       │   ├── GenericIdException.java
│   │                       │   ├── GenericValueException.java
│   │                       │   ├── OrderValidationException.java
│   │                       │   └── UserValidationException.java
│   │                       └── ValueObjects
│   │                           ├── GenericDate.java
│   │                           ├── GenericId.java
│   │                           └── GenericValue.java
│   └── resources
│       ├── application.properties
│       ├── column-mapping.properties
│       ├── logback-spring.xml
│       └── static
└── test
    └── java
        └── com
            └── challenge
                ├── ChallengeApplicationTests.java
                └── parsers
                    ├── Application
                    │   └── UseCases
                    │       ├── Files
                    │       │   ├── MoveFileToPathWithDateSubFolderTest.java
                    │       │   ├── ReadFileAsFileContentDTOTest.java
                    │       │   └── WriteObjectAsJsonFileTest.java
                    │       └── Parsing
                    │           ├── ProcessOrderTest.java
                    │           ├── ProcessProductTest.java
                    │           └── ProcessUserTest.java
                    ├── Infraestructure
                    │   ├── Services
                    │   │   ├── FileProcessingSchedulerTest.java
                    │   │   └── ProcessingServiceTest.java
                    │   └── Web
                    │       └── Controllers
                    │           ├── FileUploadControllerTest.java
                    │           └── ManualExecutionControllerTest.java
                    └── core
                        └── Domain
                            ├── Entities
                            │   ├── OrderTest.java
                            │   ├── ProductTest.java
                            │   └── UserTest.java
                            └── ValueObjects
                                ├── GenericIdTests.java
                                ├── GenericValueTests.java
                                └── GerenicDateTests.java
```
