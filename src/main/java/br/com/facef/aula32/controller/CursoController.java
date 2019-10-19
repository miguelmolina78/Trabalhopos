package br.com.facef.aula32.controller;

import br.com.facef.aula32.business.CursoBusiness;
import br.com.facef.aula32.model.Curso;
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
@RequestMapping("/v1/cursos")
public class CursoController {

    private CursoBusiness cursoBusiness;

    @Autowired
    public CursoController(CursoBusiness cursoBusiness) {
        this.cursoBusiness = cursoBusiness;
    }

    @GetMapping
    public ResponseEntity<List<Curso>> findAll(){
        return ResponseEntity.ok().body(cursoBusiness.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Curso>> findBy(@PathVariable int id) {

        return ResponseEntity.ok().body(cursoBusiness.findById(id));
    }

    @PostMapping
    public ResponseEntity<Curso> post(@RequestBody Curso curso){
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoBusiness.save(curso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizaCurso(@RequestBody Curso curso, @PathVariable int id) {

        Optional<Curso> cursoAtualiza = cursoBusiness.findById(id);
        if (cursoAtualiza.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {

            Curso cursoGravar = cursoAtualiza.get();
            cursoGravar.setNome(curso.getNome());
            cursoGravar.setValorMensalidade(curso.getValorMensalidade());
            cursoGravar.setDataExpirou(curso.getDataExpirou());
            return ResponseEntity.status(HttpStatus.CREATED).body(cursoBusiness.save(cursoGravar));
        }

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        cursoBusiness.deleteById(id);
    }

    @GetMapping("/paginado")
    public List<Curso> getAllEmployees(@RequestParam(defaultValue = "0", required = false) Integer numeroPagina)
    {
//        Pageable paging = PageRequest.of(1, 10, Sort.by("nome").descending());
      // @RequestParam(defaultValue = "0") Integer pageNo
        Pageable paging = PageRequest.of (numeroPagina, 10);//, Sort.by("nome").descending());
        Page<Curso> pagedResult = cursoBusiness.listapaginada(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Curso>();
        }
    }

}
