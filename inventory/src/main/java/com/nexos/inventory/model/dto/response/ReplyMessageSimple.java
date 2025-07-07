package com.nexos.inventory.model.dto.response;

import com.nexos.inventory.model.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyMessageSimple<BDTO extends BaseDTO> extends ReplyMessage {
	private BDTO response;
}