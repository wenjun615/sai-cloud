package com.wen.sai.config;

import com.wen.sai.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <p>
 * Spring Security 安全配置
 * </p>
 *
 * @author wenjun
 * @since 2021/2/20
 */
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return userService::loadUserByUsername;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // EndpointRequest.toAnyEndpoint() 匹配任何端点：/oauth/token、/oauth/**、/login/**、/logout/** 等
                .requestMatchers(EndpointRequest.toAnyEndpoint())
                .permitAll()
                // 加载公钥接口允许访问
                .antMatchers("/keyPair/loadPublicKey")
                .permitAll()
                // Swagger 文档数据接口允许访问
                .antMatchers("/v3/api-docs")
                .permitAll()
                .anyRequest()
                .authenticated();
    }
}
