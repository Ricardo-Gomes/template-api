package com.oliveira.templateapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oliveira.templateapi.model.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);

    boolean existsByUsername(String username);
}
