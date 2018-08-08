package com.gestionusuario.app.service;

import java.util.List;

import com.gestionusuario.app.entity.Usuario;

public interface UsuarioService extends GenericService<Usuario>{

	public List<Usuario> ListarUsuarioPorNombre(String nombre) throws Exception;
	
	public boolean validarUsuario(Usuario usuario) throws Exception;
}
