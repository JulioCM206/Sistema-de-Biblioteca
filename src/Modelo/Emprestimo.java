package Modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Emprestimo {
    private final Usuario usuario;
    private final Livro livro;
    private final LocalDate dataEmprestimo;
    private final LocalDate dataPrevista;
    private LocalDate dataDevolucao;
    static int PrazoEmprestimoPadrao = 14;

    public Emprestimo(Usuario usuario, Livro livro) {
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = LocalDate.now();
        this.dataPrevista = this.dataEmprestimo.plusDays(PrazoEmprestimoPadrao); //data de quando deve entregar o livro de volta
    }
    public void registrarDevolucao() {
        this.dataDevolucao = LocalDate.now();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataPrevista() {
        return dataPrevista;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public boolean estaEmAndamento() {
        return dataDevolucao == null;
    }
}
