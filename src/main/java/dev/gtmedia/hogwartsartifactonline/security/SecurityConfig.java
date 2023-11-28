package dev.gtmedia.hogwartsartifactonline.security;

import dev.gtmedia.hogwartsartifactonline.hogwartsuser.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth->auth
                        .requestMatchers(HttpMethod.GET,"/artifacts/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/users/**").hasAuthority("ROLE_admin")
                        .requestMatchers(HttpMethod.POST,"/users").hasAuthority("ROLE_admin")
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                        .requestMatchers(HttpMethod.GET,"/swagger-ui/**").permitAll()
                        .anyRequest().authenticated()
                )
                .headers(headers->headers.frameOptions().disable())
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .build();

    }
}
