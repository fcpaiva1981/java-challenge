package br.com.fcpaiva.javachallenge.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "tb_permissoes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissoesEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column (length=255)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "fk_usuario_id")
    private UsuarioEntity usuarios;
}
