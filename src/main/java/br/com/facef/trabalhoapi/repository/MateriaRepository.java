package br.com.facef.trabalhoapi.repository;


import br.com.facef.trabalhoapi.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaRepository extends JpaRepository<Materia,Integer>  {
}
