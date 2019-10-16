package br.com.facef.aula32.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="empresa")
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    /* teste */
    @Column (name = "nome", length = 50)
    private String nome;

    public Empresa() {
    }

    public Empresa(int id, String nome) {
        this.id = id;
        this.nome = nome;
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