package com.in28minutes.springboot.myfirstwebapp.security;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SpringSecurityConfiguration {

	@Bean
	public InMemoryUserDetailsManager createUserDetailsManager() {

		Function<String, String> passwordEncoder = input -> pwEncoder().encode(input);

		UserDetails userDetails = User.builder()
				.passwordEncoder(passwordEncoder)
				.username("yuan")
				.password("640720")
				.roles("USER", "ADMIN").build();

		return new InMemoryUserDetailsManager(userDetails);
	}

	@Bean
	PasswordEncoder pwEncoder() {
		return new BCryptPasswordEncoder();
	}
}
