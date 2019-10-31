package br.com.facef.trabalhoapi.controller;

import br.com.facef.trabalhoapi.business.AlunoBusiness;
import br.com.facef.trabalhoapi.exception.RestException;
import br.com.facef.trabalhoapi.model.Aluno;
import io.swagger.annotations.*;
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

@Api(value = "/v1/alunos")


public class AlunoController {

    private AlunoBusiness alunoBusiness;

    @Autowired
    public AlunoController(AlunoBusiness alunoBusiness) {
        this.alunoBusiness = alunoBusiness;
    }



    @ApiOperation(value="Listar todos os alunos existentes.",response=Aluno.class, nickname = "findAll",
            notes="Essa operação retorna lista com as informações de todos os alunos.")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="Retorna lista de Alunos.", response=Aluno.class),
            @ApiResponse(code=500, message="Erro interno do servidor.", response=Aluno.class),
            @ApiResponse(code=400, message="Problemas com a Requisição.", response=Aluno.class)})
    @GetMapping
    public ResponseEntity<List<Aluno>> findAll() {
        return ResponseEntity.ok().body(alunoBusiness.findAll());
    }

    @ApiOperation(
            value="Listar informações de um aluno.", response=Aluno.class,
            notes="Essa operação retorna informações de um aluno.")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Retorna os dados de um aluno.",response=Aluno.class),
            @ApiResponse(code=404,message="Erro apresentado quando não encontra o ID informado.",response=Aluno.class),
            @ApiResponse(code=500, message="Erro interno do servidor.", response=Aluno.class),
            @ApiResponse(code=400, message="Problemas com a Requisição.", response=Aluno.class)})

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Aluno>> findBy(@ApiParam(name="id",value = "Id do Aluno a ser listado",required = false) @PathVariable int id) throws RestException {

        Optional<Aluno> alunoExiste  = alunoBusiness.findById(id);
        if (alunoExiste.isPresent() == false) {
            throw new RestException( );
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {

            //return ResponseEntity.ok().body(alunoBusiness.findById(id));
            return ResponseEntity.ok().body(alunoExiste);
        }


    }

    @ApiOperation(value="Inserir um novo aluno na base de dados.",response=Aluno.class,
            notes="Essa operação grava um novo aluno.")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="Retorna o novo aluno cadastrado.", response=Aluno.class),
            @ApiResponse(code=500, message="Erro interno do servidor.", response=Aluno.class),
            @ApiResponse(code=400, message="Problemas com a Requisição.", response=Aluno.class)})
    @PostMapping
    public ResponseEntity<Aluno> post(@ApiParam(name="Aluno",value = "Informações do aluno a ser inserido") @RequestBody Aluno aluno) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoBusiness.save(aluno));
    }

    @ApiOperation(value="Atualizar informações de um aluno na base de dados.",response=Aluno.class,
            notes="Essa operação atualiza dados de um aluno.")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="Retorna o aluno com informações atualizadas.", response=Aluno.class),
            @ApiResponse(code=404,message="Erro apresentado quando não encontra o ID informado.",response=Aluno.class),
            @ApiResponse(code=500, message="Erro interno do servidor.", response=Aluno.class),
            @ApiResponse(code=400, message="Problemas com a Requisição.", response=Aluno.class)})
    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizaAluno(
            @RequestBody Aluno aluno,
            @PathVariable int id) throws RestException {

        Optional<Aluno> alunoAtualiza = alunoBusiness.findById(id);
        if (alunoAtualiza.isPresent() == false) {
            throw new RestException( );
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {

            Aluno alunoGravar = alunoAtualiza.get();
            alunoGravar.setNome(aluno.getNome());
            alunoGravar.setDataNascimento(aluno.getDataNascimento());
            return ResponseEntity.status(HttpStatus.CREATED).body(alunoBusiness.save(alunoGravar));
        }

    }
    @ApiOperation(value="Exclusão de um aluno na base de dados.",response=Aluno.class,
            notes="Essa operação exclui PERMANENTEMENTE informações do aluno na base de dados.")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="Aluno Excluído.", response=Aluno.class),
            @ApiResponse(code=500, message="Erro interno do servidor.", response=Aluno.class),
            @ApiResponse(code=400, message="Problemas com a Requisição.", response=Aluno.class)})
    @DeleteMapping("/{id}")
    public void deleteById(@ApiParam(name="id",value = "Id do Aluno a ser excluído.") @PathVariable int id) {
        alunoBusiness.deleteById(id);
    }

    @ApiOperation(value="Listar de todos os alunos existentes na forma paginada.",response=Aluno.class,
            notes="Essa operação retorna lista paginadas com as informações de todos os alunos. " +
                    "Nesse EndPoint tem-se um parâmetro não requerido chamado numeropagina. " +
                    "Quando não informado, esse parametro assume o valor zero. " +
                    "O sistema trata a pagina ZERO como sendo a PRIMEIRA PÁGINA DA LISTA. " +
                    "Essa lista está configurada para ter um tamanho FIXO de 10 registros por página." )
    @ApiResponses(value= {
            @ApiResponse(code=200, message="Retorna lista de Alunos.", response=Aluno.class),
            @ApiResponse(code=500, message="Erro interno do servidor.", response=Aluno.class),
            @ApiResponse(code=400, message="Problemas com a Requisição.", response=Aluno.class)})
    @GetMapping("/paginado")
    public List<Aluno> findAllPaginado(@ApiParam(name="numeroPagina",value = "Número da Página a ser listada.")
                                       @RequestParam(defaultValue = "0", required = false) Integer numeroPagina) {
//        Pageable paging = PageRequest.of(1, 10, Sort.by("nome").descending());
        // @RequestParam(defaultValue = "0") Integer pageNo
        Pageable paging = PageRequest.of(numeroPagina, 10);//, Sort.by("nome").descending());
        Page<Aluno> pagedResult = alunoBusiness.listapaginada(paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Aluno>();
        }
    }

    @ApiOperation(value="Listar os alunos por nome (Início do nome).",response=Aluno.class,
            notes="Essa operação retorna lista de alunos por nome. " +
                  "Nesse EndPoint o nome a ser pesquisado é passado por /nome" )
    @ApiResponses(value= {
            @ApiResponse(code=200, message="Retorna lista de Alunos por nome (Início do nome).", response=Aluno.class),
            @ApiResponse(code=500, message="Erro interno do servidor.", response=Aluno.class),
            @ApiResponse(code=400, message="Problemas com a Requisição.", response=Aluno.class)})
    @GetMapping("/alunoPorNome/{nome}")
    public ResponseEntity<List<Aluno>> findByNome(@PathVariable String nome) {

        return ResponseEntity.ok().body(alunoBusiness.findAllByNomeStartingWith(nome));
    }

    @ApiOperation(value="Listar os alunos por nome (Início do nome).",response=Aluno.class,
            notes="Essa operação retorna lista de alunos por nome. " +
                    "Nesse EndPoint o nome a ser pesquisado é passado por ?nome=" )
    @ApiResponses(value= {
            @ApiResponse(code=200, message="Retorna lista de Alunos por nome (Início do nome).", response=Aluno.class),
            @ApiResponse(code=500, message="Erro interno do servidor.", response=Aluno.class),
            @ApiResponse(code=400, message="Problemas com a Requisição.", response=Aluno.class)})
    @GetMapping("/alunoPorNomeParametro")
    public ResponseEntity<List<Aluno>> findByNome2(@ApiParam(name="nome",value = "Parte inicial do nome do aluno.")
                                                   @RequestParam(required = true) String nome) {

        return ResponseEntity.ok().body(alunoBusiness.findAllByNomeStartingWith(nome));
    }

}
