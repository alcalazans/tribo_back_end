package com.alcalazans.tribo.repository;

import com.alcalazans.tribo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByEmail(String email);

	Optional<Usuario> findById(Long id);

}
