package Servico;

import Modelo.Emprestimo;
import Modelo.Livro;
import Modelo.LivroDigital;
import Modelo.LivroFisico;
import Modelo.Usuario;
import Repositorios.InterfaceRepositorioEmprestimo;

public class SistemaDeEmprestimo {
	private final SistemaDeLivros sistemaLivros;
	private final SistemaUsuario sistemaUsuario;
	private final InterfaceRepositorioEmprestimo contagemRepo;

	public SistemaDeEmprestimo(SistemaDeLivros sistemaDeLivros, SistemaUsuario sistemaDeUsuario,  InterfaceRepositorioEmprestimo contagemRepo) {
		this.sistemaLivros = sistemaDeLivros;
		this.sistemaUsuario = sistemaDeUsuario;
		this.contagemRepo = contagemRepo;
	}
	public Emprestimo realizarEmprestimo(String UsuarioID, String livroISBN){

		Usuario usuario = sistemaUsuario.buscarUsuarioPorID(UsuarioID);
		Livro livro = sistemaLivros.buscarLivroPorISBN(livroISBN);
		if(livro == null ){
			throw new IllegalStateException("Livro nao encontrado no Sistema");
		}
		if(usuario == null){
			throw new IllegalStateException("Usuário nao encontrado no Sistema");
		}
		boolean disponivel = false;
		if(livro instanceof LivroFisico){
			disponivel = ((LivroFisico) livro).Emprestar();
		} else if(livro instanceof LivroDigital){
			disponivel = true;
		}
		if(!disponivel){
			throw new IllegalStateException("Falta de estoque do livro "+ livro.getISBN() + "no sistema");
		}
		Emprestimo novoEmprestimo = new Emprestimo(usuario, livro);
		usuario.adicionarEmprestimo(novoEmprestimo);
		contagemRepo.incrementarContagemDeEmprestimo(livroISBN);
		return novoEmprestimo;
	}
	public Emprestimo realizarDevolucao(String UsuarioID, String livroISBN){
		Usuario usuario = sistemaUsuario.buscarUsuarioPorID(UsuarioID);
		if(usuario == null){
			throw new IllegalStateException("Usuário nao encontrado no Sistema");
		}
		Emprestimo emprestimoAtivo = usuario.getHistorico().stream()
		.filter(e -> e.getLivro().getISBN().equals(livroISBN) && e.estaEmAndamento())
		.findFirst()
		.orElse(null);

		if(emprestimoAtivo == null){
			throw new IllegalStateException("Nenhum empréstimo ativo encontrado para este livro e usuário.");
		}

		emprestimoAtivo.registrarDevolucao();

		Livro livro = emprestimoAtivo.getLivro();
		if(livro instanceof LivroFisico){
		((LivroFisico) livro).devolver();
		}

		return emprestimoAtivo;
	}
}
