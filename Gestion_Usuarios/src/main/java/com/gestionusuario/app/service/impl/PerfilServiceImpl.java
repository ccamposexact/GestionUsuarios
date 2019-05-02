package com.gestionusuario.app.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionusuario.app.dao.IUsuarioPerfilDao;
import com.gestionusuario.app.entity.Perfil;
import com.gestionusuario.app.entity.Usuario;
import com.gestionusuario.app.repository.PerfilDAO;
import com.gestionusuario.app.service.PerfilService;
import static com.gestionusuario.app.enumerator.PerfilesEnum.GESTION_DOCUMENTAL;
import static com.gestionusuario.app.enumerator.PerfilesEnum.SUPERVISOR;
import static com.gestionusuario.app.enumerator.VerificadorUsuarioEnum.EXPRESS;
import static com.gestionusuario.app.enumerator.VerificadorUsuarioEnum.REGULAR;
import static com.gestionusuario.app.enumerator.VerificadorUsuarioEnum.ESPECIAL;



@Service("perfilservice")
public class PerfilServiceImpl implements PerfilService{
	
	@Autowired
	private PerfilDAO perfildao;
	
	@Autowired
	IUsuarioPerfilDao usuarioPerfilDao;
	
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
	public boolean AsignarPermisosAPerfil(Long idPerfil, Long idPermiso) throws Exception {
		try {
			return this.perfildao.AsignarPermisosAPerfil(idPerfil, idPermiso);
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
	public boolean BorrarPermisosAPerfil(Long idPerfil) throws Exception {
		try {
			return this.perfildao.BorrarPermisosAPerfil(idPerfil);
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
	public int ValidarPerfilExistente(Long idPerfil) throws Exception {
		try {
			return this.perfildao.ValidarPerfilExistente(idPerfil);	
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public int ValidarPerfilActivo(Long idPerfil) throws Exception {
		try {
			return this.perfildao.ValidarPerfilActivo(idPerfil);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public int ValidarSiActivaDesactivaPerfil(Long idPerfil, int activo) throws Exception {
		try {
			return this.perfildao.ValidarSiActivaDesactivaPerfil(idPerfil, activo);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public String ObtenerPerfil(Long idUsuario) throws Exception {
		try {
			return this.perfildao.ObtenerPerfil(idUsuario);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public int ObtenerPerfilID(Long idUsuario) throws Exception {
		try {
			return this.perfildao.ObtenerPerfilID(idUsuario);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public String obtenerCorreo(Long idVerificador) throws Exception {
		
		Iterable<String> correos = null;
		
		if(idVerificador==REGULAR || idVerificador==ESPECIAL) {
			correos = usuarioPerfilDao.obtenerCorreoGD(GESTION_DOCUMENTAL);
		}else if(idVerificador==EXPRESS) {
			correos = usuarioPerfilDao.obtenerCorreoGDAndSuper(GESTION_DOCUMENTAL, SUPERVISOR);
		}
		List<String> correolst = StreamSupport.stream(correos.spliterator(), false).collect(Collectors.toList());
		return  StreamSupport.stream(correolst.spliterator(), false).collect(Collectors.joining(", "));
		
	}

	@Override
	public String findPerfilByUsuarioId(Long idUsuario) throws Exception {
		return usuarioPerfilDao.findPerfilByUsuarioId(idUsuario);
		
	}

}
