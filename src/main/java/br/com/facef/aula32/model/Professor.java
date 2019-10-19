package br.com.facef.aula32.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="professor")
public class Professor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    /* teste */
    @Column (name = "nome", length = 50)
    private String nome;
    private float salario;


    /*Comentário para commit*/
    public Professor() {
    }

    public Professor(int id, String nome, float salario) {
        this.id = id;
        this.nome = nome;
        this.salario = salario;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
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