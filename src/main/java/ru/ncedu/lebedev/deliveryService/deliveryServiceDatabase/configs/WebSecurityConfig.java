/*package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails artem =
                User.withDefaultPasswordEncoder()
                        .username("Artem")
                        .password("Artem")
                        .roles("USER")
                        .build();
        UserDetails polina =
                User.withDefaultPasswordEncoder()
                        .username("Polina")
                        .password("Polina")
                        .roles("USER")
                        .build();
        UserDetails misha =
                User.withDefaultPasswordEncoder()
                        .username("Misha")
                        .password("Misha")
                        .roles("USER")
                        .build();
        return new InMemoryUserDetailsManager(artem, polina, misha);
    }
}*/
