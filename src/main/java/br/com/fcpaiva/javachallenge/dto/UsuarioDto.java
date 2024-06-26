package br.com.fcpaiva.javachallenge.dto;

import br.com.fcpaiva.javachallenge.convert.EntityOutputConvert;
import br.com.fcpaiva.javachallenge.entity.UsuarioEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {
    private Long id;
    private String nome;
    private String email;

    public static UsuarioEntity toEntity(UsuarioDto usuarioDto){
        return EntityOutputConvert.convert(usuarioDto, UsuarioEntity.class);
    }

    public static List<UsuarioDto> fromEntityList(List<UsuarioEntity> usuarioEntityList) {
        return usuarioEntityList.stream().map(UsuarioDto::fromEntity).toList();
    }

    public static UsuarioDto fromEntity(UsuarioEntity usuarioEntity) {
        return EntityOutputConvert.convert(usuarioEntity, UsuarioDto.class);
    }
}
