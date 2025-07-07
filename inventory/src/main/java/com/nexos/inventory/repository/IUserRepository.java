package com.nexos.inventory.repository;

import com.nexos.inventory.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.nexos.inventory.constants.SystemConstants.ID;
import static com.nexos.inventory.constants.SystemConstants.NAME;
import static com.nexos.inventory.constants.SystemConstants.USER_ALL_QUERY;
import static com.nexos.inventory.constants.SystemConstants.USER_ID_POSITION_QUERY;
import static com.nexos.inventory.constants.SystemConstants.USER_NAME_QUERY;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
	@Query(value = USER_ALL_QUERY)
	List<User> searchAll();

	@Query(value = USER_NAME_QUERY)
	String searchByName(@Param(ID) Integer id, @Param(NAME) String name);

	@Query(value = USER_ID_POSITION_QUERY)
	List<User> searchByIdPosition(@Param(ID) Integer id);
}