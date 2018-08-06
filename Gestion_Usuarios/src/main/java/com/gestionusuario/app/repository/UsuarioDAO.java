package com.gestionusuario.app.repository;

import java.util.List;

import com.gestionusuario.app.entity.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario> {

	public List<Usuario> ListarUsuarioPorNombre(String nombre) throws Exception;
	
	
	
	
}
