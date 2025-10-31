package Test;

import Modelo.Livro;
import Modelo.LivroFisico;
import Repositorios.RepositorioLivro;
import Repositorios.RepositorioEmprestimo;
import Repositorios.RepositorioUsuario;
import Servico.SistemaDeLivros;
import Servico.SistemaDeEmprestimo;
import Servico.Relatorio;
import Servico.SistemaUsuario;

public class BibliotecaTestes {

    public static void main(String[] args) {
        System.out.println("--- INICIANDO TESTES DE INTEGRAÇÃO DO SISTEMA ---");

        // Inicialização de Repositórios
        RepositorioLivro acervoRepo = new RepositorioLivro();
        RepositorioUsuario usuarioRepo = new RepositorioUsuario();
        RepositorioEmprestimo contagemRepo = new RepositorioEmprestimo();

        // Inicialização de Serviços
        SistemaDeLivros acervoService = new SistemaDeLivros(acervoRepo);
        SistemaUsuario usuarioService = new SistemaUsuario(usuarioRepo);
        SistemaDeEmprestimo emprestimoService = new SistemaDeEmprestimo (acervoService, usuarioService, contagemRepo);
        Relatorio relatorioService = new Relatorio(acervoService, contagemRepo);

        // =================================================================
        // 1. TESTE DE CADASTRO
        // =================================================================

        System.out.println("\n[TESTE 1: CADASTROS]");
        try {
            acervoService.cadastrarLivro("1984", "George Orwell", "978-8535914849", 5);
            acervoService.cadastrarLivro("Dom Casmurro", "Machado de Assis", "978-8571838501", 3);
            acervoService.cadastrarLivro("Digital Book", "Author", "999-9999999999", 0);
            usuarioService.cadastrarUsuario("Alice Silva", "U001");
            usuarioService.cadastrarUsuario("Bruno Santos", "U002");
            System.out.println("  -> Cadastro de Livros e Usuários: OK");

            // Tentativa de cadastrar ISBN duplicado (DEVE FALHAR)
            try {
                acervoService.cadastrarLivro("1984 (Duplicado)", "George Orwell", "978-8535914849", 1);
            } catch (IllegalArgumentException e) {
                System.out.println("  -> Teste ISBN duplicado: SUCESSO. (" + e.getMessage() + ")");
            }

        } catch (Exception e) {
            System.out.println("  -> Erro no cadastro inicial: FALHA. " + e.getMessage()); //nao eh pra acontecer
        }

        // =================================================================
        // 2. TESTE DE EMPRÉSTIMO E DISPONIBILIDADE
        // =================================================================

        System.out.println("\n[TESTE 2: EMPRÉSTIMO]");
        try {
            // Empréstimo bem-sucedido
            emprestimoService.realizarEmprestimo("U001", "978-8535914849"); // Alice pega 1984
            emprestimoService.realizarEmprestimo("U002", "978-8535914849"); // Bruno pega 1984
            System.out.println("  -> Empréstimos: OK.");

            // Verifica disponibilidade
            Livro livro1984 = acervoService.buscarLivroPorISBN("978-8535914849");
            if (livro1984 instanceof LivroFisico) {
                LivroFisico lf = (LivroFisico) livro1984;
                if (lf.getQuantidadeDisponivel() == 3) {
                    System.out.println("  -> Disponibilidade 1984 (5 - 2 = 3): OK.");
                } else {
                    System.out.println("  -> FALHA: Disponibilidade esperada 3, obtida " + lf.getQuantidadeDisponivel());
                }
            }

            // Empréstimo Digital
            emprestimoService.realizarEmprestimo("U001", "999-9999999999");
            System.out.println("  -> Empréstimo Digital: OK.");

        } catch (IllegalStateException e) {
            System.out.println("  -> Erro durante empréstimo: FALHA. " + e.getMessage());
        }

        // =================================================================
        // 3. TESTE DE DEVOLUÇÃO
        // =================================================================

        System.out.println("\n[TESTE 3: DEVOLUÇÃO]");
        try {
            emprestimoService.realizarDevolucao("U001", "978-8535914849"); // Alice devolve 1984
            System.out.println("  -> Devolução: OK.");

            // Verifica disponibilidade após devolução
            Livro livro1984 = acervoService.buscarLivroPorISBN("978-8535914849");
            if (livro1984 instanceof LivroFisico) {
                LivroFisico lf = (LivroFisico) livro1984;
                if (lf.getQuantidadeDisponivel() == 4) {
                    System.out.println("  -> Disponibilidade 1984 (3 + 1 = 4): OK.");
                } else {
                    System.out.println("  -> FALHA: Disponibilidade esperada 4, obtida " + lf.getQuantidadeDisponivel());
                }
            }
        } catch (IllegalStateException e) {
            System.out.println("  -> Erro durante devolução: FALHA. " + e.getMessage());
        }

        // =================================================================
        // 4. TESTE DE RELATÓRIO
        // =================================================================
        System.out.println("\n[TESTE 4: RELATÓRIO]");
        // O relatório deve mostrar 1984 com 2 empréstimos (Alice e Bruno), e Digital Book com 1.
        String relatorio = relatorioService.GerarRelatorio();
        System.out.println(relatorio);

        if (relatorio.contains("Total de Empréstimos Registrados: 3")) {
            System.out.println("  -> Contagem Total de Empréstimos: OK.");
        } else {
            System.out.println("  -> FALHA: Contagem Total Incorreta.");
        }

        System.out.println("\n--- FIM DOS TESTES ---");
    }
}
