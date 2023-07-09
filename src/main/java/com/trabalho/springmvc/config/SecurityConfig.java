package com.trabalho.springmvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.trabalho.springmvc.filter.AcessLoginRegisterPage;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
     
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
            .authenticationProvider(getDaoAuthProvider())
            .build();
    }

    //define a criptografia usada e a classe que extende de UserDetailsService que vai gerenciar o login
    @Bean
    public DaoAuthenticationProvider getDaoAuthProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsServiceImpl);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
    }

    //define criptografia na hora de verificar a senha no login
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }
        
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf()
            .disable()
            .addFilterAfter(new AcessLoginRegisterPage(), UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests()
            .antMatchers("/login").permitAll()
            .antMatchers("/register").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .usernameParameter("email")
            .passwordParameter("senha")
            //.loginProcessingUrl("/perform_login") //caso queira uma lógica propria de login
            .defaultSuccessUrl("/user", true)
            .failureUrl("/login?error=true")
            .failureHandler(new CustomErroLogin())//lógica propria se o login falhar
            .successHandler(new CustomSuccessLogin())
            .and()
            .logout()
            .logoutSuccessHandler(new CustomLogoutHandler())
            .deleteCookies("JSESSIONID");
            //.logoutSuccessHandler(logoutSuccessHandler());

        return http.build();
    }
    
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/resources/static/**");
    }
}
