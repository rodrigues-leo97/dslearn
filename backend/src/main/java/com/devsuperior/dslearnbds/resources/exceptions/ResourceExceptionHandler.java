package com.devsuperior.dslearnbds.resources.exceptions;


import com.devsuperior.dslearnbds.services.exceptions.BadRequestException;
import com.devsuperior.dslearnbds.services.exceptions.DataBaseException;
import com.devsuperior.dslearnbds.services.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException e, HttpServletRequest request) { //404 - notFound
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now()); //para pegar o instante ATUAL da request
		err.setStatus(HttpStatus.NOT_FOUND.value()); //404
		err.setError("Resource not found");
		err.setMessage(e.getMessage()); //pegando a mensagem passada no método findById para quando o erro estourar
		err.setPath(request.getRequestURI()); //pega o caminho da requisição feita. EX: "/categories/6"
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<StandardError> badRequest(BadRequestException e, HttpServletRequest request) {//erro 400(badRequest)
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.BAD_REQUEST.value()); //400 - badRequest
		err.setError("category already exists");
		err.setMessage(e.getMessage()); 
		err.setPath(request.getRequestURI()); 
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<StandardError> database(DataBaseException e, HttpServletRequest request) {//erro 400(badRequest)
		StandardError err = new StandardError();
		HttpStatus status = HttpStatus.BAD_REQUEST; //para não ficar repetitivo

		err.setTimestamp(Instant.now()); 
		err.setStatus(status.value()); //400 - badRequest
		err.setError("Database exception"); //para quando houver violação de integridade na base de dados
		err.setMessage(e.getMessage()); 
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {//erro 400(badRequest)
		ValidationError err = new ValidationError();
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY; //422 - alguma entidade não foi possível de ser processada

		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Database exception");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());

		//classe de erro personalizada pra tratar essa resposta
		for(FieldError f : e.getBindingResult().getFieldErrors()) {
			err.addError(f.getField(), f.getDefaultMessage());
		}

		return ResponseEntity.status(status).body(err);
	}
	
}
