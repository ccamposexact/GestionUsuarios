package com.gestionusuario.app.service;

import com.gestionusuario.app.entity.Perfil;

public interface PerfilService extends GenericService<Perfil> {
	
	
	public int ValidarPermisos(Long idUsuario, Long idPermiso) throws Exception;
	
	

}
