package br.com.facef.aula32.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="professor")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Professor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    /* teste */
    @Column (name = "nome", length = 50)
    private String nome;
    private float salario;

    //@JsonManagedReference
    @OneToMany(mappedBy = "professor")
    @JsonIgnore
    private Set<Materia> materia;


    public Set<Materia> getMateria() {
        return materia;
    }

    public void setMateria(Set<Materia> materia) {
        this.materia = materia;
    }

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