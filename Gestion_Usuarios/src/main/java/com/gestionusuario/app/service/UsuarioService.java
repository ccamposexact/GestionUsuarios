package com.gestionusuario.app.service;

import java.util.List;

import com.gestionusuario.app.entity.Usuario;

public interface UsuarioService extends GenericService<Usuario>{

	public List<Usuario> ListarUsuarioPorNombre(String nombre) throws Exception;
	
	public boolean validarUsuario(Usuario usuario) throws Exception;
	
	public int ValidarUsuarioExistente(Long idUsuario) throws Exception;
	
	public int ValidarUsuarioActivo(Long idUsuario) throws Exception;
	
	public int ValidarDatosExistentes(String dni, String correo, String matricula) throws Exception;
	
	public boolean AsignarPerfilAUsuario(Long idUsuario, Long idPerfil) throws Exception;
}
