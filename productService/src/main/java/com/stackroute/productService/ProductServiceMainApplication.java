package com.stackroute.productService;

import com.stackroute.productService.Filter.ProductFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.logging.Filter;

@SpringBootApplication

public  class ProductServiceMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceMainApplication.class, args);
		System.out.println("Product");
	}
//		@Bean
//		public FilterRegistrationBean getfilter()
//		{
//			FilterRegistrationBean fbean = new FilterRegistrationBean();
//			fbean.setFilter(new ProductFilter());
////			fbean.addUrlPatterns("/products/add","/products/delete/*","/products/update/*");
//			return fbean;
//		}


}
