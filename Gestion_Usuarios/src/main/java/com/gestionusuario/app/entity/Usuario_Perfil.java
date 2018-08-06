package com.gestionusuario.app.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="UsuarioPerfil")
public class Usuario_Perfil extends GenericEntity{

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idUsuario_Perfil;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fAsignada;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fBaja;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Perfil perfil;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Usuario usuario;
	
	
	public Usuario_Perfil() {
		this.setEstado("1");
	}

	public Long getIdUsuario_Perfil() {
		return idUsuario_Perfil;
	}
	
	

	public void setIdUsuario_Perfil(Long idUsuario_Perfil) {
		this.idUsuario_Perfil = idUsuario_Perfil;
	}

	public Date getfAsignada() {
		return fAsignada;
	}

	public void setfAsignada(Date fAsignada) {
		this.fAsignada = fAsignada;
	}

	public Date getfBaja() {
		return fBaja;
	}

	public void setfBaja(Date fBaja) {
		this.fBaja = fBaja;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	
	
}
