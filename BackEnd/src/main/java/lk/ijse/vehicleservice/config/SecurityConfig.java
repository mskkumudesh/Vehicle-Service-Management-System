package lk.ijse.vehicleservice.config;

import lk.ijse.vehicleservice.util.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final JwtFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http

                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();

                    config.setAllowCredentials(true);

                    config.setAllowedOrigins(List.of(
                            "http://localhost:63342",
                            "http://127.0.0.1:63342",
                            "http://127.0.0.1:8081",
                            "http://127.0.0.1:8080"

                    ));

                    config.setAllowedHeaders(List.of("*"));
                    config.setAllowedMethods(List.of(
                            "GET",
                            "POST",
                            "PUT",
                            "DELETE",
                            "PATCH",
                            "OPTIONS"
                    ));

                    return config;
                }))


                .csrf(AbstractHttpConfigurer::disable)


                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/api/v1/user/**").permitAll()
                        .requestMatchers("/api/v1/vehicle/**").permitAll()
                        .requestMatchers("/api/v1/booking/**").permitAll()
                        .requestMatchers("/api/v1/payment/**").permitAll()
                        .requestMatchers("/api/v1/service/**").permitAll()
                        .anyRequest().authenticated()
                )


                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )


                .authenticationProvider(authenticationProvider())


                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
}