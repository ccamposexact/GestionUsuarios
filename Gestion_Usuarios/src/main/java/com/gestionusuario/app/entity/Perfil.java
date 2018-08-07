package com.gestionusuario.app.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
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

import org.hibernate.annotations.ColumnDefault;

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
								@StoredProcedureParameter(mode=ParameterMode.IN,name="nombre", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,name="descripcion", type=String.class),
							}					
					),
				@NamedStoredProcedureQuery(
						name="perfiles.eliminar", 
						procedureName="EliminarPerfiles",
						resultClasses= Perfil.class,
						parameters={
								@StoredProcedureParameter(mode=ParameterMode.IN,name="id",type=Long.class),
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
	
	@ManyToMany
	private List<Permiso> permisos;
	
	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}
	
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

	@Override
	public String toString() {
		return "Perfil [idPerfil=" + idPerfil + ", nombre=" + nombre + ", fCreacion=" + fCreacion + ", descripcion="
				+ descripcion + ", activo=" + activo + ", permisos=" + permisos + "]";
	}

	
	
	
	
	
	
	

}
