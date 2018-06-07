package com.example.wselsfk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WselsfkApplication {

	public static void main(String[] args) {
		SpringApplication.run(WselsfkApplication.class, args);
	}

//	@Bean
//	public FilterRegistrationBean registerFilter(){
//		FilterRegistrationBean bean = new FilterRegistrationBean();
//		bean.addUrlPatterns("/*");
//		bean.setFilter(new CrosFilter());
//		return bean;
//	}
}
