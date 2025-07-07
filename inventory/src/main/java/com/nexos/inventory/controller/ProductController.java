package com.nexos.inventory.controller;

import com.nexos.inventory.annotation.RestApi;
import com.nexos.inventory.model.dto.product.ProductCreateDTO;
import com.nexos.inventory.model.dto.product.ProductUpdateDTO;
import com.nexos.inventory.model.dto.response.ReplyMessageList;
import com.nexos.inventory.model.dto.response.ReplyMessageSimple;
import com.nexos.inventory.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.nexos.inventory.constants.SystemConstants.INVENTORY_PATH;
import static com.nexos.inventory.constants.SystemConstants.PRODUCT_PATH;
import static com.nexos.inventory.utils.ResponseUtils.answerList;
import static com.nexos.inventory.utils.ResponseUtils.answerSimple;

@RestApi
@RequestMapping(path = INVENTORY_PATH + PRODUCT_PATH)
public class ProductController implements IBaseController<ProductCreateDTO, ProductUpdateDTO> {

	@Autowired
	private IBaseService<ProductCreateDTO, ProductUpdateDTO> service;

	@Override
	public ResponseEntity<ReplyMessageSimple<ProductUpdateDTO>> create(ProductCreateDTO entityDto) {
		return answerSimple(service.getCreate(entityDto));
	}

	@Override
	public ResponseEntity<ReplyMessageList<ProductUpdateDTO>> getAll() {
		return answerList(service.getFindAll());
	}

	@Override
	public ResponseEntity<ReplyMessageSimple<ProductUpdateDTO>> getById(Integer id) {
		return answerSimple(service.getFindById(id));
	}

	@Override
	public ResponseEntity<ReplyMessageSimple<ProductUpdateDTO>> update(ProductUpdateDTO entityDto) {
		return answerSimple(service.getUpdate(entityDto));
	}

	@Override
	public ResponseEntity<ReplyMessageSimple<ProductUpdateDTO>> delete(Integer id) {
		return answerSimple(service.getDelete(id));
	}
}