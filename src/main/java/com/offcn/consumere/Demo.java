package com.offcn.consumere;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("classpath:applicationContext-mq2.xml");
	}

}
