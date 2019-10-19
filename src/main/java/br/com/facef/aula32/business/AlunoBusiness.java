package br.com.facef.aula32.business;

import br.com.facef.aula32.model.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AlunoBusiness {

    List<Aluno> findAll();
    Optional<Aluno> findById(int id);
    Aluno save(Aluno a);
    void deleteById(int id);
    Page<Aluno> listapaginada(Pageable pageable);

}

