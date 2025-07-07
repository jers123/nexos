package com.nexos.inventory.mappers;

import com.nexos.inventory.model.dto.user.UserCreateDTO;
import com.nexos.inventory.model.dto.user.UserUpdateDTO;
import com.nexos.inventory.model.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserMapper implements IAdvancedMapper<UserCreateDTO, UserUpdateDTO, User> {

	@Override
	public User create(UserCreateDTO entityDto) {
		return null;
	}

	@Override
	public UserUpdateDTO read(User entity) {
		UserUpdateDTO entityDto = new UserUpdateDTO();
		entityDto.setIdUser(entity.getIdUser());
		entityDto.setName(entity.getName());
		entityDto.setAge(entity.getAge());
		entityDto.setIdPosition(entity.getIdPosition().getIdPosition());
		entityDto.setEntryDate(entity.getEntryDate());
		return entityDto;
	}

	@Override
	public User update(UserUpdateDTO entityDto, User entity) {
		entity.setName(entityDto.getName());
		entity.setAge(entityDto.getAge());
		entity.setEntryDate(entityDto.getEntryDate());
		return entity;
	}

	@Override
	public User create(UserCreateDTO entityDto, User entity) {
		entity.setName(entityDto.getName());
		entity.setAge(entityDto.getAge());
		entity.setEntryDate(entityDto.getEntryDate());
		return entity;
	}
}