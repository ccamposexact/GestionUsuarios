package com.gestionusuario.app.repository;

public interface GenericDAO<T> {
	
	public boolean insertar(T objeto) throws Exception;
	public boolean modificar(T objeto) throws Exception;
	public boolean eliminar (T objeto) throws Exception;

}
