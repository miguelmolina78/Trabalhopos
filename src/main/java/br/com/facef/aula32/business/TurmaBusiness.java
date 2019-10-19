package br.com.facef.aula32.business;

import br.com.facef.aula32.model.Turma;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TurmaBusiness {

    List<Turma> findAll();
    Optional<Turma> findById(int id);
    Turma save(Turma t);
    void deleteById(int id);
    Page<Turma> listapaginada(Pageable pageable);

}

