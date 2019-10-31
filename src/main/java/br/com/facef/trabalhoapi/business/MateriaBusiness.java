package br.com.facef.trabalhoapi.business;


import br.com.facef.trabalhoapi.model.Materia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MateriaBusiness {

    List<Materia> findAll();
    Optional<Materia> findById(int id);
    Materia save(Materia e);
    void deleteById(int id);
    Page<Materia> listapaginada(Pageable pageable);

}

