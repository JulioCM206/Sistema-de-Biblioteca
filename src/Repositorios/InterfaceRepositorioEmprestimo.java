package Repositorios;

import java.util.Map;

public interface InterfaceRepositorioEmprestimo {
    void incrementarContagemDeEmprestimo(String ISBN);
    Map<String, Integer> listarContagem();
    int getTotalEmprestimos();
}
