package com.nexos.inventory.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.nexos.inventory.constants.SystemConstants.DATABASE;
import static com.nexos.inventory.constants.SystemConstants.NAME;
import static com.nexos.inventory.constants.SystemConstants.ID_POSITION;
import static com.nexos.inventory.constants.SystemConstants.POSITION_NAME_LENGTH;
import static com.nexos.inventory.constants.SystemConstants.POSITION_NAME_UK;
import static com.nexos.inventory.constants.SystemConstants.POSITION_TABLE;
import static com.nexos.inventory.constants.SystemConstants.SCHEMA;

@Entity
@Table(name = POSITION_TABLE, catalog = DATABASE, schema = SCHEMA,
		uniqueConstraints = {
				@UniqueConstraint(name = POSITION_NAME_UK, columnNames = {NAME}),
		}
)
@Getter
@Setter
public class Position extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = ID_POSITION, nullable = false, updatable = false)
	private Integer idPosition;

	@Column(name=NAME, nullable=false, length=POSITION_NAME_LENGTH, unique=true)
	private String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idPosition", fetch = FetchType.LAZY)
	private List<User> users;
}