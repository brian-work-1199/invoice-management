package io.guywithglasses.invoicemanagement.configuration;

import io.guywithglasses.invoicemanagement.handler.CustomAccessDeniedHandler;
import io.guywithglasses.invoicemanagement.handler.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.OPTIONS;
import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private static final String[] PUBLIC_URLS = {};
    private final BCryptPasswordEncoder encoder;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()).cors(withDefaults());
        http.sessionManagement(session -> session.sessionCreationPolicy(STATELESS));
        http.authorizeHttpRequests(request -> request.requestMatchers(PUBLIC_URLS).permitAll());
        http.authorizeHttpRequests(request -> request.requestMatchers(OPTIONS).permitAll()); // Not needed
        http.authorizeHttpRequests(request -> request.requestMatchers(DELETE, "/user/delete/**").hasAnyAuthority("DELETE:USER"));
        http.authorizeHttpRequests(request -> request.requestMatchers(DELETE, "/customer/delete/**").hasAnyAuthority("DELETE:CUSTOMER"));
        http.exceptionHandling(exception -> exception.accessDeniedHandler(customAccessDeniedHandler).authenticationEntryPoint(customAuthenticationEntryPoint));
        http.authorizeHttpRequests(request -> request.anyRequest().authenticated());
//        http.addFilterBefore(customAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(null);
        authProvider.setPasswordEncoder(encoder);
        return new ProviderManager(authProvider);
    }
}
