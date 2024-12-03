# Luiza Labs - Android Test

Este projeto implementa um aplicativo Android que consulta a API do GitHub para exibir os repositórios mais populares em Java, juntamente com os pull requests associados a cada repositório. O aplicativo utiliza as tecnologias e ferramentas mencionadas no enunciado do teste, incluindo Retrofit, OkHttp, Dagger2 (Hilt), RxJava, Glide, e testes unitários.

---

## Tecnologias Utilizadas

- **Kotlin**: Linguagem principal utilizada para o desenvolvimento.
- **Retrofit**: Utilizado para fazer chamadas HTTP à API do GitHub.
- **OkHttp**: Biblioteca utilizada para otimizar as requisições HTTP.
- **RxJava**: Implementa programação reativa para gerenciar fluxos assíncronos e eventos.
- **Dagger2 (Hilt)**: Implementa Injeção de Dependência (IoC) para uma arquitetura mais escalável e modular.
- **Glide**: Biblioteca para cache e carregamento de imagens.
- **JUnit & Mockk**: Frameworks utilizados para testes unitários e mocks.
- **Espresso**: Utilizado para testar a interface do usuário (UI).
- **Material Design**: Para garantir que a interface do usuário siga as diretrizes do Google.

---

## Funcionalidades

### Lista de Repositórios
- A tela inicial exibe uma lista dos repositórios mais populares com base na API do GitHub.
- Cada item na lista exibe:
  - Nome do repositório
  - Descrição do repositório
  - Nome e foto do autor
  - Número de estrelas e forks
- A página de repositórios implementa **scroll infinito** (endless scroll), carregando automaticamente a próxima página quando o usuário chega ao final da lista.

### Pull Requests de Repositórios
- Ao tocar em um repositório, a lista de pull requests do repositório é exibida.
- Cada item da lista de pull requests exibe:
  - Nome e foto do autor do PR
  - Título do PR
  - Data de criação do PR
  - Corpo do PR
- Ao tocar em um PR, o aplicativo abre a página do Pull Request no navegador.

---

## Arquitetura

A arquitetura foi projetada para ser escalável e modular, utilizando práticas recomendadas para o desenvolvimento Android:

- **MVVM (Model-View-ViewModel)**: Seguindo o padrão MVVM para separar a lógica de negócios da interface do usuário, facilitando a manutenção e testabilidade.
- **Repositórios**: Cada módulo de dados utiliza Retrofit para a comunicação com a API e Glide para o carregamento de imagens.
- **Injeção de Dependências**: Utilizamos o Hilt para facilitar a injeção de dependências, promovendo um código desacoplado e modular.

---

## Funcionalidades Extras

- **Cache de Imagens**: O Glide é utilizado para garantir o cache de imagens dos usuários e repositórios, melhorando a performance e a experiência do usuário.
- **Cache da API**: As respostas da API são armazenadas localmente para garantir que os dados sejam carregados de forma mais rápida em casos de conexões lentas ou quedas de rede.

---

## Testes

### Testes Unitários
- Utilizamos o framework JUnit para testes unitários.
- **Mockk** foi usado para mockar as dependências e facilitar os testes.
- Os testes verificam o comportamento dos ViewModels e Repositórios.

### Testes de UI
- **Espresso** foi utilizado para automatizar os testes de UI.
- Os testes verificam se a lista de repositórios e pull requests são exibidas corretamente e se a navegação entre telas está funcionando conforme esperado.

---

## Como Rodar o Projeto

1. **Clone o repositório**:
    ```bash
    git clone https://github.com/andersonms92/LLAndroidTest.git
    ```

2. **Abra o projeto no Android Studio**.
3. **Sincronize o Gradle** (Android Studio geralmente faz isso automaticamente ao abrir o projeto).
4. **Construa o projeto e execute no seu dispositivo Android ou no emulador**.

### Para rodar os testes:
```bash
./gradlew test
