package com.gestionusuario.app.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

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
			
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("perfiles.insertar");
			spq.setParameter("nombre", perfil.getNombre());
			spq.setParameter("descripcion", perfil.getDescripcion());
			
			spq.execute();
			
			sw=true;
			
			em.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		
		return sw;
	}

	@Override
	public boolean modificar(Perfil objeto) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Perfil objeto) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Perfil> ListarPerfiles() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
