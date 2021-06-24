package com.ekros.libraryspring.security;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configurable
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final LibraryUserDetailsService userDetailsService;

    public WebSecurityConfig(LibraryUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
        .authorizeRequests()
            .antMatchers("/auth/signin").not().fullyAuthenticated()
            .antMatchers("/book/list").permitAll()
            .antMatchers("/book/add", "/book/update/**", "/book/delete/**").hasAuthority("ADMIN")
            .anyRequest().authenticated()
        .and()
                .formLogin()
                .loginPage("/")
                .loginProcessingUrl("/auth/login")
                .defaultSuccessUrl("/")
                .permitAll()
        .and()
                .logout()
                .logoutUrl("/auth/logout")
                .permitAll()
                .logoutSuccessUrl("/");
    }
}
