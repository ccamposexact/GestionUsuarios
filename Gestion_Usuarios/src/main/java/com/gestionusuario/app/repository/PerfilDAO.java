package com.gestionusuario.app.repository;

import java.util.List;

import com.gestionusuario.app.entity.Perfil;

public interface PerfilDAO extends GenericDAO<Perfil>{
	
	public List<Perfil> ListarPerfiles() throws Exception;
	
	public int ValidarPermisos(Long idUsuario, Long idPermiso) throws Exception;
	
	public int ValidarFormatoPerfil(String nombre) throws Exception;
	
	
	
}
