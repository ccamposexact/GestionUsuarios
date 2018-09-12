package com.gestionusuario.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionusuario.app.repository.PermisoDAO;
import com.gestionusuario.app.service.PermisoService;


@Service("permisoservice")
public class PermisoServiceImpl implements PermisoService{
	
	
	@Autowired
	private PermisoDAO permisodao;
	

	
	
	public PermisoDAO getPermisodao() {
		return permisodao;
	}




	public void setPermisodao(PermisoDAO permisodao) {
		this.permisodao = permisodao;
	}




	@Override
	public int ObtenerPermisos(Long idUsuario) throws Exception {
		try {
			return this.getPermisodao().ObtenerPermisos(idUsuario);
		} catch (Exception e) {
			throw new Exception();
		}
	}

}
