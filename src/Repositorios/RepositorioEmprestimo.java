package Repositorios;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RepositorioEmprestimo implements InterfaceRepositorioEmprestimo{
	private final Map<String, Integer> ContagemDeEmprestimos;

	public RepositorioEmprestimo(){
		ContagemDeEmprestimos = new HashMap<>();
	}

	@Override
	public void incrementarContagemDeEmprestimo(String isbn){
		this.ContagemDeEmprestimos.put(isbn, this.ContagemDeEmprestimos.getOrDefault(isbn,0) + 1);
	}

	@Override
	public Map<String, Integer> listarContagem() {
		return Collections.unmodifiableMap(ContagemDeEmprestimos);
	}

	@Override
	public int getTotalEmprestimos() {
		return ContagemDeEmprestimos.values().stream().mapToInt(Integer::intValue).sum();
	}
}
