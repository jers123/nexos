package com.nexos.inventory.model.dto.user;

import com.nexos.inventory.model.dto.BaseDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import static com.nexos.inventory.constants.Constants.ID_VALUE_MINIMUM;
import static com.nexos.inventory.constants.Constants.USER_AGE_NOT_NULL;
import static com.nexos.inventory.constants.Constants.USER_NAME_NOT_BLANK;
import static com.nexos.inventory.constants.Constants.USER_NAME_NOT_NULL;
import static com.nexos.inventory.constants.Constants.USER_NAME_SIZE;
import static com.nexos.inventory.constants.SystemConstants.USER_NAME_LENGTH;

@Getter
@Setter
public class UserCreateDTO extends BaseDTO {
	@NotNull(message=USER_NAME_NOT_NULL)
	@NotBlank(message=USER_NAME_NOT_BLANK)
	@Size(min=1, max=USER_NAME_LENGTH, message=USER_NAME_SIZE)
	private String name;

	@NotNull(message=USER_AGE_NOT_NULL)
	@Min(value=1, message=ID_VALUE_MINIMUM)
	private Integer age;

	@Min(value=1, message=ID_VALUE_MINIMUM)
	private Integer idPosition;
}