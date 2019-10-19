package br.com.facef.aula32.repository;

import br.com.facef.aula32.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurmaRepository extends JpaRepository<Turma,Integer>  {
}
