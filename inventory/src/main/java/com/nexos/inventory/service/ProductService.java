package com.nexos.inventory.service;

import com.nexos.inventory.mappers.IAdvancedMapper;
import com.nexos.inventory.model.dto.product.ProductCreateDTO;
import com.nexos.inventory.model.dto.product.ProductUpdateDTO;
import com.nexos.inventory.model.dto.response.ReplyMessageList;
import com.nexos.inventory.model.dto.response.ReplyMessageSimple;
import com.nexos.inventory.model.entity.Product;
import com.nexos.inventory.model.entity.User;
import com.nexos.inventory.repository.IProductRepository;
import com.nexos.inventory.repository.IUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

import static com.nexos.inventory.constants.Constants.ID_USER_CREATE_PRODUCT_NOT_EXISTS;
import static com.nexos.inventory.constants.Constants.ID_USER_UPDATE_PRODUCT_NOT_EXISTS;
import static com.nexos.inventory.constants.Constants.NO_CONTENT;
import static com.nexos.inventory.constants.Constants.NO_CONTENT_ID;
import static com.nexos.inventory.constants.Constants.SUCCESSFULLY_CREATED_PRODUCT;
import static com.nexos.inventory.constants.Constants.SUCCESSFULLY_DELETED_PRODUCT;
import static com.nexos.inventory.constants.Constants.SUCCESSFULLY_UPDATED_PRODUCT;
import static com.nexos.inventory.constants.Constants.PRODUCT_NAME_EXISTS;
import static com.nexos.inventory.constants.Constants.YES_CONTENT;
import static com.nexos.inventory.utils.ResponseUtils.replyMessage;
import static com.nexos.inventory.utils.ResponseUtils.replyMessageList;

@Service
@Validated
public class ProductService implements IBaseService<ProductCreateDTO, ProductUpdateDTO> {

	@Autowired
	private IProductRepository repository;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IAdvancedMapper<ProductCreateDTO, ProductUpdateDTO, Product> mapper;

	@Override
	public ReplyMessageSimple<ProductUpdateDTO> getCreate(ProductCreateDTO entityDto) {
		List<String> messages = new ArrayList<>();
		ProductUpdateDTO entityOutput = null;
		try {
			String name = repository.searchByName(0, entityDto.getName());
			User idUserCreate = userRepository.findById(entityDto.getIdUserCreate()).orElse(null);
			if (name != null) {
				throw new DataIntegrityViolationException(PRODUCT_NAME_EXISTS);
			}
			if (idUserCreate == null) {
				throw new EntityNotFoundException(ID_USER_CREATE_PRODUCT_NOT_EXISTS);
			}
			Product entity = new Product();
			entity.setIdUserCreate(idUserCreate);
			entity.setIdUserUpdate(idUserCreate);
			entity = mapper.create(entityDto, entity);
			entityOutput = mapper.read(repository.save(entity));
			messages.add(SUCCESSFULLY_CREATED_PRODUCT);
		} catch (Exception e) {
			throw e;
		}
		return replyMessage(getUri(), HttpStatus.CREATED, messages, entityOutput);
	}

	@Override
	public ReplyMessageList<ProductUpdateDTO> getFindAll() {
		List<String> messages = new ArrayList<>();
		List<ProductUpdateDTO> entitiesDto = new ArrayList<>();
		try {
			List<Product> entities = repository.searchAll();
			if (!entities.isEmpty()) {
				entitiesDto = mapper.readList(entities);
				messages.add(YES_CONTENT);
			} else {
				messages.add(NO_CONTENT);
			}
		} catch (Exception e) {
			throw e;
		}
		return replyMessageList(getUri(), HttpStatus.OK, messages, entitiesDto);
	}

	@Override
	public ReplyMessageSimple<ProductUpdateDTO> getFindById(Integer id) {
		List<String> messages = new ArrayList<>();
		ProductUpdateDTO entityDto = null;
		try {
			Product entity = repository.findById(id).orElse(null);
			if (entity != null) {
				entityDto = mapper.read(entity);
				messages.add(YES_CONTENT);
			} else {
				throw new EntityNotFoundException(NO_CONTENT_ID + id);
			}
		} catch (Exception e) {
			throw e;
		}
		return replyMessage(getUri(), HttpStatus.OK, messages, entityDto);
	}

	@Override
	public ReplyMessageSimple<ProductUpdateDTO> getUpdate(ProductUpdateDTO entityDto) {
		List<String> messages = new ArrayList<>();
		try {
			Product entity = repository.findById(entityDto.getIdProduct()).orElse(null);
			if (entity != null) {
				String name = repository.searchByName(entityDto.getIdProduct(), entityDto.getName());
				User idUserUpdate = userRepository.findById(entityDto.getIdUserUpdate()).orElse(null);
				if (name != null) {
					throw new DataIntegrityViolationException(PRODUCT_NAME_EXISTS);
				}
				if (idUserUpdate == null) {
					throw new EntityNotFoundException(ID_USER_UPDATE_PRODUCT_NOT_EXISTS);
				}
				entity.setIdUserUpdate(idUserUpdate);
				entity = mapper.update(entityDto, entity);
				entityDto = mapper.read(repository.save(entity));
				messages.add(SUCCESSFULLY_UPDATED_PRODUCT);
			} else {
				throw new EntityNotFoundException(NO_CONTENT_ID + entityDto.getIdProduct());
			}
		} catch (Exception e) {
			throw e;
		}
		return replyMessage(getUri(), HttpStatus.OK, messages, entityDto);
	}

	@Override
	public ReplyMessageSimple<ProductUpdateDTO> getDelete(Integer id) {
		List<String> messages = new ArrayList<>();
		try {
			Product entity = repository.findById(id).orElse(null);
			if (entity != null) {
				repository.delete(entity);
				messages.add(SUCCESSFULLY_DELETED_PRODUCT);
			} else {
				throw new EntityNotFoundException(NO_CONTENT_ID + id);
			}
		} catch (Exception e) {
			throw e;
		}
		return replyMessage(getUri(), HttpStatus.OK, messages, null);
	}
}