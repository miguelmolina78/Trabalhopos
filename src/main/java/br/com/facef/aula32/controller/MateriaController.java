package br.com.facef.aula32.controller;


import br.com.facef.aula32.business.MateriaBusiness;
import br.com.facef.aula32.business.ProfessorBusiness;
import br.com.facef.aula32.model.Materia;
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
@RequestMapping("/v1/materias")
public class MateriaController {

    private MateriaBusiness materiaBusiness;

    @Autowired
    public MateriaController(MateriaBusiness materiaBusiness)
    {
        this.materiaBusiness = materiaBusiness;
    }

    @GetMapping
    public ResponseEntity<List<Materia>> findAll()
    {
        return ResponseEntity.ok().body(materiaBusiness.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Materia>> findBy(@PathVariable int id) {

        return ResponseEntity.ok().body(materiaBusiness.findById(id));
    }

    @PostMapping
    public ResponseEntity<Materia> post(@RequestBody Materia materia){
        return ResponseEntity.status(HttpStatus.CREATED).body(materiaBusiness.save(materia));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Materia> atualizaMateria(@RequestBody Materia materia, @PathVariable int id) {

        Optional<Materia> materiaAtualiza = materiaBusiness.findById(id);
        if (materiaAtualiza.isPresent() == false ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            Materia materiaGravar = materiaAtualiza.get();
            materiaGravar.setNome(materia.getNome());
           // materiaGravar.setSalario(professor.getSalario());
            return ResponseEntity.status(HttpStatus.CREATED).body(materiaBusiness.save(materiaGravar));
        }

    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        materiaBusiness.deleteById(id);
    }

    @GetMapping("/paginado")
    public List<Materia> getAllEmployees(@RequestParam(defaultValue = "0", required = false) Integer numeroPagina)
    {
//        Pageable paging = PageRequest.of(1, 10, Sort.by("nome").descending());
      // @RequestParam(defaultValue = "0") Integer pageNo
        Pageable paging = PageRequest.of (numeroPagina, 10);//, Sort.by("nome").descending());
        Page<Materia> pagedResult = materiaBusiness.listapaginada(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Materia>();
        }
    }

}
