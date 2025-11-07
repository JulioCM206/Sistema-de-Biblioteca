package Repositorios;

import Modelo.Livro;
import java.util.List;
import java.util.Optional;

public interface InterfaceRepositorioLivro {
    void adicionar(Livro livro);
    Optional<Livro> buscarPorISBN(String ISBN);
    List<Livro> ListarRepositorioLivro();
}
