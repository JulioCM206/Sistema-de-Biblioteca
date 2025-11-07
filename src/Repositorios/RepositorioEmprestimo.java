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
    public void incrementarContagemDeEmprestimo(String ISBN){
        this.ContagemDeEmprestimos.put(ISBN, this.ContagemDeEmprestimos.getOrDefault(ISBN,0) + 1);
    }

    @Override
    public Map<String, Integer> listarContagem() {
        // Retorna uma visão imutável para proteger o estado interno
        return Collections.unmodifiableMap(ContagemDeEmprestimos);
    }

    @Override
    public int getTotalEmprestimos() {
        return ContagemDeEmprestimos.values().stream().mapToInt(Integer::intValue).sum();
    }
}
