package com.ly.application;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.Method;

@Slf4j
class ApplicationTests {

	@Test
	public void contextLoads() throws ClassNotFoundException {
		Class clazz = Class.forName("RedisImpl");
		Method[] methods = clazz.getMethods();
	}

}
