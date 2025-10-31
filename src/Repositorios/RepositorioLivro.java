package Repositorios;
import Modelo.Livro;
import java.util.List;
import java.util.ArrayList;

public class RepositorioLivro {
    private final List <Livro> livros;

    public RepositorioLivro(){
        this.livros = new ArrayList<>();
    }
    public void adicionar(Livro livro){
        this.livros.add(livro);
    }

    public Livro buscarPorISBN(String ISBN){
        for (Livro livro : this.livros) {
            if (livro.getISBN().equals(ISBN)){
                return livro;
            }
        }
        return null;
    }
     public List<Livro> ListarRepositorioLivro(){
        return new ArrayList<>(this.livros);
     }
}
