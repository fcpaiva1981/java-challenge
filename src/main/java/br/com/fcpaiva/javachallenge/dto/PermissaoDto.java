package br.com.fcpaiva.javachallenge.dto;

import br.com.fcpaiva.javachallenge.convert.EntityOutputConvert;
import br.com.fcpaiva.javachallenge.entity.PermissoesEntity;
import br.com.fcpaiva.javachallenge.entity.UsuarioEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class PermissaoDto {
    private Long id;
    private String descricao;

    public static PermissoesEntity toEntity(PermissaoDto permissaoDto){
        PermissoesEntity permissoesEntity = EntityOutputConvert.convert(permissaoDto, PermissoesEntity.class);
        return permissoesEntity;
    }

    public static List<PermissaoDto> fromEntityList(List<PermissoesEntity> permissoesEntityList) {
        return permissoesEntityList.stream().map(PermissaoDto::fromEntity).toList();
    }

    public static PermissaoDto fromEntity(PermissoesEntity permissoesEntity) {
        var permissaoDto = EntityOutputConvert.convert(permissoesEntity, PermissaoDto.class);
        return permissaoDto;
    }
}
