package br.com.fcpaiva.javachallenge.controllers;

import br.com.fcpaiva.javachallenge.dto.UsuarioDto;
import br.com.fcpaiva.javachallenge.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping()
    public ResponseEntity<List<UsuarioDto>> listarTodosUsuarios() {
        List<UsuarioDto> usuarioDtoList = this.usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarioDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> buscarUsuarios(@PathVariable Long id) {
        UsuarioDto usuarioDto = this.usuarioService.buscarUsuarioId(id);
        return ResponseEntity.ok(usuarioDto);
    }

    @PostMapping()
    public ResponseEntity<UsuarioDto> salvarUsuario(@RequestBody UsuarioDto usuarioDto) {
        UsuarioDto usuario = this.usuarioService.salvarUsuarios(usuarioDto);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> atualizarUsuario(@PathVariable Long id,@RequestBody UsuarioDto usuarioDto) {
        UsuarioDto usuario = this.usuarioService.atualizarUsuario(id, usuarioDto);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirUsuario(@PathVariable Long id){
        boolean usuarioExcluido = this.usuarioService.excluirUsuario(id);
        if(usuarioExcluido){
            return ResponseEntity.ok("Usuario excluido com sucesso!");
        }
        return ResponseEntity.notFound().build();
    }
}
