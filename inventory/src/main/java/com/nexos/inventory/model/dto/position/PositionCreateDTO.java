package com.nexos.inventory.model.dto.position;

import com.nexos.inventory.model.dto.BaseDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import static com.nexos.inventory.constants.Constants.POSITION_NAME_NOT_BLANK;
import static com.nexos.inventory.constants.Constants.POSITION_NAME_NOT_NULL;
import static com.nexos.inventory.constants.Constants.POSITION_NAME_SIZE;
import static com.nexos.inventory.constants.SystemConstants.POSITION_NAME_LENGTH;

@Getter
@Setter
public class PositionCreateDTO extends BaseDTO {
	@NotNull(message=POSITION_NAME_NOT_NULL)
	@NotBlank(message=POSITION_NAME_NOT_BLANK)
	@Size(min=1, max=POSITION_NAME_LENGTH, message=POSITION_NAME_SIZE)
	private String name;
}