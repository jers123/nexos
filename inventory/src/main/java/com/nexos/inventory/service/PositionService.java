package com.nexos.inventory.service;

import com.nexos.inventory.mappers.IMapper;
import com.nexos.inventory.model.dto.position.PositionCreateDTO;
import com.nexos.inventory.model.dto.position.PositionUpdateDTO;
import com.nexos.inventory.model.dto.response.ReplyMessageList;
import com.nexos.inventory.model.dto.response.ReplyMessageSimple;
import com.nexos.inventory.model.entity.Position;
import com.nexos.inventory.repository.IPositionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

import static com.nexos.inventory.constants.Constants.NO_CONTENT;
import static com.nexos.inventory.constants.Constants.NO_CONTENT_ID;
import static com.nexos.inventory.constants.Constants.POSITION_NAME_EXISTS;
import static com.nexos.inventory.constants.Constants.POSITION_RELATION;
import static com.nexos.inventory.constants.Constants.SUCCESSFULLY_CREATED_POSITION;
import static com.nexos.inventory.constants.Constants.SUCCESSFULLY_DELETED_POSITION;
import static com.nexos.inventory.constants.Constants.SUCCESSFULLY_UPDATED_POSITION;
import static com.nexos.inventory.constants.Constants.YES_CONTENT;
import static com.nexos.inventory.utils.ResponseUtils.replyMessage;
import static com.nexos.inventory.utils.ResponseUtils.replyMessageList;

@Service
@Validated
public class PositionService implements IBaseService<PositionCreateDTO, PositionUpdateDTO> {

	@Autowired
	private IPositionRepository repository;

	@Autowired
	private IMapper<PositionCreateDTO, PositionUpdateDTO, Position> mapper;

	@Override
	public ReplyMessageSimple<PositionUpdateDTO> getCreate(PositionCreateDTO entityDto) {
		List<String> messages = new ArrayList<>();
		PositionUpdateDTO entityOutput = null;
		try {
			String name = repository.searchByName(0, entityDto.getName());
			if (name == null) {
				Position entity = mapper.create(entityDto);
				entityOutput = mapper.read(repository.save(entity));
				messages.add(SUCCESSFULLY_CREATED_POSITION);
			} else {
				throw new DataIntegrityViolationException(POSITION_NAME_EXISTS);
			}
		} catch (Exception e) {
			throw e;
		}
		return replyMessage(getUri(), HttpStatus.CREATED, messages, entityOutput);
	}

	@Override
	public ReplyMessageList<PositionUpdateDTO> getFindAll() {
		List<String> messages = new ArrayList<>();
		List<PositionUpdateDTO> entitiesDto = new ArrayList<>();
		try {
			List<Position> entities = repository.searchAll();
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
	public ReplyMessageSimple<PositionUpdateDTO> getFindById(Integer id) {
		List<String> messages = new ArrayList<>();
		PositionUpdateDTO entityDto = null;
		try {
			Position entity = repository.findById(id).orElse(null);
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
	public ReplyMessageSimple<PositionUpdateDTO> getUpdate(PositionUpdateDTO entityDto) {
		List<String> messages = new ArrayList<>();
		try {
			Position entity = repository.findById(entityDto.getIdPosition()).orElse(null);
			if (entity != null) {
				String name = repository.searchByName(entityDto.getIdPosition(), entityDto.getName());
				if (name == null) {
					entity = mapper.update(entityDto, entity);
					entityDto = mapper.read(repository.save(entity));
					messages.add(SUCCESSFULLY_UPDATED_POSITION);
				} else {
					throw new DataIntegrityViolationException(POSITION_NAME_EXISTS);
				}
			} else {
				throw new EntityNotFoundException(NO_CONTENT_ID + entityDto.getIdPosition());
			}
		} catch (Exception e) {
			throw e;
		}
		return replyMessage(getUri(), HttpStatus.OK, messages, entityDto);
	}

	@Override
	public ReplyMessageSimple<PositionUpdateDTO> getDelete(Integer id) {
		List<String> messages = new ArrayList<>();
		try {
			Position entity = repository.findById(id).orElse(null);
			if (entity != null) {
				if ((entity.getUsers() == null || entity.getUsers().isEmpty())) {
					repository.delete(entity);
					messages.add(SUCCESSFULLY_DELETED_POSITION);
				} else {
					throw new DataIntegrityViolationException(POSITION_RELATION);
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