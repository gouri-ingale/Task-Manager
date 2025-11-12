package com.project.taskManager.config;

import com.project.taskManager.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@Profile("prod")
public class SpringSecurityP {

    // ❌ REMOVED: @Autowired private UserDetailsServiceImpl userDetailsService;
    // ❌ REMOVED: configureGlobal(AuthenticationManagerBuilder auth) method

    // The userDetailsService is now injected directly into the DaoAuthenticationProvider bean method.

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                // Assuming you need both HTTP Basic and Form Login
                .httpBasic(withDefaults())
                .formLogin(withDefaults())
                .logout(withDefaults())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // 2. Correct CSRF Disable Configuration (New Style)
                .csrf(csrf -> csrf.disable());
        return http.build();
    }

    /**
     * Define the PasswordEncoder bean.
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * Define the AuthenticationProvider bean.
     * This explicitly wires the custom UserDetailsService and PasswordEncoder
     * which Spring Security will use automatically.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(
            UserDetailsServiceImpl userDetailsService, // Injected by Spring automatically
            PasswordEncoder passwordEncoder) {           // Injected by Spring automatically

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }
}