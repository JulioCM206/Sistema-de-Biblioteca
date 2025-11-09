package Modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Emprestimo {
	private final Usuario usuario;
	private final Livro livro;
	private final LocalDate dataEmprestimo;
	private LocalDate dataDevolucao;

	public Emprestimo(Usuario usuario, Livro livro) {
		if (usuario == null){
			throw new IllegalArgumentException("Usuário não pode ser nulo.");
		}
		if (livro == null){
			throw new IllegalArgumentException("O livro não pode ser nulo.");
		}
		this.usuario = usuario;
		this.livro = livro;
		this.dataEmprestimo = LocalDate.now(); 
	}
	public void registrarDevolucao() {
		if (dataDevolucao != null){
			throw new IllegalStateException("Empréstimo já foi devolvido!");
		}
		this.dataDevolucao = LocalDate.now();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Livro getLivro() {
		return livro;
	}

	public LocalDate getDataEmprestimo() {
		return dataEmprestimo;
	}

	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}

	public boolean estaEmAndamento() {
		return dataDevolucao == null;
	}
}
