package com.nexos.inventory.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

import static com.nexos.inventory.constants.SystemConstants.ENTRY_DATE;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity implements Serializable {
	@Column(name=ENTRY_DATE, nullable=false)
	private LocalDate entryDate;
}