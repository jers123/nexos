package com.nexos.inventory.service;

import com.nexos.inventory.mappers.IAdvancedMapper;
import com.nexos.inventory.model.dto.response.ReplyMessageList;
import com.nexos.inventory.model.dto.response.ReplyMessageSimple;
import com.nexos.inventory.model.dto.user.UserCreateDTO;
import com.nexos.inventory.model.dto.user.UserUpdateDTO;
import com.nexos.inventory.model.entity.Position;
import com.nexos.inventory.model.entity.User;
import com.nexos.inventory.repository.IPositionRepository;
import com.nexos.inventory.repository.IUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

import static com.nexos.inventory.constants.Constants.ID_POSITION_USER_NOT_EXISTS;
import static com.nexos.inventory.constants.Constants.NO_CONTENT;
import static com.nexos.inventory.constants.Constants.NO_CONTENT_ID;
import static com.nexos.inventory.constants.Constants.SUCCESSFULLY_CREATED_USER;
import static com.nexos.inventory.constants.Constants.SUCCESSFULLY_DELETED_USER;
import static com.nexos.inventory.constants.Constants.SUCCESSFULLY_UPDATED_USER;
import static com.nexos.inventory.constants.Constants.USER_NAME_EXISTS;
import static com.nexos.inventory.constants.Constants.USER_RELATION;
import static com.nexos.inventory.constants.Constants.YES_CONTENT;
import static com.nexos.inventory.utils.ResponseUtils.replyMessage;
import static com.nexos.inventory.utils.ResponseUtils.replyMessageList;

@Service
@Validated
public class UserService implements IBaseService<UserCreateDTO, UserUpdateDTO> {

	@Autowired
	private IUserRepository repository;

	@Autowired
	private IPositionRepository positionRepository;

	@Autowired
	private IAdvancedMapper<UserCreateDTO, UserUpdateDTO, User> mapper;

	@Override
	public ReplyMessageSimple<UserUpdateDTO> getCreate(UserCreateDTO entityDto) {
		List<String> messages = new ArrayList<>();
		UserUpdateDTO entityOutput = null;
		try {
			String name = repository.searchByName(0, entityDto.getName());
			Position idPosition = positionRepository.findById(entityDto.getIdPosition()).orElse(null);
			if (name != null) {
				throw new DataIntegrityViolationException(USER_NAME_EXISTS);
			}
			if (idPosition == null) {
				throw new EntityNotFoundException(ID_POSITION_USER_NOT_EXISTS);
			}
			User entity = new User();
			entity.setIdPosition(idPosition);
			entity = mapper.create(entityDto, entity);
			entityOutput = mapper.read(repository.save(entity));
			messages.add(SUCCESSFULLY_CREATED_USER);
		} catch (Exception e) {
			throw e;
		}
		return replyMessage(getUri(), HttpStatus.CREATED, messages, entityOutput);
	}

	@Override
	public ReplyMessageList<UserUpdateDTO> getFindAll() {
		List<String> messages = new ArrayList<>();
		List<UserUpdateDTO> entitiesDto = new ArrayList<>();
		try {
			List<User> entities = repository.searchAll();
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
	public ReplyMessageSimple<UserUpdateDTO> getFindById(Integer id) {
		List<String> messages = new ArrayList<>();
		UserUpdateDTO entityDto = null;
		try {
			User entity = repository.findById(id).orElse(null);
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
	public ReplyMessageSimple<UserUpdateDTO> getUpdate(UserUpdateDTO entityDto) {
		List<String> messages = new ArrayList<>();
		try {
			User entity = repository.findById(entityDto.getIdUser()).orElse(null);
			if (entity != null) {
				String name = repository.searchByName(entityDto.getIdUser(), entityDto.getName());
				Position idPosition = positionRepository.findById(entityDto.getIdPosition()).orElse(null);
				if (name != null) {
					throw new DataIntegrityViolationException(USER_NAME_EXISTS);
				}
				if (idPosition == null) {
					throw new EntityNotFoundException(ID_POSITION_USER_NOT_EXISTS);
				}
				entity.setIdPosition(idPosition);
				entity = mapper.update(entityDto, entity);
				entityDto = mapper.read(repository.save(entity));
				messages.add(SUCCESSFULLY_UPDATED_USER);
			} else {
				throw new EntityNotFoundException(NO_CONTENT_ID + entityDto.getIdPosition());
			}
		} catch (Exception e) {
			throw e;
		}
		return replyMessage(getUri(), HttpStatus.OK, messages, entityDto);
	}

	@Override
	public ReplyMessageSimple<UserUpdateDTO> getDelete(Integer id) {
		List<String> messages = new ArrayList<>();
		try {
			User entity = repository.findById(id).orElse(null);
			if (entity != null) {
				if ((entity.getProductCreate() == null || entity.getProductCreate().isEmpty()) && (entity.getProductUpdate() == null || entity.getProductUpdate().isEmpty())) {
					repository.delete(entity);
					messages.add(SUCCESSFULLY_DELETED_USER);
				} else {
					throw new DataIntegrityViolationException(USER_RELATION);
				}
			} else {
				throw new EntityNotFoundException(NO_CONTENT_ID + id);
			}
		} catch (Exception e) {
			throw e;
		}
		return replyMessage(getUri(), HttpStatus.OK, messages, null);
	}
}