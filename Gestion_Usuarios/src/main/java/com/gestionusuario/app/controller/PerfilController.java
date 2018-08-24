																																																																																																																																																																																																																																																																																																																																																																																																																																																													package com.gestionusuario.app.controller;

import java.util.ArrayList;

import org.apache.catalina.mapper.Mapper;
import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestionusuario.app.entity.Perfil;
import com.gestionusuario.app.entity.Usuario;
import com.gestionusuario.app.enumerator.PermisosLista;
import com.gestionusuario.app.service.PerfilService;
import com.gestionusuario.app.service.UsuarioService;

@Controller
@RequestMapping("/perfil")
public class PerfilController {
	
	@Autowired
	private PerfilService perfilservice;
	
	@Autowired
	private UsuarioService usuarioservice;
	
	
	public UsuarioService getUsuarioservice() {
		return usuarioservice;
	}



	public void setUsuarioservice(UsuarioService usuarioservice) {
		this.usuarioservice = usuarioservice;
	}



	public PerfilService getPerfilservice() {
		return perfilservice;
	}



	public void setPerfilservice(PerfilService perfilservice) {
		this.perfilservice = perfilservice;
	}



	@RequestMapping(value = "/CrearPerfil", consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	public @ResponseBody String crearPerfil(@RequestBody String request) throws Exception {
		
		int rpta;
		int validar;
		int existe;
		int uactivo;
		
		ObjectMapper mapper = new ObjectMapper();
		JSONObject requestJson = new JSONObject(request);		
		String idUsuario = requestJson.getString("idUsuario");
		Perfil perfil = mapper.readValue(requestJson.getString("Perfil"), Perfil.class);
	
		existe=this.getUsuarioservice().ValidarUsuarioExistente(Long.parseLong(idUsuario));
		
		if(existe==1) {
			
			uactivo = this.getUsuarioservice().ValidarUsuarioActivo(Long.parseLong(idUsuario));
			
			if(uactivo==1) {
				
				rpta=this.getPerfilservice().ValidarPermisos(Long.parseLong(idUsuario),PermisosLista.CreadorPerfil);
				
				if(rpta==1) 
				{	
					validar=this.getPerfilservice().ValidarFormatoPerfil(perfil.getNombre());
					
					switch(validar) {
					case 1: return "{\"RPTA\":\"NOMBRE DE PERFIL INCORRECTO\"}";
					case 2: return "{\"RPTA\":\"FORMATO DE NOMBRE INCORRECTO (SOLO LETRAS)\"}";
					default:
						
							try {
								this.getPerfilservice().insertar(perfil);
								} catch (Exception e) {
								e.printStackTrace();
							}
							return "{\"RPTA\":\"SE REGISTRO PERFIL\"}";
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
	
	@RequestMapping(value = "/AsignarPermisosPerfiles", consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	public @ResponseBody String AsignarPermisosPerfiles(@RequestBody String request) throws Exception {
		
		int existe=0;
		int vperm=0;
		int uexiste;
		int uactivo=0;
		int pexiste=0;
		boolean perm=false;
		ArrayList<Integer> Lista = new ArrayList<Integer>();
		JSONObject requestJson = new JSONObject(request);
		String idUsuario = requestJson.getString("idUsuario");
		String idPerfil = requestJson.getString("idPerfil");
		JSONArray lstPermisos = requestJson.getJSONArray("Permisos");
		perm=lstPermisos.isNull(0);
		
		uexiste=this.getUsuarioservice().ValidarUsuarioExistente(Long.parseLong(idUsuario));
		
		if(uexiste==1) {
			uactivo = this.getUsuarioservice().ValidarUsuarioActivo(Long.parseLong(idUsuario));
			if(uactivo==1) {
				vperm=this.getPerfilservice().ValidarPermisos(Long.parseLong(idUsuario) ,PermisosLista.AsignadorPermisos);
				if(vperm==1) 
				{
					
					if(pexiste==1) {

						if(perm) 
						{
							this.getPerfilservice().BorrarPermisosAPerfil(Long.parseLong(idPerfil));
							return "{\"RPTA\":\"SE ELIMINARON LOS PERMISOS \"}";
						}
						else
						{
							for(int i = 0; i < lstPermisos.length(); ++i) {
								JSONObject rec = lstPermisos.getJSONObject(i);
								Long id =rec.getLong("idPermiso");
								existe=getPerfilservice().ValidarPermisosExistentes(id);
								Lista.add(existe);
							}
							if(Lista.contains(0)) 
							{
								return "{\"RPTA\":\"EL PERMISO QUE INTENTA AGREGAR NO EXISTE\"}";
							}
							else 
							{
								this.getPerfilservice().BorrarPermisosAPerfil(Long.parseLong(idPerfil));
								for (int i = 0; i < lstPermisos.length(); ++i) {
									JSONObject rec = lstPermisos.getJSONObject(i);
									Long id =rec.getLong("idPermiso");
									this.getPerfilservice().AsignarPermisosAPerfil(Long.parseLong(idPerfil),id);
								}
								return "{\"RPTA\":\"EL PERFIL HA SIDO ACTUALIZADO CORRECTAMENTE\"}";
							}
						}
					
					}
					else
					{
						return "{\"RPTA\":\"NO EXISTE EL PERFIL EN LA BASE DE DATOS\"}";
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
	
	
	@RequestMapping(value = "/ModificarPerfil", consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8", method = RequestMethod.PATCH)
	public @ResponseBody String ModificarPerfil(@RequestBody String request) throws Exception{
		
		int rpta = 0;
		int validar= 0;
		int existe= 0;
		int uexiste= 0;
		int uactivo= 0;
		int pexiste= 0;
		
		ObjectMapper mapper = new ObjectMapper();
		JSONObject requestJson = new JSONObject(request);
		String idUsuario = requestJson.getString("idUsuario");
		String idPerfil = requestJson.getString("idPerfil");
		Perfil perfil= mapper.readValue(requestJson.getString("Perfil"), Perfil.class);
		
		System.out.println("ESTE ES: " + perfil.getNombre());
		
		uexiste=this.getUsuarioservice().ValidarUsuarioExistente(Long.parseLong(idUsuario));
		System.out.println("ESTE ES: " + perfil.getNombre());
		if(uexiste==1) {
			
			uactivo = this.getUsuarioservice().ValidarUsuarioActivo(Long.parseLong(idUsuario));
			System.out.println("ESTE ES: " + perfil.getNombre());
			if(uactivo==1) 
			{
				pexiste=this.getPerfilservice().ValidarPerfilExistente(Long.parseLong(idPerfil));
				System.out.println("ESTE ES: " + perfil.getNombre());
				if(pexiste==1) 
				{
					rpta=this.getPerfilservice().ValidarPermisos(Long.parseLong(idUsuario) ,PermisosLista.ModificadorPerfil);
					System.out.println("ESTE ES: " + perfil.getNombre());
					if(rpta==1)
					{
						validar=this.getPerfilservice().ValidarFormatoPerfil(perfil.getNombre());
						System.out.println("ESTE ES: " + perfil.getNombre());
						System.out.println("ESTE ES: " + validar);
						switch(validar) {
						case 1: return "{\"RPTA\":\"EL NOMBRE DE PERFIL ESTA VACIO\"}";
						case 2: return "{\"RPTA\":\"EL NOMBRE DEL PERFIL YA EXISTE EN LA BASE DE DATOS\"}";
						case 3: return "{\"RPTA\":\"FORMATO DE NOMBRE INCORRECTO (SOLO LETRAS)\"}";
						default:
						
								existe=this.perfilservice.modificar(perfil);
								if(existe==0) 
								{
									return "{\"RPTA\":\"NO EXISTE PERFIL\"}";
								}
														
								return "{\"RPTA\":\"REGISTROS ACTUALIZADOS\"}";
							
							
						}
					}
					else 
					{
						return "{\"RPTA\":\"EL USUARIO NO TIENE EL PERMISO PARA REALIZAR ESTA ACCIÓN\"}";
					}
				}
				else 
				{
					return "{\"RPTA\":\"NO EXISTE EL PERFIL EN LA BASE DE DATOS\"}";
				}
			}
			else 
			{
				return "{\"RPTA\":\"EL USUARIO QUE INTENTA REALIZAR LA CREACION ESTA DESACTIVADO\"}";
			}
		}
		return "{\"RPTA\":\"EL USUARIO QUE INTENTA REALIZAR LA CREACION NO EXISTE\"}";
		
	}
	
	@RequestMapping(value = "/ModificarEstado", produces = "application/json; charset=utf-8", method = RequestMethod.PATCH)
	public @ResponseBody String ModificarEstado(@RequestBody String request) throws Exception {
	
		int existe=0;
		int uactivo=0;
		int permiso=0;
		int estado=0;
		int pexiste=0;
		JSONObject requestJson = new JSONObject(request);
		String idUsuario = requestJson.getString("idUsuario");
		String idPerfil = requestJson.getString("idPerfil");
		String activo = requestJson.getString("activo");
		//perfil.setIdPerfil(Long.parseLong(idPerfil));
		
		existe=this.getUsuarioservice().ValidarUsuarioExistente(Long.parseLong(idUsuario));

		if(existe==1) 
		{
			uactivo = this.getUsuarioservice().ValidarUsuarioActivo(Long.parseLong(idUsuario));
			if(uactivo==1) 
			{
				permiso=this.getPerfilservice().ValidarPermisos(Long.parseLong(idUsuario),PermisosLista.ModificadorEstadoPerfiles);
				if(permiso==1)
				{
					
					pexiste=this.getPerfilservice().ValidarPerfilExistente(Long.parseLong(idPerfil));
					
					if(pexiste==1) {
						
						estado = this.getPerfilservice().ValidarSiActivaDesactivaPerfil(Long.parseLong(idPerfil), Integer.parseInt(activo));
						switch (estado) 
						{
						case 1:
							return "{\"RPTA\":\"EL PERFIL YA SE ENCUENTRA ACTIVADO\"}";
						case 2:
							return "{\"RPTA\":\"EL PERFIL YA SE ENCUENTRA DESACTIVADO\"}";
						case 3:
							return "{\"RPTA\":\"LA ACTIVACIÓN DEL PERFIL SE LOGRÓ CON ÉXITO\"}";
						default:
							return "{\"RPTA\":\"SE REALIZÓ LA DESACTIVACIÓN DEL PERFIL\"}";
						}
						
					}
					else
					{
						return "{\"RPTA\":\"NO EXISTE EL PERFIL EN LA BASE DE DATOS\"}";
					}
					
					
				}
				return "{\"RPTA\":\"EL USUARIO NO TIENE EL PERMISO PARA MODIFICAR\"}";
			}
			else 
			{
				return "{\"RPTA\":\"EL USUARIO QUE INTENTA REALIZAR LA CREACION ESTA DESACTIVADO\"}";
			}
		}	
		return "{\"RPTA\":\"EL USUARIO QUE INTENTA REALIZAR LA CREACION NO EXISTE\"}";
	}

	
	
	
	
	
}
