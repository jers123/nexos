package com.nexos.inventory.mappers;

import com.nexos.inventory.model.dto.product.ProductCreateDTO;
import com.nexos.inventory.model.dto.product.ProductUpdateDTO;
import com.nexos.inventory.model.entity.Product;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ProductMapper implements IAdvancedMapper<ProductCreateDTO, ProductUpdateDTO, Product> {

	@Override
	public Product create(ProductCreateDTO entityDto) {
		return null;
	}

	@Override
	public ProductUpdateDTO read(Product entity) {
		ProductUpdateDTO entityDto = new ProductUpdateDTO();
		entityDto.setIdProduct(entity.getIdProduct());
		entityDto.setName(entity.getName());
		entityDto.setQuantity(entity.getQuantity());
		entityDto.setIdUserCreate(entity.getIdUserCreate().getIdUser());
		entityDto.setEntryDate(entity.getEntryDate());
		entityDto.setIdUserUpdate(entity.getIdUserUpdate().getIdUser());
		entityDto.setUpdateDate(entity.getUpdateDate());
		return entityDto;
	}

	@Override
	public Product update(ProductUpdateDTO entityDto, Product entity) {
		entity.setName(entityDto.getName());
		entity.setQuantity(entityDto.getQuantity());
		entity.setEntryDate(entityDto.getEntryDate());
		entity.setUpdateDate(LocalDate.now());
		return entity;
	}

	@Override
	public Product create(ProductCreateDTO entityDto, Product entity) {
		entity.setName(entityDto.getName());
		entity.setQuantity(entityDto.getQuantity());
		entity.setEntryDate(entityDto.getEntryDate());
		entity.setUpdateDate(LocalDate.now());
		return entity;
	}
}