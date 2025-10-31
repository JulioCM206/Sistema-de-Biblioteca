package Repositorios;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RepositorioEmprestimo {
    private final Map<String, Integer> ContagemDeEmprestimos;

    public RepositorioEmprestimo(){
        ContagemDeEmprestimos = new HashMap<>();
    }

    public void incrementarContagemDeEmprestimo(String ISBN){
        this.ContagemDeEmprestimos.put(ISBN, this.ContagemDeEmprestimos.getOrDefault(ISBN,0) + 1);
    }

    public Map<String, Integer> listarContagem() {
        // Retorna uma visão imutável para proteger o estado interno
        return Collections.unmodifiableMap(ContagemDeEmprestimos);
    }
    public int getTotalEmprestimos() {
        return ContagemDeEmprestimos.values().stream().mapToInt(Integer::intValue).sum();
    }
}
