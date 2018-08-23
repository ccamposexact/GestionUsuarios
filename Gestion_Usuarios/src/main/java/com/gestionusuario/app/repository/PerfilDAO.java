package com.gestionusuario.app.repository;



import com.gestionusuario.app.entity.Perfil;

public interface PerfilDAO extends GenericDAO<Perfil>{
	
	public int ValidarPermisos(Long idUsuario, Long idPermiso) throws Exception;
	
	public int ValidarFormatoPerfil(String nombre) throws Exception;
	
	public boolean AsignarPermisosAPerfil(Long idPerfil, Long idPermiso) throws Exception;
		
	public boolean BorrarPermisosAPerfil(Long idPerfil ) throws Exception;
	
	public int ValidarPermisosExistentes (Long idPermiso) throws Exception ;
	
	public int ValidarPerfilExistente(Long idPerfil) throws Exception;
	
	public int ValidarPerfilActivo(Long idPerfil) throws Exception;
	
	public int ValidarSiActivaDesactivaPerfil(Long idPerfil, int activo) throws Exception;
	
}
