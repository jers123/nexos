package com.nexos.inventory.mappers;

import com.nexos.inventory.model.dto.position.PositionCreateDTO;
import com.nexos.inventory.model.dto.position.PositionUpdateDTO;
import com.nexos.inventory.model.entity.Position;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PositionMapper implements IMapper<PositionCreateDTO, PositionUpdateDTO, Position> {

	@Override
	public Position create(PositionCreateDTO entityDto) {
		Position entity = new Position();
		entity.setName(entityDto.getName());
		entity.setEntryDate(LocalDate.now());
		return entity;
	}

	@Override
	public PositionUpdateDTO read(Position entity) {
		PositionUpdateDTO entityDto = new PositionUpdateDTO();
		entityDto.setIdPosition(entity.getIdPosition());
		entityDto.setName(entity.getName());
		entityDto.setEntryDate(entity.getEntryDate());
		return entityDto;
	}

	@Override
	public Position update(PositionUpdateDTO entityDto, Position entity) {
		entity.setName(entityDto.getName());
		return entity;
	}
}