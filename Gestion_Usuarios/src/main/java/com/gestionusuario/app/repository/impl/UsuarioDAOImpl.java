package com.gestionusuario.app.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;

import com.gestionusuario.app.entity.Usuario;
import com.gestionusuario.app.repository.UsuarioDAO;

@Repository("usuariodao")
public class UsuarioDAOImpl implements UsuarioDAO {

	@PersistenceContext
	private EntityManager em;
	@Override
	public int insertar(Usuario objeto) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modificar(Usuario objeto) throws Exception {
		// TODO Auto-generated method stub
		return 0;
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
	
		boolean sw=false;
		
		try {
			
			
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("usuario.validar");
			
			spq.setParameter("id", usuario.getIdUsuario());
			
			spq.execute();
			Object rpta =spq.getOutputParameterValue(1);
			if(rpta!=null) {
				if(rpta=="1") {
					sw=true;
				}
			}
			
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sw;
	}
	
	




}
