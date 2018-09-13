package com.gestionusuario.app.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;

import com.gestionusuario.app.repository.PermisoDAO;

@Repository("permisodao")
public class PermisoDAOimpl implements PermisoDAO{
	
	
	@PersistenceContext
	private EntityManager em;


	@Override
	public int ObtenerPermisos(Long idUsuario) throws Exception {

		int sw = 0;

		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("permisos.ObtenerPermisos");
			spq.setParameter("idUsuario", idUsuario);
			spq.execute();
			sw=1;
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sw;
	}

}
