package br.com.facef.aula32.contoller;

import br.com.facef.aula32.business.EmpresaBusiness;
import br.com.facef.aula32.model.Empresa;
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
@RequestMapping("/v1/empresas")
public class EmpresaController {

    private EmpresaBusiness empresaBusiness;

    @Autowired
    public EmpresaController(EmpresaBusiness empresaBusiness) {
        this.empresaBusiness = empresaBusiness;
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> findAll(){
        return ResponseEntity.ok().body(empresaBusiness.findAll());
    }

    @GetMapping("/metodo")
    public String method2(){
        return "method2 de retorno de string.";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Empresa>> findBy(@PathVariable int id) {

        return ResponseEntity.ok().body(empresaBusiness.findById(id));
    }

    @PostMapping
    public ResponseEntity<Empresa> post(@RequestBody Empresa empresa){
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaBusiness.save(empresa));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        empresaBusiness.deleteById(id);
    }

    @GetMapping("/paginado")
    public List<Empresa> getAllEmployees(@RequestParam(defaultValue = "0", required = false) Integer numeroPagina)
    {
//        Pageable paging = PageRequest.of(1, 10, Sort.by("nome").descending());
      // @RequestParam(defaultValue = "0") Integer pageNo
        Pageable paging = PageRequest.of (numeroPagina, 10);//, Sort.by("nome").descending());
        Page<Empresa> pagedResult = empresaBusiness.listapaginada(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Empresa>();
        }
    }

}
