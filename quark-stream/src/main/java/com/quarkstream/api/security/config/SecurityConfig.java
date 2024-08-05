package com.quarkstream.api.security.config;

import com.quarkstream.api.security.jwt.JwtGeneratorFilter;
import com.quarkstream.api.security.jwt.JwtValidationFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain mySecurityConfig(HttpSecurity http) throws Exception
	{
		
	// CORS configuration
		http.sessionManagement( sessionManagement ->  sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.cors(cors -> {
			cors.configurationSource(new CorsConfigurationSource() {
				
				@Override
				public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
					
					CorsConfiguration cfg = new CorsConfiguration();
					cfg.setAllowedOriginPatterns(Collections.singletonList("*"));
					cfg.setAllowedMethods(Collections.singletonList("*"));
					cfg.setAllowCredentials(true);
					cfg.setAllowedHeaders(Collections.singletonList("*"));
					cfg.setExposedHeaders(Arrays.asList("Authorization"));
					
				   return cfg;
					
				}
			});
		})
			
		.authorizeHttpRequests(
				(auth)-> auth
				.requestMatchers(HttpMethod.POST,"/rest/user/sign-up").permitAll()
				.requestMatchers("/v3/api-docs/**", "/swagger-ui*/**").permitAll()
				.requestMatchers("/**").hasAnyRole("USER","ADMIN")
				.anyRequest().authenticated()
				)
		.csrf(csrf -> csrf.ignoringRequestMatchers("/**")
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				)
		.addFilterAfter(new JwtGeneratorFilter(), BasicAuthenticationFilter.class)
		.addFilterBefore(new JwtValidationFilter(), BasicAuthenticationFilter.class)
		.httpBasic(Customizer.withDefaults())
		.formLogin(Customizer.withDefaults());
		
		 return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

}
