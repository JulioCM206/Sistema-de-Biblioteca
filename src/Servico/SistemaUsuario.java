package Servico;

import Modelo.Usuario;
import Repositorios.InterfaceRepositorioUsuario;

public class SistemaUsuario {
	private final InterfaceRepositorioUsuario repositorioUsuario;

	public SistemaUsuario(InterfaceRepositorioUsuario repositorioUsuario) {
		this.repositorioUsuario = repositorioUsuario;
	}

	public void cadastrarUsuario(String nome, String ID) throws IllegalArgumentException {
		if(repositorioUsuario.buscarPorID(ID).isPresent()) {
			throw new IllegalArgumentException("ID de usuário já cadastrado.");
		}
		Usuario novoUsuario = new Usuario(nome, ID);
		repositorioUsuario.adicionarUsuario(novoUsuario);
	}

	public Usuario buscarUsuarioPorID(String ID) {
		return repositorioUsuario.buscarPorID(ID).orElse(null);
	}
}
