package com.gestionusuario.app.repository;

public interface GenericDAO<T> {
	
	public int insertar(T objeto) throws Exception;
	public int modificar(T objeto) throws Exception;
	public boolean eliminar (T objeto) throws Exception;

}
