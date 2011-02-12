/**
 * Created on 11/02/11
 */
package com.ai4u.util.junit;

import java.lang.annotation.*;

/**
 * @author kreich
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})

public @interface Repeat {

	int value();
	
}
