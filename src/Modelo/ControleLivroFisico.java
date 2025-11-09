package Modelo;

public interface ControleLivroFisico {
	void adicionarCopias(int copias);
	boolean podeEmprestar();
	void registrarEmprestimo();
	void registrarDevolucao();
}
