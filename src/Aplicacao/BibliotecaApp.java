package Aplicacao;

import Modelo.Livro;
import Modelo.LivroFisico;
import Repositorios.RepositorioLivro;
import Repositorios.RepositorioUsuario;
import Repositorios.RepositorioEmprestimo;
import Servico.SistemaDeLivros;
import Servico.SistemaDeEmprestimo;
import Servico.Relatorio;
import Servico.SistemaUsuario;

import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {

    private static final Scanner scanner = new Scanner(System.in);

    private static final RepositorioLivro acervoRepo = new RepositorioLivro();
    private static final RepositorioUsuario usuarioRepo = new RepositorioUsuario();
    private static final RepositorioEmprestimo contagemRepo = new RepositorioEmprestimo();

    private static final SistemaDeLivros acervoService = new SistemaDeLivros(acervoRepo);
    private static final SistemaUsuario usuarioService = new SistemaUsuario(usuarioRepo);
    private static final SistemaDeEmprestimo emprestimoService = new SistemaDeEmprestimo(acervoService, usuarioService, contagemRepo);
    private static final Relatorio relatorioService = new Relatorio(acervoService, contagemRepo);

    static void main() {
        inicializarDadosSimulados();
        menuPrincipal();
    }

    private static void inicializarDadosSimulados() {
        try {
            acervoService.cadastrarLivro("O Senhor dos Anéis", "J.R.R. Tolkien", "978-8595700813", 10);
            acervoService.cadastrarLivro("Sapiens", "Yuval Noah Harari", "978-8535914849", 5);
            acervoService.cadastrarLivro("Código Limpo", "Robert C. Martin", "978-8576059960", 3);
            acervoService.cadastrarLivro("Padrões de Projeto (Digital)", "Erich Gamma et al.", "999-0000000001", 0);

            usuarioService.cadastrarUsuario("Joao Dev", "1001");
            usuarioService.cadastrarUsuario("Maria Leitora", "1002");

            System.out.println("Dados iniciais carregados.");
        } catch (Exception e) {
            System.out.println("Erro ao inicializar dados: " + e.getMessage());
        }
    }

    private static void menuPrincipal() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n\n--- SISTEMA DE BIBLIOTECA ---");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Cadastrar Usuário");
            System.out.println("3. Listar Livros (Acervo)");
            System.out.println("4. Realizar Empréstimo");
            System.out.println("5. Realizar Devolução");
            System.out.println("6. Gerar Relatório de Empréstimos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();

                try {
                    switch (opcao) {
                        case 1: cadastrarLivro(); break;
                        case 2: cadastrarUsuario(); break;
                        case 3: listarLivros(); break;
                        case 4: realizarEmprestimo(); break;
                        case 5: realizarDevolucao(); break;
                        case 6: gerarRelatorio(); break;
                        case 0: System.out.println("Encerrando o sistema . . ."); break;
                        default: System.out.println("Opção inválida. Tente novamente.");
                    }
                } catch (Exception e) {
                    System.err.println("ERRO NA OPERAÇÃO: " + e.getMessage());
                }
            } else {
                System.out.println("Entrada inválida. Digite um número.");
                scanner.nextLine();
            }
        }
    }


    private static void cadastrarLivro() {
        System.out.println("\n--- CADASTRO DE LIVRO ---");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Quantidade de Cópias (0 para Digital): ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        acervoService.cadastrarLivro(titulo, autor, isbn, quantidade);
        System.out.println("Livro '" + titulo + "' cadastrado/Atualizado com sucesso!");
    }


    private static void cadastrarUsuario() {
        System.out.println("\n--- CADASTRO DE USUÁRIO ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("ID Único: ");
        String id = scanner.nextLine();

        usuarioService.cadastrarUsuario(nome, id);
        System.out.println("Usuário '" + nome + "' (ID: " + id + ") cadastrado com sucesso!");
    }


    private static void listarLivros() {
        System.out.println("\n--- ACERVO DA BIBLIOTECA ---");
        List<Livro> livros = acervoService.listarLivro();

        if (livros.isEmpty()) {
            System.out.println("O acervo está vazio.");
            return;
        }

        for (Livro livro : livros) {
            String disponibilidade;
            if (livro instanceof LivroFisico lf) {
                disponibilidade = String.format("Físico | Disponível: %d/%d",
                        lf.getQuantidadeDisponivel(),
                        lf.getQuantidadeTotal());
            } else {
                disponibilidade = "Digital | Sempre Disponível";
            }
            System.out.printf("- Título: %s | Autor: %s | ISBN: %s | Tipo: %s%n",
                    livro.getTitulo(), livro.getAutor(), livro.getISBN(), disponibilidade);
        }
    }

    // FUNÇÃO 3: Realizar Empréstimo
    private static void realizarEmprestimo() {
        System.out.println("\n--- REALIZAR EMPRÉSTIMO ---");
        System.out.print("ID do Usuário: ");
        String userId = scanner.nextLine();
        System.out.print("ISBN do Livro: ");
        String isbn = scanner.nextLine();

        emprestimoService.realizarEmprestimo(userId, isbn);
        System.out.println("Empréstimo registrado com sucesso!");
    }

    // FUNÇÃO 4: Realizar Devolução
    private static void realizarDevolucao() {
        System.out.println("\n--- REALIZAR DEVOLUÇÃO ---");
        System.out.print("ID do Usuário: ");
        String userId = scanner.nextLine();
        System.out.print("ISBN do Livro a ser devolvido: ");
        String isbn = scanner.nextLine();

        emprestimoService.realizarDevolucao(userId, isbn);
        System.out.println("Devolução registrada com sucesso!");
    }

    // FUNÇÃO 6: Gerar Relatório
    private static void gerarRelatorio() {
        System.out.println(relatorioService.GerarRelatorio());
    }
}