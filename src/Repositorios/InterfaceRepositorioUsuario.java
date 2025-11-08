package Repositorios;

import Modelo.Usuario;
import java.util.Optional;

public interface InterfaceRepositorioUsuario {
	void adicionarUsuario(Usuario usuario);
	Optional<Usuario> buscarPorID(String id);
}
