package br.com.facef.aula32.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="curso")
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column (name = "nome", length = 50)
    private String nome;
    @Column (name = "valorMensalidade")
    private Double valorMensalidade;

    @Column (name = "dataExpirou")
    private Date dataExpirou;

    @JsonManagedReference
    @OneToMany(mappedBy = "curso")
    private Set<Turma> turma;

    public Curso() {
    }

    public Curso(int id, String nome) {
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

    public Double getValorMensalidade() {
        return valorMensalidade;
    }

    public void setValorMensalidade(Double valorMensalidade) {
        this.valorMensalidade = valorMensalidade;
    }

    public Date getDataExpirou() {
        return dataExpirou;
    }

    public void setDataExpirou(Date dataExpirou) {
        this.dataExpirou = dataExpirou;
    }

    public Set<Turma> getTurma() {
        return turma;
    }

    public void setTurma(Set<Turma> turma) {
        this.turma = turma;
    }
}