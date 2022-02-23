package com.kashitkalaecom.brandmodelmgmt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.kashitkalaecom.brandmodelmgmt.service.UserServiceImpl;
import com.kashitkalaecom.brandmodelmgmt.utilities.PropertyConfig;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserServiceImpl userService;

	@Value("${jwt.header}")
	private String tokenHeader;

	@Value("${jwt.route.authentication.path}")
	private String authenticationPath;
	
	private static String enableJwt;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoderBean());
	}

	@Bean
	public PasswordEncoder passwordEncoderBean() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
    	
		
		enableJwt=PropertyConfig.loadProperties().getProperty("enableJwt");
    	
    	if (Boolean.valueOf(enableJwt)) {
    	
        httpSecurity
            // we don't need CSRF because our token is invulnerable
            .csrf().disable()

            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()

            // don't create session
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

            .authorizeRequests()


            //.antMatchers("/consumerLogin","/login","/addCardTokenDtls","/validateAccount").permitAll()
            .antMatchers("/api/v1/login/signIn").permitAll()
            .anyRequest().authenticated();

        // Custom JWT based security filter
        JwtAuthorizationTokenFilter authenticationTokenFilter = new JwtAuthorizationTokenFilter(userDetailsService(), jwtTokenUtil, tokenHeader);
        httpSecurity
            .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        httpSecurity
            .headers()
            .frameOptions().sameOrigin()  // required to set for H2 else H2 Console will be blank.
            .cacheControl();
    	}
        else {
        	httpSecurity.csrf().disable().authorizeRequests().anyRequest().permitAll();
        }
    }

	@Override
	public void configure(WebSecurity web) throws Exception {
		// AuthenticationTokenFilter will ignore the below paths
		web.ignoring().antMatchers(HttpMethod.POST, authenticationPath)

				// allow anonymous resource requests
				.and().ignoring().antMatchers(HttpMethod.GET,

						"/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js")

				// Un-secure H2 Database (for testing purposes, H2 console shouldn't be
				// unprotected in production)
				.and().ignoring();
	}
}