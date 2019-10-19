package br.com.facef.aula32.business.impl;

import br.com.facef.aula32.business.ProfessorBusiness;
import br.com.facef.aula32.model.Professor;
import br.com.facef.aula32.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorBusinessImpl implements ProfessorBusiness {

    private ProfessorRepository professorRepository;

    @Autowired
    public ProfessorBusinessImpl(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public List<Professor> findAll() {
        return  professorRepository.findAll();

    }

    public Professor save(Professor e) {
        return professorRepository.save(e);
    }

    public Optional<Professor> findById(int id){
        return professorRepository.findById(id);

    }

    public void deleteById(int id) {
         professorRepository.deleteById(id);

    }

    public Page<Professor> listapaginada(Pageable pageable){
       return  professorRepository.findAll(pageable);

    }




}
