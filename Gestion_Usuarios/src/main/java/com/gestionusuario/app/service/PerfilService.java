package com.gestionusuario.app.service;

import com.gestionusuario.app.entity.Perfil;

public interface PerfilService extends GenericService<Perfil> {
	
	
	public int ValidarModificarPermisos(Long idUsuario, Long idPermiso, Long idPerfil) throws Exception;
	
	public int ValidarPermisos(Long idUsuario, Long idPermiso) throws Exception;

	public int ValidarFormatoPerfil(String nombre) throws Exception;
	
	public boolean AsignarPermisosAPerfiles(Long idPerfil, Long idPermiso) throws Exception;
	
	public boolean QuitarPermisosAPerfiles(Long idPerfil, Long idPermiso) throws Exception;
	
	public int ValidarAsignacionPermisos(Long idPerfil, Long idPermiso) throws Exception;
	
	public boolean BorrarPermisosDePerfil(Long idPerfil ) throws Exception;
	
	public int ValidarPermisosExistentes (Long idPermiso) throws Exception ;
	
	public boolean AgregarPermisosAPerfil(Long idPerfil, Long idPermiso) throws Exception;
	
	

}
