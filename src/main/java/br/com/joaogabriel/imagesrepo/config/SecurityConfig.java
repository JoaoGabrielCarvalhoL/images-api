package br.com.joaogabriel.imagesrepo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.joaogabriel.imagesrepo.filter.JwtFilter;
import br.com.joaogabriel.imagesrepo.jwt.JwtService;
import br.com.joaogabriel.imagesrepo.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	JwtFilter filter(JwtService jwtService, UserService userService) {
		return new JwtFilter(jwtService, userService);
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtFilter jwtFilter) throws Exception {
		return httpSecurity.cors(cors -> cors.configure(httpSecurity)).csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(authorize -> {
					authorize.requestMatchers(HttpMethod.POST, "/v1/users/**").permitAll();
					authorize.requestMatchers(HttpMethod.POST, "/v1/auth/**").permitAll();
					authorize.requestMatchers(HttpMethod.GET, "/v1/images/**").permitAll();
					authorize.anyRequest().authenticated();
				})
				//.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CorsConfigurationSource configurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
		corsConfigurationSource.registerCorsConfiguration("/**", configuration);
		return corsConfigurationSource;
	}
	

}
