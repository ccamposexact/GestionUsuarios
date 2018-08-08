package com.gestionusuario.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionusuario.app.entity.Usuario;
import com.gestionusuario.app.repository.UsuarioDAO;
import com.gestionusuario.app.service.UsuarioService;

@Service("usuarioservice")
public class UsuarioServiceImpl  implements UsuarioService{

	@SuppressWarnings("uncheked")
	@Autowired
	private UsuarioDAO usuariodao;
	
	
	
	public UsuarioDAO getUsuariodao() {
		return usuariodao;
	}

	public void setUsuariodao(UsuarioDAO usuariodao) {
		this.usuariodao = usuariodao;
	}

	@Override
	public boolean insertar(Usuario objeto) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificar(Usuario objeto) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Usuario objeto) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Usuario> ListarUsuarioPorNombre(String nombre) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validarUsuario(Usuario usuario) throws Exception {
		try {
			return this.getUsuariodao().validarUsuario(usuario);
		} catch (Exception e) {
			throw new Exception();
		}
	}

}
