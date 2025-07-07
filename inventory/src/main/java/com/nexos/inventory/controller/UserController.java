package com.nexos.inventory.controller;

import com.nexos.inventory.annotation.RestApi;
import com.nexos.inventory.model.dto.response.ReplyMessageList;
import com.nexos.inventory.model.dto.response.ReplyMessageSimple;
import com.nexos.inventory.model.dto.user.UserCreateDTO;
import com.nexos.inventory.model.dto.user.UserUpdateDTO;
import com.nexos.inventory.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.nexos.inventory.constants.SystemConstants.INVENTORY_PATH;
import static com.nexos.inventory.constants.SystemConstants.USER_PATH;
import static com.nexos.inventory.utils.ResponseUtils.answerList;
import static com.nexos.inventory.utils.ResponseUtils.answerSimple;

@RestApi
@RequestMapping(path = INVENTORY_PATH + USER_PATH)
public class UserController implements IBaseController<UserCreateDTO, UserUpdateDTO> {

	@Autowired
	private IBaseService<UserCreateDTO, UserUpdateDTO> service;

	@Override
	public ResponseEntity<ReplyMessageSimple<UserUpdateDTO>> create(UserCreateDTO entityDto) {
		return answerSimple(service.getCreate(entityDto));
	}

	@Override
	public ResponseEntity<ReplyMessageList<UserUpdateDTO>> getAll() {
		return answerList(service.getFindAll());
	}

	@Override
	public ResponseEntity<ReplyMessageSimple<UserUpdateDTO>> getById(Integer id) {
		return answerSimple(service.getFindById(id));
	}

	@Override
	public ResponseEntity<ReplyMessageSimple<UserUpdateDTO>> update(UserUpdateDTO entityDto) {
		return answerSimple(service.getUpdate(entityDto));
	}

	@Override
	public ResponseEntity<ReplyMessageSimple<UserUpdateDTO>> delete(Integer id) {
		return answerSimple(service.getDelete(id));
	}
}