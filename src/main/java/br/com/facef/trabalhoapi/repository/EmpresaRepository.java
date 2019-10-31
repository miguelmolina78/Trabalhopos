package br.com.facef.trabalhoapi.repository;

import br.com.facef.trabalhoapi.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa,Integer>  {
}
