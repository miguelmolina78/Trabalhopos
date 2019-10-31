package br.com.facef.trabalhoapi.repository;

import br.com.facef.trabalhoapi.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurmaRepository extends JpaRepository<Turma,Integer>  {

}
