package br.com.facef.trabalhoapi.controller;

import br.com.facef.trabalhoapi.business.TurmaBusiness;
import br.com.facef.trabalhoapi.exception.RestException;
import br.com.facef.trabalhoapi.model.Turma;
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
@RequestMapping("/v1/turmas")
public class TurmaController {

    private TurmaBusiness turmaBusiness;

    @Autowired
    public TurmaController(TurmaBusiness turmaBusiness) {
        this.turmaBusiness = turmaBusiness;
    }

    @ApiOperation(value="Listar todos as turmas  existentes.",response= Turma.class, nickname = "findAll",
            notes="Essa operação retorna lista com as informações de todas as turmas.")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="Retorna lista de Turmas.", response=Turma.class),
            @ApiResponse(code=500, message="Erro interno do servidor.", response=Turma.class),
            @ApiResponse(code=400, message="Problemas com a Requisição.", response=Turma.class)})

    @GetMapping
    public ResponseEntity<List<Turma>> findAll(){
        return ResponseEntity.ok().body(turmaBusiness.findAll());
    }


    @ApiOperation(
            value="Listar informações de uma turma.", response=Turma.class,
            notes="Essa operação retorna informações de uma turma.")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Retorna os dados de uma turma.",response=Turma.class),
            @ApiResponse(code=404,message="Erro apresentado quando não encontra o ID informado.",response=Turma.class),
            @ApiResponse(code=500, message="Erro interno do servidor.", response=Turma.class),
            @ApiResponse(code=400, message="Problemas com a Requisição.", response=Turma.class)})

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Turma>> findBy(@PathVariable int id) throws RestException {


        Optional<Turma> turmaExiste  = turmaBusiness.findById(id);
        if (turmaExiste.isPresent() == false) {
            throw new RestException( );
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {

            return ResponseEntity.ok().body(turmaExiste);
        }

    }

    @ApiOperation(value="Inserir uma nova turma  na base de dados.",response=Turma.class,
            notes="Essa operação grava uma nova turma.")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="Retorna a turma cadastrada.", response=Turma.class),
            @ApiResponse(code=500, message="Erro interno do servidor.", response=Turma.class),
            @ApiResponse(code=400, message="Problemas com a Requisição.", response=Turma.class)})

    @PostMapping
    public ResponseEntity<Turma> post(@RequestBody Turma turma){
        return ResponseEntity.status(HttpStatus.CREATED).body(turmaBusiness.save(turma));
    }


    @ApiOperation(value="Atualizar informações de uma turma na base de dados.",response=Turma.class,
            notes="Essa operação atualiza dados de uma turma.")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="Retorna a turma  com informações atualizadas.", response=Turma.class),
            @ApiResponse(code=404,message="Erro apresentado quando não encontra o ID informado.",response=Turma.class),
            @ApiResponse(code=500, message="Erro interno do servidor.", response=Turma.class),
            @ApiResponse(code=400, message="Problemas com a Requisição.", response=Turma.class)})

    @PutMapping("/{id}")
    public ResponseEntity<Turma> atualizaCurso(@RequestBody Turma turma, @PathVariable int id) throws RestException {

        Optional<Turma> turmaAtualiza = turmaBusiness.findById(id);
        if (turmaAtualiza.isPresent() == false ) {
            throw new RestException( );

            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {

            Turma turmaGravar = turmaAtualiza.get();
            turmaGravar.setDataInicioInscricao(turma.getDataInicioInscricao());
            turmaGravar.setDataFinalInscricao(turma.getDataFinalInscricao());
            turmaGravar.setDataInicioAulas(turma.getDataInicioAulas());
            turmaGravar.setDataFinalAulas(turma.getDataFinalAulas());
            return ResponseEntity.status(HttpStatus.CREATED).body(turmaBusiness.save(turmaGravar));
        }

    }

    @ApiOperation(value="Exclusão de uma turma na base de dados.",response=Turma.class,
            notes="Essa operação exclui PERMANENTEMENTE informações da turma  na base de dados.")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="Turma Excluída.", response=Turma.class),
            @ApiResponse(code=500, message="Erro interno do servidor.", response=Turma.class),
            @ApiResponse(code=400, message="Problemas com a Requisição.", response=Turma.class)})

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        turmaBusiness.deleteById(id);
    }

    @ApiOperation(value="Listar de todas as turmas existentes na forma paginada.",response=Turma.class,
            notes="Essa operação retorna lista paginadas com as informações de todas as turmas. " +
                    "Nesse EndPoint tem-se um parâmetro não requerido chamado numeropagina. " +
                    "Quando não informado, esse parametro assume o valor zero. " +
                    "O sistema trata a pagina ZERO como sendo a PRIMEIRA PÁGINA DA LISTA. " +
                    "Essa lista está configurada para ter um tamanho FIXO de 10 registros por página." )
    @ApiResponses(value= {
            @ApiResponse(code=200, message="Retorna lista de turmas.", response=Turma.class),
            @ApiResponse(code=500, message="Erro interno do servidor.", response=Turma.class),
            @ApiResponse(code=400, message="Problemas com a Requisição.", response=Turma.class)})

    @GetMapping("/paginado")
    public List<Turma> findAllPaginado(@RequestParam(defaultValue = "0", required = false) Integer numeroPagina)
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
