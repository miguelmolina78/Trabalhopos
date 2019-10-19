package br.com.facef.aula32.business.impl;

import br.com.facef.aula32.business.MateriaBusiness;
import br.com.facef.aula32.business.ProfessorBusiness;
import br.com.facef.aula32.model.Curso;
import br.com.facef.aula32.model.Materia;
import br.com.facef.aula32.model.Professor;
import br.com.facef.aula32.repository.CursoRepository;
import br.com.facef.aula32.repository.MateriaRepository;
import br.com.facef.aula32.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaBusinessImpl implements MateriaBusiness {

    private MateriaRepository materiaRepository;

    @Autowired
    public MateriaBusinessImpl(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

    @Override
    public List<Materia> findAll() {
        return  materiaRepository.findAll();

    }

    public Materia save(Materia e)
    {
        return materiaRepository.save(e);
    }

    public Optional<Materia> findById(int id){
        return materiaRepository.findById(id);

    }

    public void deleteById(int id) {
         materiaRepository.deleteById(id);

    }

    public Page<Materia> listapaginada(Pageable pageable){
       return  materiaRepository.findAll(pageable);

    }




}
