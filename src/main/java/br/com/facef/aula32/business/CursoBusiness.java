package br.com.facef.aula32.business;

import br.com.facef.aula32.model.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CursoBusiness {

    List<Curso> findAll();
    Optional<Curso> findById(int id);
    Curso save(Curso c);
    void deleteById(int id);
    Page<Curso> listapaginada(Pageable pageable);

}

