package tn.esprit.spring.pidevgestionconge.config;


import lombok.RequiredArgsConstructor;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfiguration {        //THIS CLASS IS TO TELL SPRING WHAT CONFIG WE WILL USE

    private final JwtAuthentificationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    private static final String[] AUTH_WHITELIST = {
            "/api/user/**"
    };


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {        //T THE START SPRING SECURITY WILL LOOK FOR SPRINGSECURITY FILTER CHAIN RESPONSIBLE OF CONFIGURING ALL HTTP SECURITY

        http
                .csrf()                                 //DISABLE CSRF
                .disable()
                .authorizeHttpRequests()
                .antMatchers(AUTH_WHITELIST)
                .permitAll()
                .antMatchers("/api/v1/demo-controller")
                .hasAnyAuthority("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //  SESSION SHOULD BE STATELESS SO EACH REQUEST MUST BE AUTHENTICATED LIKE THIS SPRING WILL CREATE A NEW SESSION FOR EACH REQUES
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }


}
