package com.nexos.inventory.model.dto.response;

import com.nexos.inventory.model.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReplyMessageList<BDTO extends BaseDTO> extends ReplyMessage {
	private List<BDTO> response;
}