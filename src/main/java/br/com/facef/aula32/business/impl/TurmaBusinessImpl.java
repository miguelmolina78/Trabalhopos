package br.com.facef.aula32.business.impl;

import br.com.facef.aula32.business.TurmaBusiness;
import br.com.facef.aula32.model.Turma;
import br.com.facef.aula32.repository.CursoRepository;
import br.com.facef.aula32.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaBusinessImpl implements TurmaBusiness {

    private final TurmaRepository turmaRepository;
    private final CursoRepository cursoRepository;

    @Autowired
    public TurmaBusinessImpl(TurmaRepository turmaRepository, CursoRepository cursoRepository) {
        this.turmaRepository = turmaRepository;
        this.cursoRepository = cursoRepository;
    }

    @Override
    public List<Turma> findAll() {
        return  turmaRepository.findAll();

    }

    public Turma save(Turma t) {
        t.setCurso(cursoRepository.findById(t.getCurso().getId())
                .get());
        return turmaRepository.save(t);
    }

    public Optional<Turma> findById(int id){
        return turmaRepository.findById(id);

    }

    public void deleteById(int id) {
        turmaRepository.deleteById(id);

    }

    public Page<Turma> listapaginada(Pageable pageable){
       return  turmaRepository.findAll(pageable);

    }




}
