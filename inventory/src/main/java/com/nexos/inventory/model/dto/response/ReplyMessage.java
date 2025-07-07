package com.nexos.inventory.model.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public abstract class ReplyMessage {
	private String uri;
	private HttpStatus httpStatus;
	private Boolean error;
	private List<String> message;
	private LocalDateTime date;
}