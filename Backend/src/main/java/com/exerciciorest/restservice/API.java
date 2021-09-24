package com.exerciciorest.restservice;

import java.sql.*;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.*;

@RestController
public class API {
    final String url = "jdbc:postgresql://localhost/tests";
    final String username = "ithiro";
    final String password = "1r0";

    Connection db;

    public boolean connect() {
        try {
            db = DriverManager.getConnection(url, username, password);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean disconnect() {
        try {
            db.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public API () {
        for(int i = 0; i < 100; ++i) {
            if (connect()) {
                return;
            }
        }
    }
    @GetMapping("/alunos")
    public ArrayList<Aluno> alunos() {
        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("select * from alunos");

            ArrayList<Aluno> alunos = new ArrayList<>();
            while (rs.next()) {
                Aluno aluno = new Aluno(rs.getInt(1), rs.getString(2));
                alunos.add(aluno);
            }
            return alunos;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    @PostMapping("/alunos/cadastrar")
    public boolean cadastrarAluno(@RequestParam(value="nome") String nome) {
        try {
            Statement st = db.createStatement();
            st.executeUpdate("insert into alunos (name) values ('"+nome+"')");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    @GetMapping("/alunos/maiornota")
    public ArrayList<Object> maiorNota() {
        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("""
                select a.name, p.max from
                alunos as a
                join
                (
                    select id_aluno, max(nota)
                    from provas
                    group by id_aluno
                ) as p
                on a.id=p.id_aluno
                order by p.max desc limit 1
            """);
            ArrayList<Object> maior = new ArrayList<>();
            if (rs.next()) {
                maior.add(rs.getString(1));
                maior.add(rs.getFloat(2));
                return maior;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }/*
    @GetMapping("/alunos/{id}")
    public Aluno aluno(@PathVariable(value="id") int id) {
        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("select * from alunos where id="+id);

            if (rs.next()) {
                return new Aluno(rs.getInt(1), rs.getString(2));
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }*/
    @GetMapping("/alunos/{id}/maiornota")
    public float maiorNota(@PathVariable(value="id") int id) {
        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("select max(nota) from provas where id_aluno="+id);

            if (rs.next()) {
                return rs.getFloat(1);
            } else {
                return -1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    @GetMapping("/alunos/{id}/menornota")
    public float menorNota(@PathVariable(value="id") int id) {
        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("select min(nota) from provas where id_aluno="+id);

            if (rs.next()) {
                return rs.getFloat(1);
            } else {
                return -1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    @GetMapping("/alunos/{id}/medianota")
    public float mediaNota(@PathVariable(value="id") int id) {
        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("select nota from provas where id_aluno="+id);

            float sum = 0;
            int count = 0;
            while (rs.next()) {
                sum += rs.getFloat(1);
                count++;
            }
            if (count == 0) {
                return 0;
            } else {
                return sum/count;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    @GetMapping("/alunos/{id}/numeroprovas")
    public int numeroProvas(@PathVariable(value="id") int id) {
        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("select id from provas where id_aluno="+id);

            int count = 0;
            while (rs.next()) {
                count++;
            }
            return count;

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    @GetMapping("/provas")
    public ArrayList<Prova> provas() {
        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("select * from provas");

            ArrayList<Prova> provas = new ArrayList<>();
            while (rs.next()) {
                Prova prova = new Prova(rs.getInt(1), rs.getInt(2), rs.getFloat(3));
                provas.add(prova);
            }
            return provas;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    @PostMapping("/provas/cadastrar")
    public boolean cadastrarProva(@RequestParam(value="idAluno") int idAluno, @RequestParam(value="nota") float nota) {
        try {
            Statement st = db.createStatement();
            st.executeUpdate("insert into provas (id_aluno, nota) values ("+idAluno+", "+nota+")");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}