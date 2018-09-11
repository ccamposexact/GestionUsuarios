package com.gestionusuario.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.gestionusuario.app.entity.Usuario;

public interface LoginUsuarioDAO extends CrudRepository<Usuario, Long> {

	Usuario findByUsername(String username);
	
}
