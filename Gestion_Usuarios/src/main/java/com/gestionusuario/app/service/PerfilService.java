package com.gestionusuario.app.service;

import com.gestionusuario.app.entity.Perfil;

public interface PerfilService extends GenericService<Perfil> {
	
	public int ValidarPermisos(Long idUsuario, Long idPermiso) throws Exception;
	
	public int ValidarFormatoPerfil(String nombre) throws Exception;
	
	public boolean AsignarPermisosAPerfil(Long idPerfil, Long idPermiso) throws Exception;
		
	public boolean BorrarPermisosAPerfil(Long idPerfil ) throws Exception;
	
	public int ValidarPermisosExistentes (Long idPermiso) throws Exception ;
	
	public int ValidarPerfilExistente(Long idPerfil) throws Exception;
	
	public int ValidarPerfilActivo(Long idPerfil) throws Exception;
	
	public int ValidarSiActivaDesactivaPerfil(Long idPerfil, int activo) throws Exception;
	
	public String ObtenerPerfil(Long idUsuario) throws Exception;
	
	public int ObtenerPerfilID(Long idUsuario) throws Exception;

}
