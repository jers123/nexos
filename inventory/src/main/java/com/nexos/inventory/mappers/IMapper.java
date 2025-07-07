package com.nexos.inventory.mappers;

import com.nexos.inventory.model.dto.BaseDTO;
import com.nexos.inventory.model.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

public interface IMapper <BEC extends BaseDTO, BEU extends BaseDTO, BE extends BaseEntity> {
	BE create(BEC entityDto);
	BEU read(BE entity);
	default List<BEU> readList(List<BE> entities) {
		List<BEU> entitiesDto = new ArrayList<>();
		for (BE entity : entities) {
			entitiesDto.add(read(entity));
		}
		return entitiesDto;
	}
	BE update(BEU entityDto, BE entity);
}