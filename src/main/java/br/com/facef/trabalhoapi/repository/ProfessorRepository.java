package br.com.facef.trabalhoapi.repository;


import br.com.facef.trabalhoapi.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor,Integer>  {
}
