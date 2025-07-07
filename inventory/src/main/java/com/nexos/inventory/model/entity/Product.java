package com.nexos.inventory.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import static com.nexos.inventory.constants.SystemConstants.DATABASE;
import static com.nexos.inventory.constants.SystemConstants.NAME;
import static com.nexos.inventory.constants.SystemConstants.ID_PRODUCT;
import static com.nexos.inventory.constants.SystemConstants.ID_USER;
import static com.nexos.inventory.constants.SystemConstants.ID_USER_CREATE;
import static com.nexos.inventory.constants.SystemConstants.ID_USER_UPDATE;
import static com.nexos.inventory.constants.SystemConstants.SCHEMA;
import static com.nexos.inventory.constants.SystemConstants.QUANTITY;
import static com.nexos.inventory.constants.SystemConstants.PRODUCT_NAME_LENGTH;
import static com.nexos.inventory.constants.SystemConstants.PRODUCT_NAME_UK;
import static com.nexos.inventory.constants.SystemConstants.PRODUCT_TABLE;
import static com.nexos.inventory.constants.SystemConstants.PRODUCT_USER_CREATE_FK;
import static com.nexos.inventory.constants.SystemConstants.PRODUCT_USER_UPDATE_FK;
import static com.nexos.inventory.constants.SystemConstants.UPDATE_DATE;

@Entity
@Table(name = PRODUCT_TABLE, catalog = DATABASE, schema = SCHEMA,
		uniqueConstraints = {
				@UniqueConstraint(name = PRODUCT_NAME_UK, columnNames = {NAME}),
		}
)
@Getter
@Setter
public class Product extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = ID_PRODUCT, nullable = false, updatable = false)
	private Integer idProduct;

	@Column(name=NAME, nullable=false, length=PRODUCT_NAME_LENGTH, unique=true)
	private String name;

	@Column(name=QUANTITY, nullable=false)
	private Integer quantity;

	@JoinColumn(name = ID_USER_CREATE, referencedColumnName = ID_USER, nullable = false,
			foreignKey = @ForeignKey(name = PRODUCT_USER_CREATE_FK))
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private User idUserCreate;

	@JoinColumn(name = ID_USER_UPDATE, referencedColumnName = ID_USER, nullable = false,
			foreignKey = @ForeignKey(name = PRODUCT_USER_UPDATE_FK))
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private User idUserUpdate;

	@Column(name=UPDATE_DATE, nullable=false)
	private LocalDate updateDate;
}