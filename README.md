# Sistema-de-Biblioteca

### Repositório contendo o projeto de Boas Práticas de Programação - Sistema de Biblioteca Simplificado  
Professor: Gibeon Soares

---

## Alunos:
- Glauco Sales Paiva Virginio  
- Julio César Medeiros de Araujo  

---

## 📘 Explicação do Projeto

Este projeto simula um **sistema de gerenciamento de uma biblioteca em Java**.  
O sistema permite o controle completo de livros, usuários e operações de empréstimo e devolução, com suporte a livros físicos e digitais.

### Funcionalidades Principais:
1. **Cadastro de Livros**  
   Usando título, autor, ISBN e quantidade de cópias (apenas para livros físicos).

2. **Cadastro de Usuários**  
   Registro de nome e ID único.

3. **Empréstimo de Livro**  
   Verificação de disponibilidade (para livros físicos) e registro do empréstimo no histórico do usuário.

4. **Devolução de Livro**  
   Atualização do acervo com a devolução e registro da data de devolução.

5. **Listagem de Livros**  
   Exibição de todos os livros cadastrados, indicando a quantidade disponível.

6. **Relatório de Empréstimos**  
   Geração de um relatório com os livros mais emprestados e o total de empréstimos realizados.

---

## 🗂️ Estrutura do Projeto

src/

├── Aplicacao/

├── Modelo/

├── Repositorios/

└── Servico/

---

## ⚙️ Instruções para Compilar o Sistema

### Passos:

1. Entre na pasta do projeto:

   ```
   bash
   cd src
   ``` 

2. Compile todos os arquivos ```.java```:

    ```
    javac Aplicacao/BibliotecaApp.java
    ```

---

## ▶️ Instruções para Executar o Sistema

Após compilar, execute o programa com:

```
java Aplicacao.BibliotecaApp
```
