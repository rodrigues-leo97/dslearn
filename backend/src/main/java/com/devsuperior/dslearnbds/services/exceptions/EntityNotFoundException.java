package com.devsuperior.dslearnbds.services.exceptions;

public class EntityNotFoundException extends RuntimeException{
	//Exception -> exceção obrigatoriamente tratada
	//RunTimeException -> não precisa ser necessariamente tratada, pode ou não
	
	private static final long serialVersionUID = 1L;
	
	public EntityNotFoundException(String msg) {
		super(msg); //super -> repassa a mensagem para o construtor do RunTimeException(super classe)
	}

}
