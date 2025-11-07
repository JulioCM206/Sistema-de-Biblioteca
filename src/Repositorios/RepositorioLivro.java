package Repositorios;
import Modelo.Livro;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

public class RepositorioLivro implements InterfaceRepositorioLivro {
    private final List <Livro> livros;

    public RepositorioLivro(){
        this.livros = new ArrayList<>();
    }

    @Override
    public void adicionar(Livro livro){
        this.livros.add(livro);
    }

    @Override
    public Optional<Livro> buscarPorISBN(String ISBN){
        for (Livro livro : this.livros) {
            if (livro.getISBN().equals(ISBN)){
                return Optional.of(livro);
            }
        }
        return Optional.empty();
    }

    @Override
     public List<Livro> ListarRepositorioLivro(){
        return new ArrayList<>(this.livros);
     }
}
