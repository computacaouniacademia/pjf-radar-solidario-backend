package com.radar.solidario.exception.user.alreadyExists;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.radar.solidario.util.Response;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class UserAlreadyExistsExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserAlreadyExistsException.class)
	public final ResponseEntity<Object> exceptionHandler(UserAlreadyExistsException exception) {
		log.error("UserAlreadyExistsException - Message: {}", exception);

		Response<Void> response = new Response<>();
		response.addError(exception.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
}
