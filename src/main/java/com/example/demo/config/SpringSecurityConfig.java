package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.service.MyUserDetailService;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailService myUserDetailService;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SimpleAuthenticationSuccessHandler successHandler;

    //config cacs tai nguyen: css,js khong can xac thuc
    public void configure(WebSecurity web) {
        web.ignoring();
//        .antMatchers("assets1/*")
//        .antMatchers("assets/*")
//        .antMatchers("images/*");
    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService).passwordEncoder(bCryptPasswordEncoder);
    }

    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        		.antMatchers("/assets1/**").permitAll()
        		.antMatchers("/assets/**").permitAll()
        		.antMatchers("images/*").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/sign-up").permitAll()
                .antMatchers("/sign-up-process").permitAll()
                .antMatchers("/insert-staff").permitAll()
                .antMatchers("/insert-staff-process").permitAll()
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/login")
                .and().formLogin().successHandler(successHandler)
                .permitAll()
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied")
                .and()
                .csrf().disable()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll();

    }
}
