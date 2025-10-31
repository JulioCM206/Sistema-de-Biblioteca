package Servico;

import Modelo.Usuario;
import Repositorios.RepositorioUsuario;

public class SistemaUsuario {
    private final RepositorioUsuario repositorioUsuario;

    public SistemaUsuario(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    public void cadastrarUsuario(String nome, String ID) throws IllegalArgumentException {
        if(repositorioUsuario.buscarPorID(ID) != null) {
            throw new IllegalArgumentException("ID de usuário já cadastrado.");
        }
        Usuario novoUsuario = new Usuario(nome, ID);
        repositorioUsuario.adicionarUsuario(novoUsuario);
    }

    public Usuario buscarUsuarioPorID(String ID) {
        return repositorioUsuario.buscarPorID(ID);
    }
/*
    public List<Usuario> listarUsuarios() {
        return repositorioUsuario.ListarRepositorioUsuario();
    }
*/
}
