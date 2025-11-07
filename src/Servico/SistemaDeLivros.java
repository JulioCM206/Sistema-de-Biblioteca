package Servico;
import Modelo.Livro;
import Modelo.LivroFisico;
import Modelo.LivroDigital;
// import Repositorios.RepositorioLivro;
import java.util.List;
import Repositorios.InterfaceRepositorioLivro; //tirar import do repositorio depois

public class SistemaDeLivros {
    private final InterfaceRepositorioLivro repositorioLivro;

    public SistemaDeLivros(InterfaceRepositorioLivro repositorioLivro) {
        this.repositorioLivro = repositorioLivro;
    }

    //Cadastro
    public void cadastrarLivro(String titulo, String autor, String ISBN, int quantidade) throws IllegalArgumentException {
        Livro livroExistente = buscarLivroPorISBN(ISBN);

        if (livroExistente != null) {
            if (livroExistente instanceof LivroFisico livrofisico) {
                livrofisico.AdicionarCopias(quantidade);
                return;
            }
        }

        Livro novoLivro = (quantidade > 0)
            ? new LivroFisico(titulo, autor, ISBN, quantidade) : new LivroDigital(titulo, autor, ISBN);
        repositorioLivro.adicionar(novoLivro);
    }

    public List<Livro> listarLivro() {
        return repositorioLivro.ListarRepositorioLivro();
    }

    public Livro buscarLivroPorISBN(String ISBN) {
        return repositorioLivro.buscarPorISBN(ISBN).orElseThrow(() -> new IllegalArgumentException("Livro com ISBN " + ISBN + " não encontrado."));
    }
}
