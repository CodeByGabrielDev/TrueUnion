package br.com.TrueUnion.TrueUnion.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class HashPassword {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // ← Essa é a implementação mais comum
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll() // Libera todas as rotas
		).csrf(csrf -> csrf.disable()) // Desativa proteção contra CSRF (pra não dar erro em POSTs)
				.formLogin(form -> form.disable()) // Desativa tela de login
				.httpBasic(basic -> basic.disable()); // Desativa login via pop-up

		return http.build();
	}
}
