package com.gestionusuario.app.dao;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gestionusuario.app.entity.Usuario;
import com.gestionusuario.app.entity.Usuario_Perfil;

@Repository
public interface IUsuarioPerfilDao extends CrudRepository<Usuario_Perfil, Long> {

	@Query("SELECT u.correo FROM Usuario u WHERE u IN(SELECT up.usuario FROM Usuario_Perfil up WHERE"
			+ " up.perfil.idPerfil = ?1)")
	public Iterable<String> obtenerCorreoAutorizador(Long idPerfil);
	
}
