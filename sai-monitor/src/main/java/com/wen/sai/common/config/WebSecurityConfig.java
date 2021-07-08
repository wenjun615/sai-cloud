package com.wen.sai.common.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * <p>
 * 安全配置
 * </p>
 *
 * @author wenjun
 * @since 2021/2/28
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final String adminContextPath;

    public WebSecurityConfig(AdminServerProperties adminServerProperties) {
        this.adminContextPath = adminServerProperties.getContextPath();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SavedRequestAwareAuthenticationSuccessHandler successHandler =
                new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(adminContextPath + "/");
        http.authorizeRequests()
                // 所有静态资源可以访问
                .antMatchers(adminContextPath + "/assets/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage(adminContextPath + "/login")
                .successHandler(successHandler)
                .permitAll()
                .and()
                .logout()
                .logoutUrl(adminContextPath + "/logout")
                .permitAll()
                .and()
                // 开启 Http Basic 支持，服务注册到监控中心需要使用
                .httpBasic()
                .and()
                .csrf()
                // 开启基于 Cookie 的 CSRF 保护
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                // 忽略 CSRF 保护以便服务注册进监控中心
                .ignoringAntMatchers(adminContextPath + "/instances", adminContextPath + "/actuator/**");
    }
}
