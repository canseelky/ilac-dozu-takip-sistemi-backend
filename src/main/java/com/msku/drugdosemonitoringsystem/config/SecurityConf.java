package com.msku.drugdosemonitoringsystem.config;

import com.msku.drugdosemonitoringsystem.auth.AuthEntryPoint;
import com.msku.drugdosemonitoringsystem.auth.JwtAuthFilter;
import com.msku.drugdosemonitoringsystem.auth.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static com.msku.drugdosemonitoringsystem.auth.PasswordEncoder.passwordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConf extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailService mUserDetailService;

    @Autowired
    private AuthEntryPoint mEntryPoint;

    @Bean
    public JwtAuthFilter createAuthFilter(){
        return new JwtAuthFilter();
    }
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(mUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        AuthenticationEntryPoint handler;
        httpSecurity
                .cors()
                .and()
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(mEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/api/auth/**")
                .permitAll()
                .antMatchers("/api/hospital/**")
                .permitAll()
                .antMatchers("/api/city/**")
                .permitAll()
                .antMatchers("/swagger-ui/**")
                .permitAll()
                .antMatchers("/api/patient/**").hasAnyAuthority("patient")
                .antMatchers("/api/doctor/**").hasAnyAuthority("doctor")
                .antMatchers("/api/password").hasAnyAuthority("patient","doctor")
                .anyRequest().authenticated();
       httpSecurity.addFilterBefore(createAuthFilter(), UsernamePasswordAuthenticationFilter.class);
    }


}
