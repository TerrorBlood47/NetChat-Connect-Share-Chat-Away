package com.example.NetChatBackend.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class AppConfig {

	@Bean
	public SecurityFilterChain securityFilterChain( HttpSecurity http ) throws Exception {
		
		
		http.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeRequests(authorize -> authorize
						.requestMatchers("/api/**").permitAll().requestMatchers("/v2/api-docs/**").permitAll()
						.requestMatchers("/v3/api-docs/**").permitAll()
						.requestMatchers("/swagger-ui/**").permitAll()
						.requestMatchers("/swagger-resources/**").permitAll()
						.requestMatchers("/swagger-ui.html").permitAll()
						
				)
				.addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
				.csrf(AbstractHttpConfigurer::disable)
				.cors(cors -> cors.configurationSource(request -> {
					CorsConfiguration cfg = new CorsConfiguration();
					cfg.setAllowedOrigins(Arrays.asList("*"));
					cfg.setAllowedMethods(Arrays.asList("*"));
					cfg.setAllowedHeaders(Arrays.asList("*"));
					cfg.setExposedHeaders(Arrays.asList("Authorization"));
					cfg.setAllowCredentials(true);
					cfg.setMaxAge(3600L);
					return cfg;
				}))
				.formLogin(withDefaults())
				.httpBasic(withDefaults());
		
		
		return http.build();
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer(){
		return web -> web.ignoring().requestMatchers("/**");
	}
	

}
