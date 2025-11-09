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
import java.util.Objects;
import java.util.Scanner;

public class BibliotecaApp {

	private final Scanner scanner;
	private final SistemaDeLivros acervoService;
	private final SistemaUsuario usuarioService;
	private final SistemaDeEmprestimo emprestimoService;
	private final Relatorio relatorioService;

	public BibliotecaApp(Scanner scanner) {
		this.scanner = Objects.requireNonNull(scanner, "Scanner não pode ser nulo.");

		RepositorioLivro acervoRepo = new RepositorioLivro();
		RepositorioUsuario usuarioRepo = new RepositorioUsuario();
		RepositorioEmprestimo contagemRepo = new RepositorioEmprestimo();

		this.acervoService = new SistemaDeLivros(acervoRepo);
		this.usuarioService = new SistemaUsuario(usuarioRepo);
		this.emprestimoService = new SistemaDeEmprestimo(acervoService, usuarioService, contagemRepo);
		this.relatorioService = new Relatorio(acervoService, contagemRepo);
	}

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			BibliotecaApp app = new BibliotecaApp(scanner);
			app.inicializarDadosSimulados();
			app.executarMenu();
		} catch (Exception e) {
			System.err.println("Erro fatal: " + e.getMessage());
		}
	}

	private void inicializarDadosSimulados() {
		try {
			acervoService.cadastrarLivro("O Senhor dos Anéis", "J.R.R. Tolkien", "978-8595700813", 10);
			acervoService.cadastrarLivro("Sapiens", "Yuval Noah Harari", "978-8535914849", 5);
			acervoService.cadastrarLivro("Código Limpo", "Robert C. Martin", "978-8576059960", 3);
			acervoService.cadastrarLivro("Padrões de Projeto (Digital)", "Erich Gamma et al.", "999-0000000001", 0);

			usuarioService.cadastrarUsuario("João Dev", "1001");
			usuarioService.cadastrarUsuario("Maria Leitora", "1002");

			System.out.println("Dados iniciais carregados com sucesso.");
		} catch (Exception e) {
			System.err.println("Falha ao inicializar dados: " + e.getMessage());
		}
	}

	private void executarMenu() {
		int opcao;
		do {
			exibirMenu();
			opcao = lerInteiro("Escolha uma opção: ");

			try {
				switch (opcao) {
					case 1 -> cadastrarLivro();
					case 2 -> cadastrarUsuario();
					case 3 -> listarLivros();
					case 4 -> realizarEmprestimo();
					case 5 -> realizarDevolucao();
					case 6 -> gerarRelatorio();
					case 0 -> System.out.println("Encerrando o sistema...");
					default -> System.out.println("Opção inválida. Tente novamente.");
				}
			} catch (IllegalArgumentException e) {
				System.err.println("Entrada inválida: " + e.getMessage());
			} catch (Exception e) {
				System.err.println("Erro durante a operação: " + e.getMessage());
			}
		} while (opcao != 0);
	}

	private void exibirMenu() {
		System.out.println("\n--- SISTEMA DE BIBLIOTECA ---");
		System.out.println("1. Cadastrar Livro");
		System.out.println("2. Cadastrar Usuário");
		System.out.println("3. Listar Livros");
		System.out.println("4. Realizar Empréstimo");
		System.out.println("5. Realizar Devolução");
		System.out.println("6. Gerar Relatório");
		System.out.println("0. Sair");
	}

	private void cadastrarLivro() {
		System.out.println("\n--- CADASTRO DE LIVRO ---");
		String titulo = lerTexto("Título: ");
		String autor = lerTexto("Autor: ");
		String isbn = lerTexto("ISBN: ");
		int quantidade = lerInteiro("Quantidade de Cópias (0 = Digital): ");

		if (titulo.isBlank() || autor.isBlank() || isbn.isBlank()) {
			throw new IllegalArgumentException("Título, autor e ISBN são obrigatórios.");
		}
		if (quantidade < 0) {
			throw new IllegalArgumentException("Quantidade não pode ser negativa.");
		}

		acervoService.cadastrarLivro(titulo, autor, isbn, quantidade);
		System.out.printf("Livro '%s' cadastrado com sucesso.%n", titulo);
	}

	private void cadastrarUsuario() {
		System.out.println("\n--- CADASTRO DE USUÁRIO ---");
		String nome = lerTexto("Nome: ");
		String id = lerTexto("ID único: ");

		if (nome.isBlank() || id.isBlank()) {
			throw new IllegalArgumentException("Nome e ID são obrigatórios.");
		}

		usuarioService.cadastrarUsuario(nome, id);
		System.out.printf("Usuário '%s' cadastrado com sucesso.%n", nome);
	}

	private void listarLivros() {
		System.out.println("\n--- ACERVO DA BIBLIOTECA ---");
		List<Livro> livros = acervoService.listarLivro();

		if (livros.isEmpty()) {
			System.out.println("O acervo está vazio.");
			return;
		}

		for (Livro livro : livros) {
			String tipo = (livro instanceof LivroFisico lf)
			? String.format("Físico | Disponível: %d/%d", lf.getQuantidadeDisponivel(), lf.getQuantidadeTotal())
			: "Digital | Sempre disponível";

			System.out.printf("- %s | %s | ISBN: %s | %s%n",
				livro.getTitulo(), livro.getAutor(), livro.getISBN(), tipo);
		}
	}

	private void realizarEmprestimo() {
		System.out.println("\n--- REALIZAR EMPRÉSTIMO ---");
		String userId = lerTexto("ID do Usuário: ");
		String isbn = lerTexto("ISBN do Livro: ");
		emprestimoService.realizarEmprestimo(userId, isbn);
		System.out.println("Empréstimo registrado com sucesso.");
	}

	private void realizarDevolucao() {
		System.out.println("\n--- REALIZAR DEVOLUÇÃO ---");
		String userId = lerTexto("ID do Usuário: ");
		String isbn = lerTexto("ISBN do Livro: ");
		emprestimoService.realizarDevolucao(userId, isbn);
		System.out.println("Devolução registrada com sucesso.");
	}

	private void gerarRelatorio() {
		System.out.println(relatorioService.gerarRelatorio());
	}

	private String lerTexto(String mensagem) {
		System.out.print(mensagem);
		return scanner.nextLine().trim();
	}

	private int lerInteiro(String mensagem) {
		while (true) {
			System.out.print(mensagem);
			if (scanner.hasNextInt()) {
				int valor = scanner.nextInt();
				scanner.nextLine();
				return valor;
			}
			System.out.println("Entrada inválida. Digite um número inteiro.");
			scanner.nextLine();
		}
	}
}
