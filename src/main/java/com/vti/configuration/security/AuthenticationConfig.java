package com.vti.configuration.security;

import com.vti.configuration.exception.ErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class AuthenticationConfig {

    // Sử dụng để mẫ hoá password sang dãy Hex
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Phương thức để xác minh mật khẩu tài khoản
    @Bean
    public AuthenticationManager authenticationManager(
            HttpSecurity http,
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder
    ) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and().build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            ErrorHandler errorHandle) throws Exception {
        http
                .csrf().disable()
                .cors(Customizer.withDefaults())
                // handle error 9,10
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling
                                .authenticationEntryPoint(errorHandle)
                                .accessDeniedHandler(errorHandle))
                .authorizeHttpRequests(authorizeHttpRequests ->
                        // dùng để phân quyền
                        authorizeHttpRequests
                                .antMatchers(HttpMethod.DELETE)
                                .hasAuthority("ADMIN")
                                .antMatchers(HttpMethod.POST, "/api/v1/departments/**", "/api/v1/accounts/**")
                                .hasAnyAuthority("ADMIN", "MANAGER")
                                .antMatchers(HttpMethod.PUT, "/api/v1/departments/**", "/api/v1/accounts/**")
                                .hasAnyAuthority("ADMIN", "MANAGER")
                                .antMatchers("/api/v1/auth/**")
                                .permitAll() // cho phep tat cả API "/api/v1/auth/**" truy cập
                                .anyRequest() // còn lại thì yêu cầu username, password
                                .authenticated()
                )
                // Spring security sẽ hỗ trợ phương thức formLogin
                .formLogin(formLogin ->
                        formLogin
                                // Đường dẫn của trang login
                                .loginPage("/page/auth/login/index.html")
                                // Đường dẫn đến người xử lý
                                .loginProcessingUrl("/api/v1/auth/login")
                                // thành công thì quay lại trang dưới
                                .defaultSuccessUrl("/index.html")
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/api/v1/auth/logout")
                                .deleteCookies("JSESSIONID")
                )
                .rememberMe(rememberMe -> rememberMe.key("uniqueAndSecret"))
                .httpBasic();
        return http.build();
    }

    // Bỏ qua kiểm tra bảo mật
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers(
                "/",
                "/index.html",
                "/common/**",
                "/page/**"
        );
    }
}
