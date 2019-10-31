package br.com.facef.trabalhoapi.business.impl;

import br.com.facef.trabalhoapi.business.EmpresaBusiness;
import br.com.facef.trabalhoapi.model.Empresa;
import br.com.facef.trabalhoapi.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaBusinessImpl implements EmpresaBusiness {

    private EmpresaRepository empresaRepository;

    @Autowired
    public EmpresaBusinessImpl(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @Override
    public List<Empresa> findAll() {
        return  empresaRepository.findAll();

    }

    public Empresa save(Empresa e) {
        return empresaRepository.save(e);
    }

    public Optional<Empresa> findById(int id){
        return empresaRepository.findById(id);

    }

    public void deleteById(int id) {
         empresaRepository.deleteById(id);

    }

    public Page<Empresa> listapaginada(Pageable pageable){
       return  empresaRepository.findAll(pageable);

    }




}
