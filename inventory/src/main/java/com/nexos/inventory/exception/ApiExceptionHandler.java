package com.nexos.inventory.exception;

import com.nexos.inventory.model.dto.BaseDTO;
import com.nexos.inventory.model.dto.response.ReplyMessageSimple;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nexos.inventory.constants.Constants.HTTP_MESSAGE1;
import static com.nexos.inventory.constants.Constants.HTTP_MESSAGE2;
import static com.nexos.inventory.constants.Constants.ID_VALUE_MINIMUM;
import static com.nexos.inventory.constants.Constants.INCORRECT_JSON;
import static com.nexos.inventory.utils.ResponseUtils.answerSimple;
import static com.nexos.inventory.utils.ResponseUtils.replyMessage;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler({HttpRequestMethodNotSupportedException.class})
	@ResponseBody
	public <BDTO extends BaseDTO> ResponseEntity<ReplyMessageSimple<BDTO>> httpRequestMethodException(HttpRequestMethodNotSupportedException exception, WebRequest webRequest) {
		List<String> messages = new ArrayList<>();
		messages.add(HTTP_MESSAGE1 + supportedMethods(exception) + HTTP_MESSAGE2 + exception.getMethod());
		return answerSimple(replyMessage(webRequest.getDescription(false).replace("uri=",""), HttpStatus.METHOD_NOT_ALLOWED, messages, null));
	}

	@ExceptionHandler({MethodArgumentNotValidException.class})
	@ResponseBody
	public <BDTO extends BaseDTO> ResponseEntity<ReplyMessageSimple<BDTO>> validationFieldsException(MethodArgumentNotValidException exception, WebRequest webRequest) {
		Map<String, String> mapErrors = new HashMap<>();
		exception.getBindingResult().getAllErrors().forEach((error) -> {
			String clave = ((FieldError) error).getField();
			String valor = error.getDefaultMessage();
			mapErrors.put(clave, valor);
		});
		Collection<String> errors = mapErrors.values();
		List<String> messages = new ArrayList<>(errors);
		return answerSimple(replyMessage(webRequest.getDescription(false).replace("uri=",""), HttpStatus.BAD_REQUEST, messages, null));
	}

	@ExceptionHandler({HttpMessageNotReadableException.class})
	@ResponseBody
	public <BDTO extends BaseDTO> ResponseEntity<ReplyMessageSimple<BDTO>> validationFieldsExceptionBySQL(HttpMessageNotReadableException exception, WebRequest webRequest) {
		List<String> messages = new ArrayList<>();
		String error = exception.getMessage().trim();
		error = error.replace("JSON parse error: ", "");
		if(error.substring(0,20).equals("Unexpected character")) {
			messages.add(INCORRECT_JSON);
		}
		if(error.substring(0,32).equals("Cannot deserialize value of type")) {
			String[] errors = error.split(" ");
			errors[5] = errors[5].replace("`", "");
			errors[5] = errors[5].split("\\.")[2];
			messages.add("No se puede guardar un valor de tipo " + errors[7] + " en una variable de tipo " + errors[5]);
		}
		return answerSimple(replyMessage(webRequest.getDescription(false).replace("uri=",""), HttpStatus.BAD_REQUEST, messages, null));
	}

	@ExceptionHandler({MethodArgumentTypeMismatchException.class})
	@ResponseBody
	public <BDTO extends BaseDTO> ResponseEntity<ReplyMessageSimple<BDTO>> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception, WebRequest webRequest) {
		List<String> messages = new ArrayList<>();
		messages.add("Se esperaba que el parámetro " + exception.getName() + " recibiera un tipo de dato " +
				exception.getRequiredType().getSimpleName() + " pero recibió un tipo de dato " + exception.getValue().getClass().getSimpleName());

		return answerSimple(replyMessage(webRequest.getDescription(false).replace("uri=",""), HttpStatus.BAD_REQUEST, messages, null));
	}

	@ExceptionHandler({HandlerMethodValidationException.class})
	@ResponseBody
	public <BDTO extends BaseDTO> ResponseEntity<ReplyMessageSimple<BDTO>> handlerMethodValidationException(HandlerMethodValidationException exception, WebRequest webRequest) {
		List<String> messages = new ArrayList<>();
		messages.add(ID_VALUE_MINIMUM);

		return answerSimple(replyMessage(webRequest.getDescription(false).replace("uri=",""), HttpStatus.BAD_REQUEST, messages, null));
	}

	@ExceptionHandler({DataAccessException.class})
	@ResponseBody
	public <BDTO extends BaseDTO> ResponseEntity<ReplyMessageSimple<BDTO>> handlerMethodValidationException(DataAccessException exception, WebRequest webRequest) {
		List<String> messages = new ArrayList<>();
		messages.add(exception.getMessage());

		return answerSimple(replyMessage(webRequest.getDescription(false).replace("uri=",""), HttpStatus.CONFLICT, messages, null));
	}

	@ExceptionHandler({DataIntegrityViolationException.class})
	@ResponseBody
	public <BDTO extends BaseDTO> ResponseEntity<ReplyMessageSimple<BDTO>> handlerMethodValidationException(DataIntegrityViolationException exception, WebRequest webRequest) {
		List<String> messages = new ArrayList<>();
		messages.add(exception.getMessage());

		return answerSimple(replyMessage(webRequest.getDescription(false).replace("uri=",""), HttpStatus.CONFLICT, messages, null));
	}

	@ExceptionHandler({EntityNotFoundException.class})
	@ResponseBody
	public <BDTO extends BaseDTO> ResponseEntity<ReplyMessageSimple<BDTO>> handlerMethodValidationException(EntityNotFoundException exception, WebRequest webRequest) {
		List<String> messages = new ArrayList<>();
		messages.add(exception.getMessage());

		return answerSimple(replyMessage(webRequest.getDescription(false).replace("uri=",""), HttpStatus.NOT_FOUND, messages, null));
	}

	@ExceptionHandler({Exception.class})
	@ResponseBody
	public <BDTO extends BaseDTO> ResponseEntity<ReplyMessageSimple<BDTO>> handlerMethodValidationException(Exception exception, WebRequest webRequest) {
		List<String> messages = new ArrayList<>();
		messages.add(exception.getMessage());

		return answerSimple(replyMessage(webRequest.getDescription(false).replace("uri=",""), HttpStatus.INTERNAL_SERVER_ERROR, messages, null));
	}

	private String supportedMethods(HttpRequestMethodNotSupportedException exception) {
		String method = exception.getSupportedHttpMethods().toString();
		method = method.replace("[","");
		method = method.replace("]","");
		return method;
	}
}