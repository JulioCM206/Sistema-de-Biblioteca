package Repositorios;

import Modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositorioUsuario implements InterfaceRepositorioUsuario {
	private final List<Usuario> usuarios;

	public RepositorioUsuario() {
		this.usuarios = new ArrayList<>();
	}

	@Override
	public void adicionarUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
	}

	@Override
	public Optional<Usuario> buscarPorID(String id) {
		for (Usuario u : usuarios) {
			if (u.getId().equals(id)) {
				return Optional.of(u);
			}
		}
		return Optional.empty();

	}
}
