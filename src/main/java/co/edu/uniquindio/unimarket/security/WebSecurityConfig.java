package co.edu.uniquindio.unimarket.security;

import co.edu.uniquindio.unimarket.security.jwt.JwtAuthenticationFilter;
import co.edu.uniquindio.unimarket.security.jwt.JwtAuthorizationFilter;
import co.edu.uniquindio.unimarket.security.servicios.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public JwtAuthorizationFilter jwtTokenFilter() {
        return new JwtAuthorizationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
        AuthenticationManager authenticationManager = builder.build();
        http.authenticationManager(authenticationManager);
        http.csrf().disable();
        http.cors();
        http.authorizeHttpRequests().requestMatchers("/api/auth/**").permitAll();
        http.authorizeHttpRequests().requestMatchers("/api/moderador/**").hasAuthority("MODERADOR");
        http.authorizeHttpRequests().requestMatchers("/api/usuario/**", "/api/comentario/**", "/api/queja/**", "/api/compra/**", "/api/imagenes/**").hasAuthority("CLIENTE");
        http.authorizeHttpRequests().requestMatchers("/api/producto/crear_producto/**", "/api/producto/actualizar_producto/**", "/api/producto/eliminar_producto/**").hasAuthority("CLIENTE");
        http.authorizeHttpRequests().requestMatchers("/api/producto/listar_propios/**").hasAuthority("CLIENTE");
        http.authorizeHttpRequests().requestMatchers("/api/password/**").permitAll();
        http.authorizeHttpRequests().requestMatchers("/api/producto/listar_disponibles/**", "/api/producto/obtener/**", "/api/producto/listar_categoria/**", "/api/producto/listar_nombre/**", "/api/producto/listar_precio/**").permitAll().anyRequest().authenticated();
        http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationFilter);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
