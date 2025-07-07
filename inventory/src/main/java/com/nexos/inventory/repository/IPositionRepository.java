package com.nexos.inventory.repository;

import com.nexos.inventory.model.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.nexos.inventory.constants.SystemConstants.ID;
import static com.nexos.inventory.constants.SystemConstants.NAME;
import static com.nexos.inventory.constants.SystemConstants.POSITION_ALL_QUERY;
import static com.nexos.inventory.constants.SystemConstants.POSITION_NAME_QUERY;

@Repository
public interface IPositionRepository extends JpaRepository<Position, Integer> {
	@Query(value = POSITION_ALL_QUERY)
	List<Position> searchAll();

	@Query(value = POSITION_NAME_QUERY)
	String searchByName(@Param(ID) Integer id, @Param(NAME) String name);
}