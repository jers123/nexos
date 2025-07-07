package com.nexos.inventory.annotation;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.nexos.inventory.constants.SystemConstants.LOCAL_ORIGIN_PATH;
import static com.nexos.inventory.constants.SystemConstants.LOCAL_ORIGIN_PATH2;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@RestController
@CrossOrigin(origins = {LOCAL_ORIGIN_PATH,
		LOCAL_ORIGIN_PATH2,
		"*"	}
		,methods = {RequestMethod.GET,
		RequestMethod.POST,
		RequestMethod.PUT,
		RequestMethod.DELETE
}
)
public @interface RestApi {
}