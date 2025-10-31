package Modelo;

public class LivroFisico extends Livro {
    private int quantidadeTotal;
    private int quantidadeDisponivel;

    public LivroFisico(String titulo, String autor, String ISBN, int quantidadeTotal) {
        super(titulo, autor, ISBN);
        this.quantidadeTotal = quantidadeTotal;
        this.quantidadeDisponivel = quantidadeTotal;
    }
    public int getQuantidadeTotal() {
        return quantidadeTotal;
    }
    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }
    public boolean Emprestar(){
        if(quantidadeDisponivel > 0){
            this.quantidadeDisponivel--;
            return true;
        }
        return false;
    }
    public void devolver(){
            this.quantidadeDisponivel++;
    }
    public void AdicionarCopias(int copias){
        if (copias > 0) {
            this.quantidadeTotal += copias;
            this.quantidadeDisponivel += copias;
        }
    }
}
