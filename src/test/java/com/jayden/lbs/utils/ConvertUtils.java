package com.jayden.lbs.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * User : jianjun.xu
 * Date : 2016/11/16
 * Time : 11:05
 * Desc :
 */
public class ConvertUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConvertUtils.class);

	public static Map<String, String> convertBean2Map(Object bean) {
		Class type = bean.getClass();
		Map returnMap = new HashMap();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(type);
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			for (int i = 0; i < pds.length; i++) {
				PropertyDescriptor descriptor = pds[i];
				String propertyName = descriptor.getName();
				if (!propertyName.equals("class")) {
					Method readMethod = descriptor.getReadMethod();
					Object result = readMethod.invoke(bean, new Object[0]);
					if (result != null) {
						returnMap.put(propertyName, String.valueOf(result));
					} else {
						returnMap.put(propertyName, "");
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("convertBean2Map error.", e);
		}
		return returnMap;
	}
}
