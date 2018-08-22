package com.gestionusuario.app.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestionusuario.app.entity.Usuario;
import com.gestionusuario.app.enumerator.PermisosLista;
import com.gestionusuario.app.service.PerfilService;
import com.gestionusuario.app.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioservice;
	
	@Autowired
	private PerfilService perfilservice;
	

	public PerfilService getPerfilservice() {
		return perfilservice;
	}

	public void setPerfilservice(PerfilService perfilservice) {
		this.perfilservice = perfilservice;
	}

	public UsuarioService getUsuarioservice() {
		return usuarioservice;
	}

	public void setUsuarioservice(UsuarioService usuarioservice) {
		this.usuarioservice = usuarioservice;
	}
	
	@RequestMapping(value = "/CrearUsuario", consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	public @ResponseBody String CrearUsuario(@RequestBody String request) throws Exception {
		
		int existe=0;
		int uactivo=0;
		int aut=0;
		int valor=0;
		int perf=0;
		int pactivo=0;
		Integer idusr=0;
		
		ObjectMapper mapper = new ObjectMapper();
		JSONObject requestJson = new JSONObject(request);
		String idUsuario = requestJson.getString("idUsuario");
		Usuario usuario = mapper.readValue(requestJson.getString("Usuario"), Usuario.class);
		String idPerfil = requestJson.getString("idPerfil");
		
		existe=this.getUsuarioservice().ValidarUsuarioExistente(Long.parseLong(idUsuario));
		
		if(existe==1) 
		{
			uactivo=this.getUsuarioservice().ValidarUsuarioActivo(Long.parseLong(idUsuario));
			if(uactivo==1)
			{
				aut=this.getPerfilservice().ValidarPermisos(Long.parseLong(idUsuario), PermisosLista.CreadorUsuarios);
				if(aut==1) 
				{
					if((usuario.getNombre()!="") & (usuario.getApellido()!="") & (usuario.getMatricula()!="") & (usuario.getDni()!="") & (usuario.getCorreo()!=""))
					{
						valor=this.getUsuarioservice().ValidarDatosExistentes(usuario.getDni(), usuario.getCorreo(),usuario.getMatricula() );
						switch(valor) {
						case 1:
							return "{\"RPTA\":\"DNI REPETIDO\"}";
						case 2:
							return "{\"RPTA\":\"MATRICULA REPETIDO\"}";
						case 3:
							return "{\"RPTA\":\"CORREO REPETIDO\"}";
						default:
							perf=this.getPerfilservice().ValidarPerfilExistente(Long.parseLong(idPerfil));
							if(perf==1)
							{
								pactivo=this.getPerfilservice().ValidarPerfilActivo(Long.parseLong(idPerfil));
								if(pactivo==1)
								{
									idusr=this.getUsuarioservice().insertar(usuario);
									
									this.getUsuarioservice().AsignarPerfilAUsuario(Long.valueOf(idusr.longValue()), Long.parseLong(idPerfil));
									return "{\"RPTA\":\"SE INSERTO EL USUARIO\"}";
								}
								else
								{
									return "{\"RPTA\":\"EL PERFIL NO ESTÁ ACTIVO\"}";
								}
							}
							else 
							{	
							return "{\"RPTA\":\"EL PERFIL NO EXISTEE \"}";
							}
						}
									
					}
					else {
						return "{\"RPTA\":\"POR FAVOR, REGISTRE TODOS LOS DATOS DEL USUARIO\"}";
					}
				}
				else 
				{
					return "{\"RPTA\":\"EL USUARIO NO TIENE EL PERMISO PARA REALIZAR ESTA ACCIÓN\"}";
				}
			}
			else 
			{
				return "{\"RPTA\":\"EL USUARIO QUE INTENTA REALIZAR LA CREACION ESTA DESACTIVADO\"}";	
			}
		}
		
		return "{\"RPTA\":\"EL USUARIO QUE INTENTA REALIZAR LA CREACION NO EXISTE\"}";
	}
	
	
	@RequestMapping(value = "/ModificarEstado", consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	public @ResponseBody String ModificarEstado(@RequestBody String request) throws Exception 
	{
		
		int existe = 0; //si existe el que ejecuta
		int uactivo = 0;// si esta activo el que ejecuta
		int permiso = 0; //si tiene permiso el que ejecuta
		int estado = 0; //
		
		ObjectMapper mapper = new ObjectMapper();
		JSONObject requestJson = new JSONObject(request);
		String idUsuario = requestJson.getString("idUsuario");
		String idUsuarioDest = requestJson.getString("idUsuarioDest");
		String activo = requestJson.getString("activo");
		
		existe=this.getUsuarioservice().ValidarUsuarioExistente(Long.parseLong(idUsuario));
		System.out.println("Este es : " + existe );
		if(existe==1) 
		{
			
			uactivo=this.getUsuarioservice().ValidarUsuarioActivo(Long.parseLong(idUsuario));
			System.out.println("Este es : " + uactivo );
			if(uactivo==1)
			{
				
				permiso=this.getPerfilservice().ValidarPermisos(Long.parseLong(idUsuario), PermisosLista.ModificadorEstadoUsuarios);
				System.out.println("Este es : " + permiso );
				if(permiso==1) 
				{
					
					if(valor==1)
						this.getUsuarioservice().ValidarSiActivaDesactiva(a, Integer.parseInt(activo));
					else
						this.getUsuarioservice().ValidarSiActivaDesactiva(b, Integer.parseInt(activo));
					
					
					
					estado=this.getUsuarioservice().ValidarSiActivaDesactiva(Long.parseLong(idUsuarioDest), Integer.parseInt(activo));
					System.out.println("Este es : " + estado );
					switch(estado) 
					{
					case 1:
						return "{\"RPTA\":\"EL USUARIO YA SE ENCUENTRA ACTIVADO\"}"; 
					case 2:
						return "{\"RPTA\":\"EL USUARIO YA SE ENCUENTRA DESACTIVADO\"}";
					case 3:
						return "{\"LA ACTIVACIÓN DEL USUARIO SE LOGRÓ CON ÉXITO\"}";
					default:
						return "{\"RPTA\":\"SE REALIZÓ LA DESACTIVACIÓN DEL USUARIO\"}";
					}
				}
				else 
				{
					return "{\"RPTA\":\"EL USUARIO NO TIENE EL PERMISO PARA MODIFICAR\"}";
				}
			}
			else 
			{
				return "{\"RPTA\":\"EL USUARIO QUE INTENTA REALIZAR LA MODIFICACION ESTA DESACTIVADO\"}";
			}
		}
		
		return "{\"RPTA\":\"EL USUARIO QUE INTENTA REALIZAR LA CREACION NO EXISTE\"}";
	}
	
	
}
