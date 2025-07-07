package com.nexos.inventory.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.nexos.inventory.constants.SystemConstants.AGE;
import static com.nexos.inventory.constants.SystemConstants.DATABASE;
import static com.nexos.inventory.constants.SystemConstants.NAME;
import static com.nexos.inventory.constants.SystemConstants.ID_POSITION;
import static com.nexos.inventory.constants.SystemConstants.ID_USER;
import static com.nexos.inventory.constants.SystemConstants.SCHEMA;
import static com.nexos.inventory.constants.SystemConstants.USER_ID_POSITION_FK;
import static com.nexos.inventory.constants.SystemConstants.USER_NAME_LENGTH;
import static com.nexos.inventory.constants.SystemConstants.USER_NAME_UK;
import static com.nexos.inventory.constants.SystemConstants.USER_TABLE;

@Entity
@Table(name = USER_TABLE, catalog = DATABASE, schema = SCHEMA,
		uniqueConstraints = {
				@UniqueConstraint(name = USER_NAME_UK, columnNames = {NAME}),
		}
)
@Getter
@Setter
public class User extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = ID_USER, nullable = false, updatable = false)
	private Integer idUser;

	@Column(name=NAME, nullable=false, length=USER_NAME_LENGTH, unique=true)
	private String name;

	@Column(name=AGE, nullable=false)
	private Integer age;

	@JoinColumn(name = ID_POSITION, referencedColumnName = ID_POSITION, nullable = false,
			foreignKey = @ForeignKey(name = USER_ID_POSITION_FK))
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Position idPosition;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idUserCreate", fetch = FetchType.LAZY)
	private List<Product> productCreate;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idUserUpdate", fetch = FetchType.LAZY)
	private List<Product> productUpdate;
}