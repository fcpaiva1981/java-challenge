package br.com.fcpaiva.javachallenge.controllers;

import br.com.fcpaiva.javachallenge.dto.PermissaoDto;
import br.com.fcpaiva.javachallenge.service.PermissoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissoes")

public class PermissoesController {

    private final PermissoesService permissoesService;

    @Autowired
    public PermissoesController(PermissoesService permissoesService) {
        this.permissoesService = permissoesService;
    }

    @GetMapping()
    public ResponseEntity<List<PermissaoDto>> listarTodasPermissoes() {
        List<PermissaoDto> PermissaoDtoList = this.permissoesService.listarPermissoes();
        return ResponseEntity.ok(PermissaoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissaoDto> buscarPermissoes(@PathVariable Long id) {
        PermissaoDto PermissaoDto = this.permissoesService.buscarPermissaoId(id);
        return ResponseEntity.ok(PermissaoDto);
    }

    @PostMapping()
    public ResponseEntity<PermissaoDto> salvarPermissao(@RequestBody PermissaoDto permissaoDto) {
        PermissaoDto permissao = this.permissoesService.salvarPermissao(permissaoDto);
        return ResponseEntity.ok(permissao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PermissaoDto> atualizarPermissao(@PathVariable Long id,@RequestBody PermissaoDto permissaoDto) {
        PermissaoDto usuario = this.permissoesService.atualizarPermissao(id, permissaoDto);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirUsuario(@PathVariable Long id){
        boolean permissaoExcluida = this.permissoesService.excluirPermissao(id);
        if(permissaoExcluida){
            return ResponseEntity.ok("Permissão excluída com sucesso!");
        }
        return ResponseEntity.notFound().build();
    }
}
