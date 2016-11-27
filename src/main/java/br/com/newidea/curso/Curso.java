package br.com.newidea.curso;

import java.io.Serializable;

/**
 * Created by fabio on 27/11/16.
 */

public class Curso implements Serializable {

    private int idCurso;
    private String nome;
    private String detalhe;

    public Curso() {
        setIdCurso(0);
        setNome("");
        setDetalhe("");
    }

    public Curso(int idCurso, String nome, String detalhe) {
        setIdCurso(idCurso);
        setNome(nome);
        setDetalhe(detalhe);
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDetalhe() {
        return this.detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }

    public String getFoto() {
        return "curso_" + Integer.toString(idCurso);
    }

}
