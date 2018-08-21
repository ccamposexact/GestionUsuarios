package com.gestionusuario.app.repository.impl;



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
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("perfiles.AsignarPermisosAPerfil");
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

	@Override
	public boolean BorrarPermisosDePerfil(Long idPerfil) throws Exception {
		boolean rpta=false;
		try {
				StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("perfiles.BorrarPermisosDePerfil");
				spq.setParameter("idPerfil", idPerfil);
				spq.execute();
				rpta=true;
				em.close();
			
		} catch (Exception e) {
				e.printStackTrace();
			}
		
		return rpta;
	}

	@Override
	public int ValidarPermisosExistentes(Long idPermiso) throws Exception {
		int rpta=0;
		try {
				StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("perfiles.ValidarPermisosExistentes");
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
	public int ValidarPerfilesExistentes(Long idPerfil) throws Exception {
		int rpta=0;
		try {
				StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("perfiles.ValidarPerfilesExistentes");
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
	public int ValidarPerfilActivo(Long idPerfil) throws Exception {
		int rpta=0;
		try {
				StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("perfiles.ValidarPerfilActivo");
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



}
