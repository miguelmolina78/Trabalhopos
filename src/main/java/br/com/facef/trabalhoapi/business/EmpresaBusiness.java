package br.com.facef.trabalhoapi.business;

import br.com.facef.trabalhoapi.model.Empresa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EmpresaBusiness {

    List<Empresa> findAll();
    Optional<Empresa> findById(int id);
    Empresa save(Empresa e);
    void deleteById(int id);
    Page<Empresa> listapaginada(Pageable pageable);

}

