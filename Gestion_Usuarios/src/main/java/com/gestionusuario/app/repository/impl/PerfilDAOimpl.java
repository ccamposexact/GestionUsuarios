package com.gestionusuario.app.repository.impl;

import java.util.List;


import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;


import org.springframework.stereotype.Repository;

import com.gestionusuario.app.entity.Perfil;

import com.gestionusuario.app.repository.PerfilDAO;


@Repository("perfildao")
public class PerfilDAOimpl implements PerfilDAO{
	
	@PersistenceContext
	private EntityManager em;
	
	

	@SuppressWarnings("uncheked")
	@Override
	public int insertar(Perfil perfil) throws Exception {
		int rpta =0;
		
		try {
			
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("perfiles.InsertarPerfiles");
			spq.setParameter("nombre", perfil.getNombre());
			spq.setParameter("descripcion", perfil.getDescripcion());
			spq.execute();
			Object ret = spq.getOutputParameterValue("idPerfil");
			rpta=Integer.parseInt(ret.toString());
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		
		return rpta;
	}

	@Override
	public int modificar(Perfil perfil) throws Exception {
		int rpta=0;
		
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("perfiles.ModificarPerfiles");
			spq.setParameter("idPerfil", perfil.getIdPerfil());
			spq.setParameter("nombre", perfil.getNombre());
			spq.setParameter("descripcion", perfil.getDescripcion());
			spq.execute();
			Object ret = spq.getOutputParameterValue("rpta");
			rpta=Integer.parseInt(ret.toString());
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return rpta;
	}

	@Override
	public boolean eliminar(Perfil perfil) throws Exception {
		
		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("perfiles.DesactivarPerfiles");
			spq.setParameter("idPerfil", perfil.getIdPerfil());
			spq.execute();
			em.close();
			sw=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sw;
	}

	@Override
	public List<Perfil> ListarPerfiles() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public int ValidarFormatoPerfil(String nombre) throws Exception {
		int rpta=0;
		try {
				StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("perfiles.ValidarFormatoPerfil");
				spq.setParameter("nombre", nombre);
				spq.execute();
				Object ret = spq.getOutputParameterValue("rpta");
				rpta=Integer.parseInt(ret.toString());
				em.close();
			
		} catch (Exception e) {
				e.printStackTrace();
			}
		
		return rpta;
	}

	@Override
	public boolean AsignarPermisosAPerfiles(Long idPerfil, Long idPermiso) throws Exception {
		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("perfiles.AsignarPermisosAPerfiles");
			spq.setParameter("idPerfil", idPerfil);
			spq.setParameter("idPermiso", idPermiso);
			spq.execute();
			em.close();
			sw=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sw;
	}

	@Override
	public boolean QuitarPermisosAPerfiles(Long idPerfil, Long idPermiso) throws Exception {
		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("perfiles.QuitarPermisosAPerfiles");
			spq.setParameter("idPerfil", idPerfil);
			spq.setParameter("idPermiso", idPermiso);
			spq.execute();
			em.close();
			sw=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sw;
	}

	@Override
	public int ValidarAsignacionPermisos(Long idPerfil, Long idPermiso) throws Exception {
		int rpta=0;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("perfiles.ValidarAsignacionPermisos");
			spq.setParameter("idPerfil", idPerfil);
			spq.setParameter("idPermiso", idPermiso);
			spq.execute();
			Object ret = spq.getOutputParameterValue("rpta");
			rpta=Integer.parseInt(ret.toString());
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rpta;
		
	}

	@Override
	public int ValidarModificarPermisos(Long idUsuario, Long idPermiso, Long idPerfil) throws Exception {

		int rpta=0;
		try {
				StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("perfiles.ValidarModificarPermisos");
				spq.setParameter("idUsuario", idUsuario);
				spq.setParameter("idPermiso", idPermiso);
				spq.setParameter("idPerfil", idPerfil);
				spq.execute();
				Object ret = spq.getOutputParameterValue("rpta");
				rpta=Integer.parseInt(ret.toString());
				em.close();
			
		} catch (Exception e) {
				e.printStackTrace();
			}
		
		return rpta;
	}

	@Override
	public int ValidarPermisos(Long idUsuario, Long idPermiso) throws Exception {
		int rpta=0;
		try {
				StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("perfiles.ValidarPermisos");
				spq.setParameter("idUsuario", idUsuario);
				spq.setParameter("idPermiso", idPermiso);
				spq.execute();
				Object ret = spq.getOutputParameterValue("rpta");
				rpta=Integer.parseInt(ret.toString());
				em.close();
			
		} catch (Exception e) {
				e.printStackTrace();
			}
		
		return rpta;
	}


}
