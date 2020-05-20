package co.proarena.usik.config;

import co.proarena.usik.filter.JWTAuthenticationFilter;
import co.proarena.usik.filter.JWTLoginFilter;
import co.proarena.usik.handler.CustomLogoutSuccessHandler;
import co.proarena.usik.security.CustomUserDetailsService;
import co.proarena.usik.security.JwtAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;


    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean()
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Scope(value = "singleton")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)


                .and()
                .authorizeRequests()
                .antMatchers("/", "/home", "/search")
                .permitAll()


                .antMatchers("/login", "/registration")
                .anonymous()


                .antMatchers("/addToCart", "/singin", "/editOrder", "/saveOrder", "/sentReceipt")
                .access("hasRole('ROLE_USER')")


                .antMatchers("/user", "/setting", "/saveSetting")
                .access("hasRole('ROLE_USER')")


                .antMatchers("/newProduct", "/editProduct", "/saveProduct", "/delProduct")
                .access("hasRole('ROLE_ADMIN')")


                .and()
                .exceptionHandling()
                .accessDeniedPage("/accessDenied")

                .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler())


                .and()
                .addFilterBefore(new JWTLoginFilter("/singin", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }


    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }
}
