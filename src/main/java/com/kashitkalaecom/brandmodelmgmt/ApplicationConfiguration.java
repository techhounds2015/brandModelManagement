package com.kashitkalaecom.brandmodelmgmt;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kashitkalaecom.brandmodelmgmt.models.PermissionsFilter;

@Configuration
public class ApplicationConfiguration {
	
	   
	/*
	 * @Bean public FilterRegistrationBean<AuthenticationFilter>
	 * authenticationFilterRegistrationBean() {
	 * FilterRegistrationBean<AuthenticationFilter> authenticationFilterBean = new
	 * FilterRegistrationBean<AuthenticationFilter>(); AuthenticationFilter
	 * authFilter = new AuthenticationFilter();
	 * 
	 * authenticationFilterBean.setFilter(authFilter);
	 * authenticationFilterBean.addUrlPatterns("/*");
	 * authenticationFilterBean.setOrder(4); // set precedence return
	 * authenticationFilterBean; }
	 */
		@Bean
		public FilterRegistrationBean<PermissionsFilter> perFilterRegistrationBean() {
			FilterRegistrationBean<PermissionsFilter> permissionsFilterBean = new FilterRegistrationBean<PermissionsFilter>();
			PermissionsFilter permissionsFilter = new PermissionsFilter();
			permissionsFilterBean.setFilter(permissionsFilter);
			permissionsFilterBean.addUrlPatterns("/*");
			permissionsFilterBean.setOrder(1); // set precedence return
			return permissionsFilterBean;
		}

}
