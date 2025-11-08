package Repositorios;

import java.util.Map;

public interface InterfaceRepositorioEmprestimo {
	void incrementarContagemDeEmprestimo(String isbn);
	Map<String, Integer> listarContagem();
	int getTotalEmprestimos();
}
