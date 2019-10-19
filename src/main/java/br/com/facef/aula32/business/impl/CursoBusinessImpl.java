package br.com.facef.aula32.business.impl;

import br.com.facef.aula32.business.CursoBusiness;
import br.com.facef.aula32.model.Curso;
import br.com.facef.aula32.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoBusinessImpl implements CursoBusiness {

    private CursoRepository cursoRepository;

    @Autowired
    public CursoBusinessImpl(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    public List<Curso> findAll() {
        return  cursoRepository.findAll();

    }

    public Curso save(Curso c) { return cursoRepository.save(c);
    }

    public Optional<Curso> findById(int id){
        return cursoRepository.findById(id);

    }

    public void deleteById(int id) {
        cursoRepository.deleteById(id);

    }

    public Page<Curso> listapaginada(Pageable pageable){
       return  cursoRepository.findAll(pageable);

    }




}
