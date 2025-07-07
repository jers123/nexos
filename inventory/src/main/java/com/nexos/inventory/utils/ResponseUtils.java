package com.nexos.inventory.utils;

import com.nexos.inventory.model.dto.BaseDTO;
import com.nexos.inventory.model.dto.response.ReplyMessageList;
import com.nexos.inventory.model.dto.response.ReplyMessageSimple;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static com.nexos.inventory.constants.SystemConstants.ACCEPT;
import static com.nexos.inventory.constants.SystemConstants.LOCATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class ResponseUtils {

	public static <BDTO extends BaseDTO> ResponseEntity<ReplyMessageSimple<BDTO>> answerSimple(ReplyMessageSimple<BDTO> replyMessage) {
		return ResponseEntity
				.status(replyMessage.getHttpStatus())
				.header(LOCATION, replyMessage.getUri())
				.header(ACCEPT, APPLICATION_JSON_VALUE)
				.body(replyMessage);
	}

	public static <BDTO extends BaseDTO> ResponseEntity<ReplyMessageList<BDTO>> answerList(ReplyMessageList<BDTO> replyMessage) {
		return ResponseEntity
				.status(replyMessage.getHttpStatus())
				.header(LOCATION, replyMessage.getUri())
				.header(ACCEPT, APPLICATION_JSON_VALUE)
				.body(replyMessage);
	}

	public static <BDTO extends BaseDTO> ReplyMessageSimple<BDTO> replyMessage(String uri, HttpStatus httpStatus, List<String> messages, BDTO response) {
		ReplyMessageSimple<BDTO> replyMessage = new ReplyMessageSimple<>();
		replyMessage.setUri(uri);
		replyMessage.setHttpStatus(httpStatus);
		replyMessage.setError(httpStatus.isError());
		replyMessage.setMessage(messages);
		replyMessage.setResponse(response);
		replyMessage.setDate(LocalDateTime.now());
		return replyMessage;
	}

	public static <BDTO extends BaseDTO> ReplyMessageList<BDTO> replyMessageList(String uri, HttpStatus httpStatus, List<String> messages, List<BDTO> response) {
		ReplyMessageList<BDTO> replyMessage = new ReplyMessageList<>();
		replyMessage.setUri(uri);
		replyMessage.setHttpStatus(httpStatus);
		replyMessage.setError(httpStatus.isError());
		replyMessage.setMessage(messages);
		replyMessage.setResponse(response);
		replyMessage.setDate(LocalDateTime.now());
		return replyMessage;
	}
}