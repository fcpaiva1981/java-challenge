package br.com.fcpaiva.javachallenge.service;

import br.com.fcpaiva.javachallenge.convert.EntityOutputConvert;
import br.com.fcpaiva.javachallenge.dto.UsuarioDto;
import br.com.fcpaiva.javachallenge.entity.UsuarioEntity;
import br.com.fcpaiva.javachallenge.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioDto> listarCarteiras() {
        return UsuarioDto.fromEntityList(usuarioRepository.findAll(Sort.by("nome")));
    }

    public UsuarioDto buscarUsuarioId(Long id) {
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        UsuarioDto  usuarioDto = EntityOutputConvert.convert(usuarioEntity, UsuarioDto.class);
        return usuarioDto;
    }

    public UsuarioDto salvarUsuarios(UsuarioDto usuarioDto) {
      UsuarioEntity usuarioEntity =EntityOutputConvert.convert(usuarioDto, UsuarioEntity.class);
      UsuarioEntity usuarioEntitySalvo = usuarioRepository.save(usuarioEntity);
      return UsuarioDto.builder().id(usuarioEntitySalvo.getId()).build();
    }

    public UsuarioDto atualizarUsuario(Long id, UsuarioDto usuarioDto) {
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        usuarioDto.setId(usuarioEntity.getId());
        usuarioRepository.saveAndFlush(UsuarioDto.toEntity(usuarioDto));
        return usuarioDto;
    }

    public boolean excluirUsuario(Long id) {
        Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findById(id);

        if(usuarioEntity.isPresent()) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
