package Modelo;

public class LivroFisico extends Livro {
	private int quantidadeTotal;
	private int quantidadeDisponivel;

	public LivroFisico(String titulo, String autor, String isbn, int quantidadeTotal) {
		super(titulo, autor, isbn);
		if (quantidadeTotal < 0){
			throw new IllegalArgumentException("A quantidade total não pode ser negativa.");
		}
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
		if (quantidadeDisponivel < quantidadeTotal){
			this.quantidadeDisponivel++;
		}
	}
	public void AdicionarCopias(int copias){
		if (copias <= 0){
			throw new IllegalArgumentException("Número de copias deve ser um número positivo.");
		}
		this.quantidadeTotal += copias;
		this.quantidadeDisponivel += copias;
	}
}
