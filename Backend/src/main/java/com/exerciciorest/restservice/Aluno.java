package com.exerciciorest.restservice;

public class Aluno {
    private final int id;
    private final String name;

    public Aluno(int id, String content) {
        this.id = id;
        this.name = content;
    }
    public int getId() {return id;}
    public String getName() {return name;}
}