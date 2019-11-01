package br.com.facef.trabalhoapi.controller;


import br.com.facef.trabalhoapi.business.ProfessorBusiness;
import br.com.facef.trabalhoapi.exception.RestException;
import br.com.facef.trabalhoapi.model.Professor;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value="Listar todos os professores existentes.",response= Professor.class, nickname = "findAll",
            notes="Essa operação retorna lista com as informações de todas os professores.")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="Retorna lista de Professores.", response=Professor.class),
            @ApiResponse(code=500, message="Erro interno do servidor.", response=Professor.class),
            @ApiResponse(code=400, message="Problemas com a Requisição.", response=Professor.class)})

    @GetMapping
    public ResponseEntity<List<Professor>> findAll(){
        return ResponseEntity.ok().body(professorBusiness.findAll());
    }

    @ApiOperation(
            value="Listar informações de um professor.", response=Professor.class,
            notes="Essa operação retorna informações de um professor.")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Retorna os dados de um professor.",response=Professor.class),
            @ApiResponse(code=404,message="Erro apresentado quando não encontra o ID informado.",response=Professor.class),
            @ApiResponse(code=500, message="Erro interno do servidor.", response=Professor.class),
            @ApiResponse(code=400, message="Problemas com a Requisição.", response=Professor.class)})

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Professor>> findBy(@PathVariable int id) throws RestException {

        Optional<Professor> professorExiste  = professorBusiness.findById(id);
        if (professorExiste.isPresent() == false) {
            throw new RestException( );
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {

            return ResponseEntity.ok().body(professorExiste);
        }        
    }

    @ApiOperation(value="Inserir um novo professor na base de dados.",response=Professor.class,
            notes="Essa operação grava um novo professor.")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="Retorna o professor cadastrado.", response=Professor.class),
            @ApiResponse(code=500, message="Erro interno do servidor.", response=Professor.class),
            @ApiResponse(code=400, message="Problemas com a Requisição.", response=Professor.class)})

    @PostMapping
    public ResponseEntity<Professor> post(@RequestBody Professor professor){
        return ResponseEntity.status(HttpStatus.CREATED).body(professorBusiness.save(professor));
    }

    @ApiOperation(value="Atualizar informações de um professor na base de dados.",response=Professor.class,
            notes="Essa operação atualiza dados de um professor.")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="Retorna o professor com informações atualizadas.", response=Professor.class),
            @ApiResponse(code=404,message="Erro apresentado quando não encontra o ID informado.",response=Professor.class),
            @ApiResponse(code=500, message="Erro interno do servidor.", response=Professor.class),
            @ApiResponse(code=400, message="Problemas com a Requisição.", response=Professor.class)})

    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizaProfessor(@RequestBody Professor professor, @PathVariable int id) throws RestException {

        Optional<Professor> professorAtualiza = professorBusiness.findById(id);
        if (professorAtualiza.isPresent() == false ) {
            throw new RestException( );
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            Professor professorGravar = professorAtualiza.get();
            professorGravar.setNome(professor.getNome());
            professorGravar.setSalario(professor.getSalario());
            return ResponseEntity.status(HttpStatus.CREATED).body(professorBusiness.save(professorGravar));
        }

    }


    @ApiOperation(value="Exclusão de um professor na base de dados.",response=Professor.class,
            notes="Essa operação exclui PERMANENTEMENTE informações do professor na base de dados.")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="Professor Excluído.", response=Professor.class),
            @ApiResponse(code=500, message="Erro interno do servidor.", response=Professor.class),
            @ApiResponse(code=400, message="Problemas com a Requisição.", response=Professor.class)})

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        professorBusiness.deleteById(id);
    }

    @ApiOperation(value="Listar de todas os professores existentes na forma paginada.",response=Professor.class,
            notes="Essa operação retorna lista paginadas com as informações de todas os professores. " +
                    "Nesse EndPoint tem-se um parâmetro não requerido chamado numeropagina. " +
                    "Quando não informado, esse parametro assume o valor zero. " +
                    "O sistema trata a pagina ZERO como sendo a PRIMEIRA PÁGINA DA LISTA. " +
                    "Essa lista está configurada para ter um tamanho FIXO de 10 registros por página." )
    @ApiResponses(value= {
            @ApiResponse(code=200, message="Retorna lista de professores.", response=Professor.class),
            @ApiResponse(code=500, message="Erro interno do servidor.", response=Professor.class),
            @ApiResponse(code=400, message="Problemas com a Requisição.", response=Professor.class)})

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
