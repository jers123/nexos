package com.nexos.inventory.constants;

import static com.nexos.inventory.constants.SystemConstants.POSITION_NAME_LENGTH;
import static com.nexos.inventory.constants.SystemConstants.PRODUCT_NAME_LENGTH;
import static com.nexos.inventory.constants.SystemConstants.USER_NAME_LENGTH;

public class Constants {

	// ERRORS
	public static final String HTTP_MESSAGE1 = "Problema de método HTTP, se esperaba la petición por medio del método ";
	public static final String HTTP_MESSAGE2 = " pero se solicitó por medio del metodo ";
	public static final String INCORRECT_JSON = "Error a leer los datos de entrada, revise que el formato JSON este correcto";
	public static final String PAST_PRESENT_DATE = "La fecha de ingreso debe ser igual o anterior a la fecha actual";

	public static final String ID_NOT_NULL = "El ID ingresado no puede ser nulo";
	public static final String ID_VALUE_MINIMUM = "El ID ingresado debe ser mayor o igual a 1";
	public static final String NO_CONTENT = "No hay registros";
	public static final String NO_CONTENT_ID = NO_CONTENT + " con el id = ";
	public static final String YES_CONTENT = "Si hay registros";

	// POSITION
	public static final String POSITION_NAME_EXISTS = "El nombre del cargo ya existe.";
	public static final String POSITION_NAME_NOT_BLANK = "El nombre del cargo no puede ser vacío.";
	public static final String POSITION_NAME_NOT_NULL = "El nombre del cargo no puede ser nulo.";
	public static final String POSITION_NAME_SIZE = "El tamaño del nombre del cargo es mínimo de 1 y máximo de " + POSITION_NAME_LENGTH + ".";
	public static final String POSITION_RELATION = "No se puede eliminar el cargo porque tiene relación con otras tablas.";
	public static final String SUCCESSFULLY_CREATED_POSITION = "Cargo creado exitosamente.";
	public static final String SUCCESSFULLY_DELETED_POSITION = "Cargo eliminado exitosamente.";
	public static final String SUCCESSFULLY_UPDATED_POSITION = "Cargo actualizado exitosamente.";

	// USER
	public static final String USER_AGE_NOT_NULL = "La edad del usuario no puede ser nulo.";
	public static final String USER_ENTRY_DATE_NOT_NULL = "La fecha de ingreso del Usuario no puede ser nulo.";
	public static final String ID_POSITION_USER_NOT_EXISTS = "El id del cargo no existe en la tabla position.";
	public static final String USER_NAME_EXISTS = "El nombre del usuario ya existe.";
	public static final String USER_NAME_NOT_BLANK = "El nombre del usuario no puede ser vacío.";
	public static final String USER_NAME_NOT_NULL = "El nombre del usuario no puede ser nulo.";
	public static final String USER_NAME_SIZE = "El tamaño del name del User es mínimo de 1 y máximo de " + USER_NAME_LENGTH + ".";
	public static final String SUCCESSFULLY_CREATED_USER = "Usuario creado exitosamente.";
	public static final String SUCCESSFULLY_DELETED_USER = "Usuario eliminado exitosamente.";
	public static final String SUCCESSFULLY_UPDATED_USER = "Usuario actualizado exitosamente.";
	public static final String USER_RELATION = "No se puede eliminar el usuario porque tiene relación con otras tablas.";

	// PRODUCT
	public static final String PRODUCT_ENTRY_DATE_NOT_NULL = "La fecha de ingreso del producto no puede ser nulo.";
	public static final String ID_USER_CREATE_PRODUCT_NOT_EXISTS = "El ID del usuario que crea el producto no existe en la tabla User.";
	public static final String ID_USER_UPDATE_PRODUCT_NOT_EXISTS = "El ID del usuario que actualiza el producto no existe en la tabla User.";
	public static final String PRODUCT_NAME_EXISTS = "El nombre del producto ya existe.";
	public static final String PRODUCT_NAME_NOT_BLANK = "El nombre del producto no puede ser vacío.";
	public static final String PRODUCT_NAME_NOT_NULL = "El nombre del producto no puede ser nulo.";
	public static final String PRODUCT_NAME_SIZE = "El tamaño del nombre del producto es mínimo de 1 y máximo de " + PRODUCT_NAME_LENGTH + ".";
	public static final String PRODUCT_QUANTITY_NOT_NULL = "La cantidad del producto no puede ser nulo.";
	public static final String SUCCESSFULLY_CREATED_PRODUCT = "Producto creado exitosamente.";
	public static final String SUCCESSFULLY_DELETED_PRODUCT = "Producto eliminado exitosamente.";
	public static final String SUCCESSFULLY_UPDATED_PRODUCT = "Producto actualizado exitosamente.";
}