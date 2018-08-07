package com.gestionusuario.app.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ParameterMode;


@NamedStoredProcedureQueries(
		{
				@NamedStoredProcedureQuery(
					name="perfiles.listar", 
					procedureName="ListarPerfiles",
					resultClasses= Perfil.class,
					parameters={
								@StoredProcedureParameter(type=void.class )
						}					
				),
				@NamedStoredProcedureQuery(
						name="perfiles.insertar", 
						procedureName="InsertarPerfiles",
						resultClasses= Perfil.class,
						parameters={
								@StoredProcedureParameter(mode=ParameterMode.OUT,name="id_perfil", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,name="nombre", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,name="descripcion", type=String.class),
							}					
					)
		}
		
)


@Entity
@Table(name="Perfiles")

public class Perfil implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idPerfil;
	
	private String nombre;

	@Temporal(TemporalType.DATE)
	private Date fCreacion;
	
	private String descripcion;


	
	protected String activo;
	
	public Perfil() {
		
	}
	
	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	@ManyToMany
	private List<Permiso> permisos;

	public Long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getfCreacion() {
		return fCreacion;
	}

	public void setfCreacion(Date fCreacion) {
		this.fCreacion = fCreacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public List<Permiso> getPermisos() {
		return permisos;
	}

	public void setPermisos(List<Permiso> permisos) {
		this.permisos = permisos;
	}

	public Perfil(Long idPerfil, String nombre, Date fCreacion, String descripcion, String activo) {
		super();
		this.idPerfil = idPerfil;
		this.nombre = nombre;
		this.fCreacion = fCreacion;
		this.descripcion = descripcion;
		this.activo = activo;
		
	}
	
	
	
	

}
