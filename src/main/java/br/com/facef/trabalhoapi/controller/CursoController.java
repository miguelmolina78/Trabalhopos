package br.com.facef.trabalhoapi.controller;

import br.com.facef.trabalhoapi.business.CursoBusiness;
import br.com.facef.trabalhoapi.exception.RestException;
import br.com.facef.trabalhoapi.model.Curso;
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
@RequestMapping("/v1/cursos")
public class CursoController {

    private CursoBusiness cursoBusiness;

    @Autowired
    public CursoController(CursoBusiness cursoBusiness) {
        this.cursoBusiness = cursoBusiness;
    }


    @ApiOperation(value="Listar todos os cursos existentes.",response=Curso.class, nickname = "findAll",
            notes="Essa operação retorna lista com as informações de todos os cursos.")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="Retorna lista de cursos.", response=Curso.class),
            @ApiResponse(code=500, message="Erro interno do servidor.", response=Curso.class),
            @ApiResponse(code=400, message="Problemas com a Requisição.", response=Curso.class)})

    @GetMapping
    public ResponseEntity<List<Curso>> findAll(){
        return ResponseEntity.ok().body(cursoBusiness.findAll());
    }

    @ApiOperation(
            value="Listar informações de um curso.", response=Curso.class,
            notes="Essa operação retorna informações de um curso.")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Retorna os dados de um curso.",response=Curso.class),
            @ApiResponse(code=404,message="Erro apresentado quando não encontra o ID informado.",response=Curso.class),
            @ApiResponse(code=500, message="Erro interno do servidor.", response=Curso.class),
            @ApiResponse(code=400, message="Problemas com a Requisição.", response=Curso.class)})

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Curso>> findBy(@PathVariable int id) throws RestException {

        Optional<Curso> cursoExiste  = cursoBusiness.findById(id);
        if (cursoExiste.isPresent() == false) {
            throw new RestException( );
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {

            //return ResponseEntity.ok().body(cursoBusiness.findById(id));
            return ResponseEntity.ok().body(cursoExiste);
        }
    }

    @ApiOperation(value="Inserir um novo curso na base de dados.",response=Curso.class,
            notes="Essa operação grava um novo curso.")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="Retorna o novo curso cadastrado.", response=Curso.class),
            @ApiResponse(code=500, message="Erro interno do servidor.", response=Curso.class),
            @ApiResponse(code=400, message="Problemas com a Requisição.", response=Curso.class)})

    @PostMapping
    public ResponseEntity<Curso> post(@RequestBody Curso curso){
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoBusiness.save(curso));
    }

    @ApiOperation(value="Atualizar informações de um curso na base de dados.",response=Curso.class,
            notes="Essa operação atualiza dados de um curso.")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="Retorna o curso com informações atualizadas.", response=Curso.class),
            @ApiResponse(code=404,message="Erro apresentado quando não encontra o ID informado.",response=Curso.class),
            @ApiResponse(code=500, message="Erro interno do servidor.", response=Curso.class),
            @ApiResponse(code=400, message="Problemas com a Requisição.", response=Curso.class)})

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizaCurso(@RequestBody Curso curso, @PathVariable int id) throws RestException {

        Optional<Curso> cursoAtualiza = cursoBusiness.findById(id);
        if (cursoAtualiza.isPresent() == false ) {
            throw new RestException( );
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {

            Curso cursoGravar = cursoAtualiza.get();
            cursoGravar.setNome(curso.getNome());
            cursoGravar.setValorMensalidade(curso.getValorMensalidade());
            cursoGravar.setDataExpirou(curso.getDataExpirou());
            return ResponseEntity.status(HttpStatus.CREATED).body(cursoBusiness.save(cursoGravar));
        }

    }

    @ApiOperation(value="Exclusão de um curso na base de dados.",response=Curso.class,
            notes="Essa operação exclui PERMANENTEMENTE informações do curso na base de dados.")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="Curso Excluído.", response=Curso.class),
            @ApiResponse(code=500, message="Erro interno do servidor.", response=Curso.class),
            @ApiResponse(code=400, message="Problemas com a Requisição.", response=Curso.class)})

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        cursoBusiness.deleteById(id);
    }

    @ApiOperation(value="Listar de todos os cursos existentes na forma paginada.",response=Curso.class,
            notes="Essa operação retorna lista paginadas com as informações de todos os cursos. " +
                    "Nesse EndPoint tem-se um parâmetro não requerido chamado numeropagina. " +
                    "Quando não informado, esse parametro assume o valor zero. " +
                    "O sistema trata a pagina ZERO como sendo a PRIMEIRA PÁGINA DA LISTA. " +
                    "Essa lista está configurada para ter um tamanho FIXO de 10 registros por página." )
    @ApiResponses(value= {
            @ApiResponse(code=200, message="Retorna lista de cursos.", response=Curso.class),
            @ApiResponse(code=500, message="Erro interno do servidor.", response=Curso.class),
            @ApiResponse(code=400, message="Problemas com a Requisição.", response=Curso.class)})

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
