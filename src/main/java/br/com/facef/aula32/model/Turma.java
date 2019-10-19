package br.com.facef.aula32.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="turma")
public class Turma implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "curso_id", nullable=false)
    private Curso curso;

    @Column (name = "dataInicioInscricao")
    private Date dataInicioInscricao;

    @Column (name = "dataFinalInscricao")
    private Date dataFinalInscricao;

    @Column (name = "dataInicioAulas")
    private Date dataInicioAulas;

    @Column (name = "dataFinalAulas")
    private Date dataFinalAulas;

    @JsonManagedReference
    @OneToMany(mappedBy = "turma")
    private Set<Aluno> aluno;

    public Set<Aluno> getAluno() {
        return aluno;
    }

    public void setAluno(Set<Aluno> aluno) {
        this.aluno = aluno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataInicioInscricao() {
        return dataInicioInscricao;
    }

    public void setDataInicioInscricao(Date dataInicioInscricao) {
        this.dataInicioInscricao = dataInicioInscricao;
    }

    public Date getDataFinalInscricao() {
        return dataFinalInscricao;
    }

    public void setDataFinalInscricao(Date dataFinalInscricao) {
        this.dataFinalInscricao = dataFinalInscricao;
    }

    public Date getDataInicioAulas() {
        return dataInicioAulas;
    }

    public void setDataInicioAulas(Date dataInicioAulas) {
        this.dataInicioAulas = dataInicioAulas;
    }

    public Date getDataFinalAulas() {
        return dataFinalAulas;
    }

    public void setDataFinalAulas(Date dataFinalAulas) {
        this.dataFinalAulas = dataFinalAulas;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}