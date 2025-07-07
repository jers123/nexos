package com.nexos.inventory.controller;

import com.nexos.inventory.annotation.RestApi;
import com.nexos.inventory.model.dto.position.PositionCreateDTO;
import com.nexos.inventory.model.dto.position.PositionUpdateDTO;
import com.nexos.inventory.model.dto.response.ReplyMessageList;
import com.nexos.inventory.model.dto.response.ReplyMessageSimple;
import com.nexos.inventory.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.nexos.inventory.constants.SystemConstants.INVENTORY_PATH;
import static com.nexos.inventory.constants.SystemConstants.POSITION_PATH;
import static com.nexos.inventory.utils.ResponseUtils.answerList;
import static com.nexos.inventory.utils.ResponseUtils.answerSimple;

@RestApi
@RequestMapping(path = INVENTORY_PATH + POSITION_PATH)
public class PositionController implements IBaseController<PositionCreateDTO, PositionUpdateDTO> {

	@Autowired
	private IBaseService<PositionCreateDTO, PositionUpdateDTO> service;

	@Override
	public ResponseEntity<ReplyMessageSimple<PositionUpdateDTO>> create(PositionCreateDTO entityDto) {
		return answerSimple(service.getCreate(entityDto));
	}

	@Override
	public ResponseEntity<ReplyMessageList<PositionUpdateDTO>> getAll() {
		return answerList(service.getFindAll());
	}

	@Override
	public ResponseEntity<ReplyMessageSimple<PositionUpdateDTO>> getById(Integer id) {
		return answerSimple(service.getFindById(id));
	}

	@Override
	public ResponseEntity<ReplyMessageSimple<PositionUpdateDTO>> update(PositionUpdateDTO entityDto) {
		return answerSimple(service.getUpdate(entityDto));
	}

	@Override
	public ResponseEntity<ReplyMessageSimple<PositionUpdateDTO>> delete(Integer id) {
		return answerSimple(service.getDelete(id));
	}
}