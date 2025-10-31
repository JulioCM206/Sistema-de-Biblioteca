package Repositorios;

import Modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuario  {
    private final List<Usuario> usuarios;

    public RepositorioUsuario() {
        this.usuarios = new ArrayList<>();
    }
    public void adicionarUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
    }

    public Usuario buscarPorID(String ID) {
        for (Usuario u : usuarios) {
            if (u.getID().equals(ID)) {
                return u;
            }
        }
        return null;

    }
//    public List<Usuario> ListarRepositorioUsuario() {
//        return new ArrayList<>(this.usuarios);
//    }
}
