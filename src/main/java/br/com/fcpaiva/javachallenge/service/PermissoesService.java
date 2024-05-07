package br.com.fcpaiva.javachallenge.service;

import br.com.fcpaiva.javachallenge.convert.EntityOutputConvert;
import br.com.fcpaiva.javachallenge.dto.PermissaoDto;
import br.com.fcpaiva.javachallenge.entity.PermissoesEntity;
import br.com.fcpaiva.javachallenge.entity.UsuarioEntity;
import br.com.fcpaiva.javachallenge.repository.PermissoesRepository;
import br.com.fcpaiva.javachallenge.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissoesService {

    @Autowired
    private PermissoesRepository permissoesRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public PermissoesService(PermissoesRepository permissoesRepository, UsuarioRepository usuarioRepository) {
        this.permissoesRepository = permissoesRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<PermissaoDto> listarPermissoes() {
        return PermissaoDto.fromEntityList(permissoesRepository.findAll(Sort.by("descricao")));
    }

    public PermissaoDto buscarPermissaoId(Long id) {
        PermissoesEntity permissoesEntity = permissoesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Permissão não encontrada"));
        PermissaoDto permissaoDto =  PermissaoDto.fromEntity(permissoesEntity);
        return permissaoDto;
    }

    public PermissaoDto salvarPermissao(PermissaoDto permissaoDto) {
        PermissoesEntity permissoesEntity = EntityOutputConvert.convert(permissaoDto, PermissoesEntity.class);
        UsuarioEntity usuario = usuarioRepository.findById(permissaoDto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não localizado"));
        permissoesEntity.setUsuarios(usuario);
        PermissoesEntity permissoesEntitySalva = permissoesRepository.save(permissoesEntity);
        return PermissaoDto.fromEntity(permissoesEntitySalva);
    }

    public PermissaoDto atualizarPermissao(Long id, PermissaoDto permissaoDto) {
        PermissoesEntity permissoesEntity = permissoesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Permissão não encontrada"));

        UsuarioEntity usuario = usuarioRepository.findById(permissaoDto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não localizado"));
        permissoesEntity.setDescricao(permissaoDto.getDescricao());
        permissoesEntity.setUsuarios(usuario);

        permissoesRepository.saveAndFlush(permissoesEntity);
        return PermissaoDto.fromEntity(permissoesEntity);
    }

    public boolean excluirPermissao(Long id) {
        Optional<PermissoesEntity> permissoesEntity = permissoesRepository.findById(id);

        if (permissoesEntity.isPresent()) {
            permissoesRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
