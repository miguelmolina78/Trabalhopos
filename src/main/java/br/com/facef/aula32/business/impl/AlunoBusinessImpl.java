package br.com.facef.aula32.business.impl;

import br.com.facef.aula32.business.AlunoBusiness;
import br.com.facef.aula32.model.Aluno;
import br.com.facef.aula32.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoBusinessImpl implements AlunoBusiness {

    private AlunoRepository alunoRepository;

    @Autowired
    public AlunoBusinessImpl(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Override
    public List<Aluno> findAll() {
        return  alunoRepository.findAll();

    }

    public Aluno save(Aluno c) { return alunoRepository.save(c);
    }

    public Optional<Aluno> findById(int id){
        return alunoRepository.findById(id);

    }

    public void deleteById(int id) {
        alunoRepository.deleteById(id);

    }

    public Page<Aluno> listapaginada(Pageable pageable){
       return  alunoRepository.findAll(pageable);

    }

}
