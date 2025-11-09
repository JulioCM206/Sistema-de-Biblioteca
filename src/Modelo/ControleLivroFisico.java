public interface ControleLivroFisico {
    void AdicionarCopias(int copias);
    boolean podeEmprestar();
    void registrarEmprestimo();
    void registrarDevolucao();
}
