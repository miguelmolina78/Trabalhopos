package br.com.facef.aula32.controller;

import br.com.facef.aula32.business.TurmaBusiness;
import br.com.facef.aula32.model.Turma;
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
@RequestMapping("/v1/turmas")
public class TurmaController {

    private TurmaBusiness turmaBusiness;

    @Autowired
    public TurmaController(TurmaBusiness turmaBusiness) {
        this.turmaBusiness = turmaBusiness;
    }

    @GetMapping
    public ResponseEntity<List<Turma>> findAll(){
        return ResponseEntity.ok().body(turmaBusiness.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Turma>> findBy(@PathVariable int id) {

        return ResponseEntity.ok().body(turmaBusiness.findById(id));
    }

    @PostMapping
    public ResponseEntity<Turma> post(@RequestBody Turma turma){
        return ResponseEntity.status(HttpStatus.CREATED).body(turmaBusiness.save(turma));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> atualizaCurso(@RequestBody Turma turma, @PathVariable int id) {

        Optional<Turma> turmaAtualiza = turmaBusiness.findById(id);
        if (turmaAtualiza.isPresent() == false ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {

            Turma turmaGravar = turmaAtualiza.get();
            turmaGravar.setDataInicioInscricao(turma.getDataInicioInscricao());
            turmaGravar.setDataFinalInscricao(turma.getDataFinalInscricao());
            turmaGravar.setDataInicioAulas(turma.getDataInicioAulas());
            turmaGravar.setDataFinalAulas(turma.getDataFinalAulas());
            return ResponseEntity.status(HttpStatus.CREATED).body(turmaBusiness.save(turmaGravar));
        }

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        turmaBusiness.deleteById(id);
    }

    @GetMapping("/paginado")
    public List<Turma> getAllEmployees(@RequestParam(defaultValue = "0", required = false) Integer numeroPagina)
    {
//        Pageable paging = PageRequest.of(1, 10, Sort.by("nome").descending());
      // @RequestParam(defaultValue = "0") Integer pageNo
        Pageable paging = PageRequest.of (numeroPagina, 10);//, Sort.by("nome").descending());
        Page<Turma> pagedResult = turmaBusiness.listapaginada(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Turma>();
        }
    }

}
