package br.com.facef.aula32.business;

import br.com.facef.aula32.model.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProfessorBusiness {

    List<Professor> findAll();
    Optional<Professor> findById(int id);
    Professor save(Professor e);
    void deleteById(int id);
    Page<Professor> listapaginada(Pageable pageable);

}

