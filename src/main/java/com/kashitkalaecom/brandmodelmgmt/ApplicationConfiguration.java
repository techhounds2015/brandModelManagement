package com.kashitkalaecom.brandmodelmgmt;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kashitkalaecom.brandmodelmgmt.security.PermissionsFilter;

@Configuration
public class ApplicationConfiguration {
	
	
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
