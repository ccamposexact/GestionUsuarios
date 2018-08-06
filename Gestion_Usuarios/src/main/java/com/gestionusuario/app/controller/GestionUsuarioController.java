package com.gestionusuario.app.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gestionusuario.app.bean.PerfilBean;
import com.gestionusuario.app.entity.Perfil;
import com.gestionusuario.app.service.PerfilService;

@Controller
@RequestMapping("/gestionusuario")
public class GestionUsuarioController {
	
	@Autowired
	private PerfilService perfilservice;
	
	
	
	public PerfilService getPerfilservice() {
		return perfilservice;
	}



	public void setPerfilservice(PerfilService perfilservice) {
		this.perfilservice = perfilservice;
	}



	@RequestMapping(value = "/agregarPerfil", consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	public @ResponseBody String agregarRequestBody(@RequestBody PerfilBean perfilbean){
		
		Perfil perfil = new Perfil();
		
		try {
			
			BeanUtils.copyProperties(perfil,perfilbean );
			this.getPerfilservice().insertar(perfil);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "{\"ret\":\"SE registro Perfil\"}";
		
	}
	

	

}
