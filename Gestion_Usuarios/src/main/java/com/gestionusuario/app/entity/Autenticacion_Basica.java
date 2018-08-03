package com.gestionusuario.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="AutenticacionBasica")
public class Autenticacion_Basica implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idAutenticacion_Basica;
	
	private String usuario;
	private String contrasena;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fAsignada;
	
	@OneToOne
	private Usuario usuarioAutBasica;

	public Long getIdAutenticacion_Basica() {
		return idAutenticacion_Basica;
	}

	public void setIdAutenticacion_Basica(Long idAutenticacion_Basica) {
		this.idAutenticacion_Basica = idAutenticacion_Basica;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Date getfAsignada() {
		return fAsignada;
	}

	public void setfAsignada(Date fAsignada) {
		this.fAsignada = fAsignada;
	}

	public Usuario getUsuarioAutBasica() {
		return usuarioAutBasica;
	}

	public void setUsuarioAutBasica(Usuario usuarioAutBasica) {
		this.usuarioAutBasica = usuarioAutBasica;
	}
	
	

}
