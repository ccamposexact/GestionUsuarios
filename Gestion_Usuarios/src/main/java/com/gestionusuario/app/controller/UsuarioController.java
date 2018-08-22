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
									this.getUsuarioservice().insertar(usuario);
									return "{\"RPTA\":\"SE INSERTO EL USUARIO\"}";
								}
								else
								{
									return "{\"RPTA\":\"EL PERFIL NO ESTÁ ACTIVO\"}";
								}
							}
							else 
							{	
							return "{\"RPTA\":\"EL PERFIL NO EXISTE \"}";
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
	
	
}
