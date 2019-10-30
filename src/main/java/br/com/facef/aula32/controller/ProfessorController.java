package br.com.facef.aula32.controller;


import br.com.facef.aula32.business.ProfessorBusiness;
import br.com.facef.aula32.model.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/professores")
public class ProfessorController {

    private ProfessorBusiness professorBusiness;

    @Autowired
    public ProfessorController(ProfessorBusiness professorBusiness) {
        this.professorBusiness = professorBusiness;
    }

    @GetMapping
    public ResponseEntity<List<Professor>> findAll(){
        return ResponseEntity.ok().body(professorBusiness.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Professor>> findBy(@PathVariable int id) {

        return ResponseEntity.ok().body(professorBusiness.findById(id));
    }

    @PostMapping
    public ResponseEntity<Professor> post(@RequestBody Professor professor){
        return ResponseEntity.status(HttpStatus.CREATED).body(professorBusiness.save(professor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizaProfessor(@RequestBody Professor professor, @PathVariable int id) {

        Optional<Professor> professorAtualiza = professorBusiness.findById(id);
        if (professorAtualiza.isPresent() == false ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            Professor professorGravar = professorAtualiza.get();
            professorGravar.setNome(professor.getNome());
            professorGravar.setSalario(professor.getSalario());
            return ResponseEntity.status(HttpStatus.CREATED).body(professorBusiness.save(professorGravar));
        }

    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        professorBusiness.deleteById(id);
    }

    @GetMapping("/paginado")
    public List<Professor> getAllEmployees(@RequestParam(defaultValue = "0", required = false) Integer numeroPagina)
    {
//        Pageable paging = PageRequest.of(1, 10, Sort.by("nome").descending());
      // @RequestParam(defaultValue = "0") Integer pageNo
        Pageable paging = PageRequest.of (numeroPagina, 10);//, Sort.by("nome").descending());
        Page<Professor> pagedResult = professorBusiness.listapaginada(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Professor>();
        }
    }

}
