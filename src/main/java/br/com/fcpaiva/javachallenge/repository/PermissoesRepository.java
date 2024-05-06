package br.com.fcpaiva.javachallenge.repository;


import br.com.fcpaiva.javachallenge.entity.PermissoesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissoesRepository extends JpaRepository<PermissoesEntity, Long> {
}
