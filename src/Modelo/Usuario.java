package Modelo;
import java.util.ArrayList;

public class Usuario {
    private final String nome;
    private  final String ID;
    private final ArrayList<Emprestimo> HistoricoDoUsuario;
    public Usuario(String nome, String ID) {
        this.nome = nome;
        this.ID = ID;
        this.HistoricoDoUsuario = new ArrayList<>();
    }
//    public String getnome() {
//        return nome;
//    }

    public String getID() {
        return ID;
    }
    public ArrayList<Emprestimo> getHistorico() {
        return HistoricoDoUsuario;
    }
    // Método para adicionar um empréstimo ao histórico
    public void adicionarEmprestimo(Emprestimo emprestimo) {
        this.HistoricoDoUsuario.add(emprestimo);
    }
}
