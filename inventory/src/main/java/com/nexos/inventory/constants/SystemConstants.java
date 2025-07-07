package com.nexos.inventory.constants;

public class SystemConstants {

	// PATHS
	public static final String LOCAL_ORIGIN_PATH = "http://localhost:8080";
	public static final String LOCAL_ORIGIN_PATH2 = "http://localhost:4200";
	public static final String INVENTORY_PATH = "/inventory";
	public static final String CREATE_PATH = "/create";
	public static final String DELETE_PATH = "/delete/";
	public static final String GET_ALL_PATH = "/get-all";
	public static final String GET_ID_PATH = "/get-id/";
	public static final String UPDATE_PATH = "/update";

	// SUBPATHS
	public static final String POSITION_PATH = "/position";
	public static final String PRODUCT_PATH = "/product";
	public static final String USER_PATH = "/user";

	// HEADERS
	public static final String ACCEPT = "Accept";
	public static final String AUTHORIZATION = "Authorization";
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String LOCATION = "Location";

	// DATABASE
	public static final String DATABASE = "inventoryDB";
	public static final String SCHEMA = "public";

	//COMMON DATA
	public static final String ENTRY_DATE = "entry_date";
	public static final String ID = "id";
	public static final String NAME = "name";

	// POSITION
	public static final String ID_POSITION = "id_position";
	public static final int POSITION_NAME_LENGTH = 100;
	public static final String POSITION_TABLE = "position";
	public static final String POSITION_NAME_UK = POSITION_TABLE + "_" + NAME + "_uk";
	public static final String POSITION_NAME_QUERY = "SELECT p.name FROM Position p WHERE LOWER(p.name) = LOWER(:" + NAME + ") AND p.idPosition != :" + ID;
	public static final String POSITION_ALL_QUERY = "SELECT p FROM Position p ORDER BY p.name ASC";

	// USER
	public static final String ID_USER = "id_user";
	public static final int USER_NAME_LENGTH = 100;
	public static final String AGE = "age";
	public static final String USER_TABLE = "user";
	public static final String USER_NAME_UK = USER_TABLE + "_" + NAME + "_uk";
	public static final String USER_ID_POSITION_FK = USER_TABLE + "_" + POSITION_TABLE + "_" + ID_POSITION + "_fk";
	public static final String USER_NAME_QUERY = "SELECT u.name FROM User u WHERE LOWER(u.name) = LOWER(:" + NAME + ") AND u.idUser != :" + ID;
	public static final String USER_ID_POSITION_QUERY = "SELECT u FROM User u WHERE u.idPosition = :" + ID + " ORDER BY u.name ASC";
	public static final String USER_ALL_QUERY = "SELECT u FROM User u ORDER BY u.name ASC";

	// PRODUCT
	public static final String ID_PRODUCT = "id_product";
	public static final int PRODUCT_NAME_LENGTH = 200;
	public static final String QUANTITY = "quantity";
	public static final String ID_USER_CREATE = "id_user_create";
	public static final String ID_USER_UPDATE = "id_user_update";
	public static final String UPDATE_DATE = "update_date";
	public static final String PRODUCT_TABLE = "product";
	public static final String PRODUCT_NAME_UK = PRODUCT_TABLE + "_" + NAME + "_uk";
	public static final String PRODUCT_USER_CREATE_FK = PRODUCT_TABLE + "_" + USER_TABLE + "_" + ID_USER_CREATE + "_fk";
	public static final String PRODUCT_USER_UPDATE_FK = PRODUCT_TABLE + "_" + USER_TABLE + "_" + ID_USER_UPDATE + "_fk";
	public static final String PRODUCT_NAME_QUERY = "SELECT p.name FROM Product p WHERE LOWER(p.name) = LOWER(:" + NAME + ") AND p.idProduct != :" + ID;
	public static final String PRODUCT_ID_USER_CREATE_QUERY = "SELECT p FROM Product p WHERE p.idUserCreate = :" + ID + " ORDER BY p.name ASC";
	public static final String PRODUCT_ID_USER_UPDATE_QUERY = "SELECT p FROM Product p WHERE p.idUserUpdate = :" + ID + " ORDER BY p.name ASC";
	public static final String PRODUCT_ALL_QUERY = "SELECT p FROM Product p ORDER BY p.name ASC";

}