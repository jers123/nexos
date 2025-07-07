package com.nexos.inventory.controller;

import com.nexos.inventory.model.dto.BaseDTO;
import com.nexos.inventory.model.dto.response.ReplyMessageList;
import com.nexos.inventory.model.dto.response.ReplyMessageSimple;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.nexos.inventory.constants.Constants.ID_VALUE_MINIMUM;
import static com.nexos.inventory.constants.SystemConstants.CREATE_PATH;
import static com.nexos.inventory.constants.SystemConstants.DELETE_PATH;
import static com.nexos.inventory.constants.SystemConstants.GET_ALL_PATH;
import static com.nexos.inventory.constants.SystemConstants.GET_ID_PATH;
import static com.nexos.inventory.constants.SystemConstants.ID;
import static com.nexos.inventory.constants.SystemConstants.UPDATE_PATH;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface IBaseController<BC extends BaseDTO, BU extends BaseDTO> {
	@PostMapping(value = CREATE_PATH, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	ResponseEntity<ReplyMessageSimple<BU>> create(@Valid @RequestBody BC entityDto);

	@GetMapping(GET_ALL_PATH)
	ResponseEntity<ReplyMessageList<BU>> getAll();

	@GetMapping(value = GET_ID_PATH + "{" + ID + "}", produces = APPLICATION_JSON_VALUE)
	ResponseEntity<ReplyMessageSimple<BU>> getById(@PathVariable(ID) @Min(value = 1, message = ID_VALUE_MINIMUM) Integer id);

	@PutMapping(value = UPDATE_PATH, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	ResponseEntity<ReplyMessageSimple<BU>> update(@Valid @RequestBody BU entityDto);

	@DeleteMapping(value = DELETE_PATH + "{" + ID + "}", produces = APPLICATION_JSON_VALUE)
	ResponseEntity<ReplyMessageSimple<BU>> delete(@PathVariable(ID) @Min(value = 1, message = ID_VALUE_MINIMUM) Integer id);
}