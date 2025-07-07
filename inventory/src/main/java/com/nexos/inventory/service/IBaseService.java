package com.nexos.inventory.service;

import com.nexos.inventory.model.dto.BaseDTO;
import com.nexos.inventory.model.dto.response.ReplyMessageList;
import com.nexos.inventory.model.dto.response.ReplyMessageSimple;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public interface IBaseService <BC extends BaseDTO, BU extends BaseDTO> {
	@Transactional()
	ReplyMessageSimple<BU> getCreate(BC entityDto);

	@Transactional(readOnly = true)
	ReplyMessageList<BU> getFindAll();

	@Transactional(readOnly = true)
	ReplyMessageSimple<BU> getFindById(Integer id);

	@Transactional()
	ReplyMessageSimple<BU> getUpdate(BU entityDto);

	@Transactional()
	ReplyMessageSimple<BU> getDelete(Integer id);

	default String getUri() {
		String url = "";
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (attrs != null) {
			HttpServletRequest request = attrs.getRequest();
			url = request.getRequestURI();
		}
		return url;
	}
}