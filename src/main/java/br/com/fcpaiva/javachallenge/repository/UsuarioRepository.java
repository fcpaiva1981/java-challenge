package br.com.fcpaiva.javachallenge.repository;

import br.com.fcpaiva.javachallenge.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
}
