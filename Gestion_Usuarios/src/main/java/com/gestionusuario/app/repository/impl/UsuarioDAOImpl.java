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
	public int insertar(Usuario usuario) throws Exception {
		int rpta =0;
		
		try {
			
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("usuario.InsertarUsuario");
			spq.setParameter("nombre", usuario.getNombre());
			spq.setParameter("apellido", usuario.getApellido());
			spq.setParameter("correo", usuario.getCorreo());
			spq.setParameter("dni", usuario.getDni());
			spq.setParameter("matricula", usuario.getMatricula());
			spq.execute();
			Object ret = spq.getOutputParameterValue("idUsuario");
			rpta=Integer.parseInt(ret.toString());
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		
		return rpta;
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

	@Override
	public int ValidarUsuarioExistente(Long idUsuario) throws Exception {
		int rpta=0;
		try {
				StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("usuario.ValidarUsuarioExistente");
				spq.setParameter("idUsuario", idUsuario);
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
	public int ValidarUsuarioActivo(Long idUsuario) throws Exception {
		int rpta=0;
		try {
				StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("usuario.ValidarUsuarioActivo");
				spq.setParameter("idUsuario", idUsuario);
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
	public int ValidarDatosExistentes(String dni, String correo, String matricula) throws Exception {
		int rpta=0;
		int valor=0;
		try {
				StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("usuario.ValidarDatosExistentes");
				spq.setParameter("dni", dni);
				spq.setParameter("correo", correo);
				spq.setParameter("matricula", matricula);
				spq.execute();
				Object ret = spq.getOutputParameterValue("rpta");
				rpta=Integer.parseInt(ret.toString());
				em.close();
				switch(rpta) {
				case 1:
					valor=1;
					break;
				case 2:
					valor=2;
					break;
				case 3:
					valor=3;
					break;
				default:
					valor=4;
				}
		} catch (Exception e) {
				e.printStackTrace();
			}
		return valor;
	}
	
	




}
