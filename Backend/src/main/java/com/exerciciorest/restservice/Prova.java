package com.exerciciorest.restservice;

public class Prova {
    private final int id;
    private final int idAluno;
    private final float nota;

    public Prova(int id, int idAluno, float nota) {
        this.id = id;
        this.idAluno = idAluno;
        this.nota = nota;
    }
    public int getId()
    {return id;}
    public int getIdAluno()
    {return idAluno;}
    public float getNota()
    {return nota;}
}