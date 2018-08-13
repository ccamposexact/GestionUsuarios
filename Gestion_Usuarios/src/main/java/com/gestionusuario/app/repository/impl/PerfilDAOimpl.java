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
	public boolean insertar(Perfil perfil) throws Exception {
		boolean sw =false;
		
		try {
			
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("perfiles.InsertarPerfiles");
			spq.setParameter("nombre", perfil.getNombre());
			spq.setParameter("descripcion", perfil.getDescripcion());
			spq.execute();
			em.close();
			sw=true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		
		return sw;
	}

	@Override
	public boolean modificar(Perfil perfil) throws Exception {
		boolean sw=false;
		
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("perfiles.ModificarPerfiles");
			spq.setParameter("idPerfil", perfil.getIdPerfil());
			spq.setParameter("nombre", perfil.getNombre());
			spq.setParameter("descripcion", perfil.getDescripcion());
			spq.execute();
			em.close();
			sw=true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return sw;
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
	@SuppressWarnings("unchecked")
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
				//System.out.println("rpta"+rpta);
				em.close();
			
		} catch (Exception e) {
				e.printStackTrace();
			}
		
		return rpta;
	}


}
