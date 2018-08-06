package com.gestionusuario.app.entity;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

//mode=ParameterMode.REF_CURSOR,name="P_CURSOR", 

@NamedStoredProcedureQueries(
		{
				@NamedStoredProcedureQuery(
					name="usuario.listar", 
					procedureName="ListarUsuarios",
					resultClasses= Usuario.class,
					parameters={
								@StoredProcedureParameter(type=void.class )
						}					
				),
				@NamedStoredProcedureQuery(
						name="usuario.eliminar", 
						procedureName="EliminarUsuario",
						resultClasses= Usuario.class,
						parameters={
									@StoredProcedureParameter(type=void.class)
									
							}					
					)
		}
	)

@Entity
@Table(name="Usuarios")
public class Usuario extends GenericEntity  {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idUsuario;
	
	private String nombre;
	private String apellido;
	private String correo;
	
	
	
	public Usuario() {
		this.setEstado("1");
	}
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	
	public Usuario(Long idUsuario, String nombre, String apellido, String correo) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
	}
	
	
	
	
	
}
