package com.gestionusuario.app.repository;

import java.util.List;

import com.gestionusuario.app.entity.Perfil;

public interface PerfilDAO extends GenericDAO<Perfil>{
	
	public List<Perfil> ListarPerfiles() throws Exception;
	
	public int ValidarPermisos(Integer idUsuario, int idPermiso) throws Exception;
	
	
	
}
