package com.gestionusuario.app.service;

import java.util.List;

import com.gestionusuario.app.entity.Perfil;
import com.gestionusuario.app.entity.Usuario;

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
	
	public String obtenerCorreo(Long idVerificador) throws Exception;
	
	public String findPerfilByUsuarioId(Long idUsuario) throws Exception;
	
	public String obtenerCorreoUTD() throws Exception;
	

}
