package com.nexos.inventory.model.dto.product;

import com.nexos.inventory.model.dto.BaseDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import static com.nexos.inventory.constants.Constants.ID_VALUE_MINIMUM;
import static com.nexos.inventory.constants.Constants.PRODUCT_NAME_NOT_BLANK;
import static com.nexos.inventory.constants.Constants.PRODUCT_NAME_NOT_NULL;
import static com.nexos.inventory.constants.Constants.PRODUCT_NAME_SIZE;
import static com.nexos.inventory.constants.Constants.PRODUCT_QUANTITY_NOT_NULL;
import static com.nexos.inventory.constants.SystemConstants.PRODUCT_NAME_LENGTH;

@Getter
@Setter
public class ProductCreateDTO extends BaseDTO {
	@NotNull(message=PRODUCT_NAME_NOT_NULL)
	@NotBlank(message=PRODUCT_NAME_NOT_BLANK)
	@Size(min=1, max=PRODUCT_NAME_LENGTH, message=PRODUCT_NAME_SIZE)
	private String name;

	@NotNull(message=PRODUCT_QUANTITY_NOT_NULL)
	@Min(value=1, message=ID_VALUE_MINIMUM)
	private Integer quantity;

	@Min(value=1, message=ID_VALUE_MINIMUM)
	private Integer idUserCreate;

	@Min(value=1, message=ID_VALUE_MINIMUM)
	private Integer idUserUpdate;

	private LocalDate updateDate;
}