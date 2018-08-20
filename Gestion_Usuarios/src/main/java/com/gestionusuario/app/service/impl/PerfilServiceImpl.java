package com.gestionusuario.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionusuario.app.entity.Perfil;
import com.gestionusuario.app.repository.PerfilDAO;
import com.gestionusuario.app.service.PerfilService;


@Service("perfilservice")
public class PerfilServiceImpl implements PerfilService{
	
	@Autowired
	private PerfilDAO perfildao;
	
	@Override
	public int insertar(Perfil perfil) throws Exception {
		
		try {
			return this.getPerfildao().insertar(perfil);
			
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public int modificar(Perfil perfil) throws Exception {
		try {
			return this.getPerfildao().modificar(perfil);
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public boolean eliminar(Perfil perfil) throws Exception {
		try {
			return this.getPerfildao().eliminar(perfil);
		} catch (Exception e) {
			throw new Exception();
		}
	}
	

	public PerfilDAO getPerfildao() {
		return perfildao;
	}

	public void setPerfildao(PerfilDAO perfildao) {
		this.perfildao = perfildao;
	}



	@Override
	public int ValidarFormatoPerfil(String nombre) throws Exception {
		try {
			return this.perfildao.ValidarFormatoPerfil(nombre);
		} catch (Exception e) {
			throw new Exception(e); 
		}
	}

	@Override
	public boolean AsignarPermisosAPerfiles(Long idPerfil, Long idPermiso) throws Exception {
		try {
			return this.perfildao.AsignarPermisosAPerfiles(idPerfil, idPermiso);
		} catch (Exception e) {
			throw new Exception(e); 
		}
	}

	@Override
	public boolean QuitarPermisosAPerfiles(Long idPerfil, Long idPermiso) throws Exception {
		try {
			return this.perfildao.QuitarPermisosAPerfiles(idPerfil, idPermiso);
		}catch (Exception e) {
			throw new Exception(e); 
		}
	}

	@Override
	public int ValidarAsignacionPermisos(Long idPerfil, Long idPermiso) throws Exception {
		try {
			return this.perfildao.ValidarAsignacionPermisos(idPerfil, idPermiso);
		}catch (Exception e) {
			throw new Exception(e); 
		}
	}

	@Override
	public int ValidarModificarPermisos(Long idUsuario, Long idPermiso, Long idPerfil) throws Exception {
		try {
			return this.perfildao.ValidarModificarPermisos(idUsuario, idPermiso, idPerfil);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public int ValidarPermisos(Long idUsuario, Long idPermiso) throws Exception {
		try {
			return this.perfildao.ValidarPermisos(idUsuario, idPermiso);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public boolean BorrarPermisosDePerfil(Long idPerfil) throws Exception {
		try {
			return this.perfildao.BorrarPermisosDePerfil(idPerfil);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public int ValidarPermisosExistentes(Long idPermiso) throws Exception {
		try {
			return this.perfildao.ValidarPermisosExistentes(idPermiso);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public boolean AgregarPermisosAPerfil(Long idPerfil, Long idPermiso) throws Exception {
		try {
			return this.perfildao.AgregarPermisosAPerfil(idPerfil, idPermiso);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	

}
