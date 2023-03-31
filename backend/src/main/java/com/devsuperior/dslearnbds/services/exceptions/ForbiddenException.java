package com.devsuperior.dslearnbds.services.exceptions;

public class ForbiddenException extends RuntimeException{ //usada para retornar o erro 403(usuário logado mas o perfil dele não é para aquele recurso), o 401 é para quando o token não é valido
	private static final long serialVersionUID = 1L;

	public ForbiddenException(String msg) {
		super(msg);
	}

}
