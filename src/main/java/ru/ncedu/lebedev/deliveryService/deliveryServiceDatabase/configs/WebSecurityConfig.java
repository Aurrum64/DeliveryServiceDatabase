package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.UsersRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.service.UserService;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.RolesEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.UsersEntity;

import java.time.LocalDateTime;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;

    @Autowired
    public WebSecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/registration", "/activate/*").permitAll()
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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
    @Bean
    public PrincipalExtractor principalExtractor(UsersRepository usersRepository) {
        return map -> {
            String id=(String) map.get("sub");
            UsersEntity user=usersRepository.findByGoogleId(id);
            if (user == null){
                UsersEntity newUser= new UsersEntity();
                newUser.setGoogleId(id);
                newUser.setUsername((String)map.get("name"));
                newUser.setEmail((String)map.get("email"));
                newUser.setGender((String)map.get("gender"));
                newUser.setActive(true);
                Iterable<UsersEntity> usersList = usersRepository.findAll();
                if (usersList.iterator().hasNext()) {
                    newUser.setRoles(Collections.singleton(RolesEntity.USER));
                } else {
                    newUser.setRoles(Collections.singleton(RolesEntity.ADMIN));
                }
                user=newUser;
            }
            user.setLastVisit(LocalDateTime.now());
            return usersRepository.save(user);
            };
    }
}
