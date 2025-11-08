package Servico;
import Modelo.Livro;
import Repositorios.RepositorioEmprestimo;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Relatorio {
	private final SistemaDeLivros sistemaDeLivros;
	private final RepositorioEmprestimo sistemaDeEmprestimo;

	public Relatorio(SistemaDeLivros sistemaDeLivros,  RepositorioEmprestimo sistemaDeEmprestimo) {
		this.sistemaDeLivros = sistemaDeLivros;
		this.sistemaDeEmprestimo = sistemaDeEmprestimo;
	}

	public String GerarRelatorio() {
		Map<String, Integer> contagemDeEmprestimosPorLivro = sistemaDeEmprestimo.listarContagem();
		int totalEmprestimos = sistemaDeEmprestimo.getTotalEmprestimos();

		List<Map.Entry<String , Integer>> listaOrdenada = contagemDeEmprestimosPorLivro.entrySet()
		.stream()
		.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
		.toList();
		StringBuilder relatorio = new StringBuilder();
		relatorio.append("--- RELATÓRIO  DE EMPRÉSTIMOS ---\n");
		relatorio.append("Numero Empréstimos por Livro:\n");

		for (Map.Entry<String , Integer> entry : listaOrdenada) {
			Livro livro = sistemaDeLivros.buscarLivroPorISBN(entry.getKey());
			String titulo;
			if (livro != null) {
				titulo = livro.getTitulo();
			} else {
				titulo = "[Livro Não Encontrado]";
			}
			relatorio.append(String.format("  - %s (ISBN: %s) | Empréstimos: %d\n", titulo, entry.getKey(), entry.getValue()));
		}
		relatorio.append("\nQuantidade Total de Empréstimos Registrados: ").append(totalEmprestimos).append("\n");
		relatorio.append("----------------------------------------------");

		return relatorio.toString();
	}
}
