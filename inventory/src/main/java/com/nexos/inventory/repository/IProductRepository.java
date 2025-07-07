package com.nexos.inventory.repository;

import com.nexos.inventory.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.nexos.inventory.constants.SystemConstants.ID;
import static com.nexos.inventory.constants.SystemConstants.NAME;
import static com.nexos.inventory.constants.SystemConstants.PRODUCT_ALL_QUERY;
import static com.nexos.inventory.constants.SystemConstants.PRODUCT_ID_USER_CREATE_QUERY;
import static com.nexos.inventory.constants.SystemConstants.PRODUCT_ID_USER_UPDATE_QUERY;
import static com.nexos.inventory.constants.SystemConstants.PRODUCT_NAME_QUERY;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {
	@Query(value = PRODUCT_ALL_QUERY)
	List<Product> searchAll();

	@Query(value = PRODUCT_NAME_QUERY)
	String searchByName(@Param(ID) Integer id, @Param(NAME) String name);

	@Query(value = PRODUCT_ID_USER_CREATE_QUERY)
	List<Product> searchByIdUserCreate(@Param(ID) Integer id);

	@Query(value = PRODUCT_ID_USER_UPDATE_QUERY)
	List<Product> searchByIdUserUpdate(@Param(ID) Integer id);
}