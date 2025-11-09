package Modelo;
import java.util.*;

public class Usuario {
	private final String nome;
	private  final String id;
	private final List<Emprestimo> historicoEmprestimos;
	public Usuario(String nome, String id) {
		if (nome == null || nome.isBlank()){
			throw new IllegalArgumentException("O nome do usuário não pode estar vazio.");
		}
		if (id == null || id.isBlank()){
			throw new IllegalArgumentException("O ID do usuário não pode estar vazio.");
		}
		this.nome = nome;
		this.id = id;
		this.historicoEmprestimos = new ArrayList<>();
	}
	public String getnome() {
		return nome;
	}

	public String getId() {
		return id;
	}
	public List<Emprestimo> getHistorico() {
		return Collections.unmodifiableList(historicoEmprestimos);
	}
	public void adicionarEmprestimo(Emprestimo emprestimo) {
		if (emprestimo == null){
			throw new IllegalArgumentException("O empréstimo não pode ser nulo");
		}
		this.historicoEmprestimos.add(emprestimo);
	}
}
