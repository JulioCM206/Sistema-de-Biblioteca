package Modelo;
public abstract class Livro {
	private final String titulo;
	private final String autor;
	private final String isbn;

	public Livro(String titulo, String autor, String isbn) {
		if (titulo == null || titulo.isBlank()){
			throw new IllegalArgumentException("Título não pode ser nulo!");
		}
		if (autor == null || autor.isBlank()){
			throw new IllegalArgumentException("Autor não pode ser nulo!");
		}
		if (isbn == null || isbn.isBlank()){
			throw new IllegalArgumentException("ISBN não pode ser nulo!");
		}
		this.titulo = titulo;
		this.autor = autor;
		this.isbn = isbn;
	}
	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public String getISBN() {
		return isbn;
	}
}
