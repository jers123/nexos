package com.nexos.inventory.model.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import static com.nexos.inventory.constants.Constants.PAST_PRESENT_DATE;

@Getter
@Setter
public abstract class BaseDTO {
	@PastOrPresent(message=PAST_PRESENT_DATE)
	private LocalDate entryDate;
}