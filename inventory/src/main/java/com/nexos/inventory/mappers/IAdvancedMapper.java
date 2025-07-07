package com.nexos.inventory.mappers;

import com.nexos.inventory.model.dto.BaseDTO;
import com.nexos.inventory.model.entity.BaseEntity;

public interface IAdvancedMapper<BEC extends BaseDTO, BEU extends BaseDTO, BE extends BaseEntity> extends IMapper<BEC, BEU, BE> {
	BE create(BEC entityDto, BE entity);
}
