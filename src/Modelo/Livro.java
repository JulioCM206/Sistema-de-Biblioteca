package Modelo;
//livro vai ser uma interface ou uma classe abstrata para livros digitais ou fisicos
//faz esse aq sem quantidade de livro definida pra na classe livro fisico ter quantidade e no digtal n
public abstract class Livro {
private final String titulo;
private final String autor;
private final String ISBN;

public Livro(String titulo, String autor, String ISBN) {
    this.titulo = titulo;
    this.autor = autor;
    this.ISBN = ISBN;
}
public String getTitulo() {
    return titulo;
}

public String getAutor() {
    return autor;
}

public String getISBN() {
    return ISBN;
}
}
