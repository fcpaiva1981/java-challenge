package br.com.fcpaiva.javachallenge.service;

import br.com.fcpaiva.javachallenge.convert.EntityOutputConvert;
import br.com.fcpaiva.javachallenge.dto.PermissaoDto;
import br.com.fcpaiva.javachallenge.dto.UsuarioDto;
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

    public PermissoesService(PermissoesRepository permissoesRepository) {
        this.permissoesRepository = permissoesRepository;
    }

    public List<PermissaoDto> listarPermissoes() {
        return PermissaoDto.fromEntityList(permissoesRepository.findAll(Sort.by("descricao")));
    }

    public PermissaoDto buscarPermissaoId(Long id) {
        PermissoesEntity permissoesEntity = permissoesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Permissão não encontrada"));
        PermissaoDto  permissaoDto = EntityOutputConvert.convert(permissoesEntity, PermissaoDto.class);
        return permissaoDto;
    }

    public PermissaoDto salvarPermissao(PermissaoDto permissaoDto) {
      PermissoesEntity permissoesEntity =EntityOutputConvert.convert(permissaoDto, PermissoesEntity.class);
      PermissoesEntity permissoesEntitySalva = permissoesRepository.save(permissoesEntity);
      return PermissaoDto.builder().id(permissoesEntitySalva.getId()).build();
    }

    public PermissaoDto atualizarPermissao(Long id, PermissaoDto permissaoDto) {
        PermissoesEntity permissoesEntity = permissoesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Permissão não encontrada"));
        permissaoDto.setId(permissoesEntity.getId());
        permissoesRepository.saveAndFlush(PermissaoDto.toEntity(permissaoDto));
        return permissaoDto;
    }

    public boolean excluirPermissao(Long id) {
        Optional<PermissoesEntity> permissoesEntity = permissoesRepository.findById(id);

        if(permissoesEntity.isPresent()) {
            permissoesRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
