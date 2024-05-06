package br.com.fcpaiva.javachallenge.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "tb_privilegios")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissoesEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column (length=255)
    private String descricao;
}
