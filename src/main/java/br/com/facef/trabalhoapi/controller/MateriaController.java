package br.com.facef.trabalhoapi.controller;


import br.com.facef.trabalhoapi.business.MateriaBusiness;
import br.com.facef.trabalhoapi.exception.RestException;
import br.com.facef.trabalhoapi.model.Materia;
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
@RequestMapping("/v1/materias")
public class MateriaController {

    private MateriaBusiness materiaBusiness;

    @Autowired
    public MateriaController(MateriaBusiness materiaBusiness)
    {
        this.materiaBusiness = materiaBusiness;
    }

    @ApiOperation(value="Listar todos as matérias  existentes.",response= Materia.class, nickname = "findAll",
            notes="Essa operação retorna lista com as informações de todas as matérias.")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="Retorna lista de Matérias.", response=Materia.class),
            @ApiResponse(code=500, message="Erro interno do servidor.", response=Materia.class),
            @ApiResponse(code=400, message="Problemas com a Requisição.", response=Materia.class)})

    @GetMapping
    public ResponseEntity<List<Materia>> findAll()
    {
        return ResponseEntity.ok().body(materiaBusiness.findAll());

    }

    @ApiOperation(
            value="Listar informações de uma matéria.", response=Materia.class,
            notes="Essa operação retorna informações de uma matéria.")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Retorna os dados de uma matéria.",response=Materia.class),
            @ApiResponse(code=404,message="Erro apresentado quando não encontra o ID informado.",response=Materia.class),
            @ApiResponse(code=500, message="Erro interno do servidor.", response=Materia.class),
            @ApiResponse(code=400, message="Problemas com a Requisição.", response=Materia.class)})

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Materia>> findBy(@PathVariable int id) throws RestException {

        Optional<Materia> materiaExiste  = materiaBusiness.findById(id);
        if (materiaExiste.isPresent() == false) {
            throw new RestException( );
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {

            return ResponseEntity.ok().body(materiaExiste);
        }
        
    }

    @ApiOperation(value="Inserir uma nova matéria  na base de dados.",response=Materia.class,
            notes="Essa operação grava uma nova matéria.")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="Retorna a matéria cadastrada.", response=Materia.class),
            @ApiResponse(code=500, message="Erro interno do servidor.", response=Materia.class),
            @ApiResponse(code=400, message="Problemas com a Requisição.", response=Materia.class)})
    
    @PostMapping
    public ResponseEntity<Materia> post(@RequestBody Materia materia){
        return ResponseEntity.status(HttpStatus.CREATED).body(materiaBusiness.save(materia));
    }

    @ApiOperation(value="Atualizar informações de uma matéria na base de dados.",response=Materia.class,
            notes="Essa operação atualiza dados de uma matéria.")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="Retorna a turma  com informações atualizadas.", response=Materia.class),
            @ApiResponse(code=404,message="Erro apresentado quando não encontra o ID informado.",response=Materia.class),
            @ApiResponse(code=500, message="Erro interno do servidor.", response=Materia.class),
            @ApiResponse(code=400, message="Problemas com a Requisição.", response=Materia.class)})

    @PutMapping("/{id}")
    public ResponseEntity<Materia> atualizaMateria(@RequestBody Materia materia, @PathVariable int id) throws RestException {

        Optional<Materia> materiaAtualiza = materiaBusiness.findById(id);
        if (materiaAtualiza.isPresent() == false ) {
            throw new RestException( );
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            Materia materiaGravar = materiaAtualiza.get();
            materiaGravar.setNome(materia.getNome());
           // materiaGravar.setSalario(professor.getSalario());
            return ResponseEntity.status(HttpStatus.CREATED).body(materiaBusiness.save(materiaGravar));
        }

    }


    @ApiOperation(value="Exclusão de uma matéria na base de dados.",response=Materia.class,
            notes="Essa operação exclui PERMANENTEMENTE informações da turma  na base de dados.")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="Matéria Excluída.", response=Materia.class),
            @ApiResponse(code=500, message="Erro interno do servidor.", response=Materia.class),
            @ApiResponse(code=400, message="Problemas com a Requisição.", response=Materia.class)})

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        materiaBusiness.deleteById(id);
    }


    @ApiOperation(value="Listar de todas as matérias existentes na forma paginada.",response=Materia.class,
            notes="Essa operação retorna lista paginadas com as informações de todas as matérias. " +
                    "Nesse EndPoint tem-se um parâmetro não requerido chamado numeropagina. " +
                    "Quando não informado, esse parametro assume o valor zero. " +
                    "O sistema trata a pagina ZERO como sendo a PRIMEIRA PÁGINA DA LISTA. " +
                    "Essa lista está configurada para ter um tamanho FIXO de 10 registros por página." )
    @ApiResponses(value= {
            @ApiResponse(code=200, message="Retorna lista de matérias.", response=Materia.class),
            @ApiResponse(code=500, message="Erro interno do servidor.", response=Materia.class),
            @ApiResponse(code=400, message="Problemas com a Requisição.", response=Materia.class)})

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
