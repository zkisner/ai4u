package com.ai4u.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AnnotationUtils {

	public static List<Method> getAnnotatedMethods(Class<?> clazz,
			Class<? extends Annotation> annotationClass) {
		List<Method> methods = new ArrayList<Method>();
		for (Method method : clazz.getMethods()) {
			if (method.isAnnotationPresent(annotationClass)) {
				methods.add(method);
			}
		}
		return methods;
	}

	public static List<Field> getAnnotatedFields(Class<?> clazz,
			Class<? extends Annotation> annotationClass) {
		List<Field> fields = new ArrayList<Field>();
		for (Field field : clazz.getFields()) {
			if (field.isAnnotationPresent(annotationClass)) {
				fields.add(field);
			}
		}
		return fields;
	}

	public static <T> List<T> getAnnotatedFieldsValues(Class<?> clazz,
			Object target, Class<? extends Annotation> annotationClass,
			Class<T> valueClass) {
		List<T> results = new ArrayList<T>();
		for (Field each : getAnnotatedFields(clazz, annotationClass)) {
			try {
				Object fieldValue = each.get(target);
				if (valueClass.isInstance(fieldValue))
					results.add(valueClass.cast(fieldValue));
			} catch (IllegalAccessException e) {
				throw new RuntimeException(
						"How did getFields return a field we couldn't access?");
			}
		}
		return results;
	}

}
