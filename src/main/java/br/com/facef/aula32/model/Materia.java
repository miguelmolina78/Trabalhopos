package br.com.facef.aula32.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="materia")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Materia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    /* teste */
    @Column (name = "nome", length = 50)
    private String nome;

    @ManyToOne
    //@JsonBackReference(value="curso")
    //@JsonBackReference
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @ManyToOne
    //@JsonBackReference
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Materia() {
    }

    public Materia(int id, String nome, Professor professor) {
        this.id = id;
        this.nome = nome;
        this.professor = professor;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}