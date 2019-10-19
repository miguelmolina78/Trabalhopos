package br.com.facef.aula32.controller;

import br.com.facef.aula32.business.AlunoBusiness;
import br.com.facef.aula32.model.Aluno;
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
@RequestMapping("/v1/alunos")
public class AlunoController {

    private AlunoBusiness alunoBusiness;

    @Autowired
    public AlunoController(AlunoBusiness alunoBusiness) {
        this.alunoBusiness = alunoBusiness;
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> findAll(){
        return ResponseEntity.ok().body(alunoBusiness.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Aluno>> findBy(@PathVariable int id) {

        return ResponseEntity.ok().body(alunoBusiness.findById(id));
    }

    @PostMapping
    public ResponseEntity<Aluno> post(@RequestBody Aluno aluno){
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoBusiness.save(aluno));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizaAluno(@RequestBody Aluno aluno, @PathVariable int id) {

        Optional<Aluno> alunoAtualiza = alunoBusiness.findById(id);
        if (alunoAtualiza.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {

            Aluno alunoGravar = alunoAtualiza.get();
            alunoGravar.setNome(aluno.getNome());
            alunoGravar.setDataNascimento(aluno.getDataNascimento());
            return ResponseEntity.status(HttpStatus.CREATED).body(alunoBusiness.save(alunoGravar));
        }

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        alunoBusiness.deleteById(id);
    }

    @GetMapping("/paginado")
    public List<Aluno> getAllEmployees(@RequestParam(defaultValue = "0", required = false) Integer numeroPagina)
    {
//        Pageable paging = PageRequest.of(1, 10, Sort.by("nome").descending());
      // @RequestParam(defaultValue = "0") Integer pageNo
        Pageable paging = PageRequest.of (numeroPagina, 10);//, Sort.by("nome").descending());
        Page<Aluno> pagedResult = alunoBusiness.listapaginada(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Aluno>();
        }
    }

}
