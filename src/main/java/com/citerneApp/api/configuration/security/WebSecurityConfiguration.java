package com.citerneApp.api.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private FailedAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    LogoutHandler logoutHandler;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public LoginFilter loginFilterBean() throws Exception {
        return new LoginFilter("/login");
    }

    @Bean
    public RequestAuthenticationFilter requestAuthenticationFilterBean() {
        return new RequestAuthenticationFilter();
    }

    @Bean
    public AllowCustomHeadersFilter allowCustomHeadersBean() {
        return new AllowCustomHeadersFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Disable CSRF protection since tokens are immune to it
                .csrf().disable()
                // If the user is not authenticated, returns 401
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .accessDeniedHandler(accessDeniedHandler)
                .and()
                .logout().addLogoutHandler(logoutHandler).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .and()
                // This is a stateless application, disable sessions
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // Security policy
                .authorizeRequests()
                .antMatchers("/dashboard/**").hasRole("OUR_SYSTEM_USER")
                .antMatchers("/reports").hasRole("OUR_SYSTEM_USER")
                .antMatchers("/reports/add").hasRole("OUR_SYSTEM_USER")
                .antMatchers("/reports/edit").hasRole("OUR_SYSTEM_USER")
                .antMatchers("/reports/delete/{id}").hasRole("OUR_SYSTEM_USER")
                .antMatchers("/Settings/getAllSettings").hasRole("OUR_SYSTEM_USER")
                .antMatchers("/Settings/getSettings/**").hasRole("OUR_SYSTEM_USER")
                .antMatchers("/SettingsMapping/**").hasRole("OUR_SYSTEM_USER")
                .antMatchers("/Settings/addSetting").hasRole("OUR_SYSTEM_USER")
                .antMatchers("/Settings/addSubSetting").hasRole("OUR_SYSTEM_USER")
                .antMatchers("/Settings/editSetting").hasRole("OUR_SYSTEM_USER")
                .antMatchers("/Settings/editSubSetting").hasRole("OUR_SYSTEM_USER")
                .antMatchers("/Settings/deleteSubSetting").hasRole("OUR_SYSTEM_USER")
                .antMatchers("/groups", "/groups/{id}", "/groups/view").hasRole("OUR_SYSTEM_USER")
                .antMatchers("/groups/add").hasRole("OUR_SYSTEM_USER")
                .antMatchers("/groups/update").hasRole("OUR_SYSTEM_USER")
                .antMatchers("/groups/delete", "/groups/deleteSelection").hasRole("OUR_SYSTEM_USER")
                .antMatchers("/roles/**").hasRole("OUR_SYSTEM_USER")
                .antMatchers("/blacklists").hasRole("OUR_SYSTEM_USER")
                .antMatchers("/blacklists/add").hasRole("OUR_SYSTEM_USER")
                .antMatchers("/blacklists/upload").hasRole("OUR_SYSTEM_USER")
                .antMatchers("/blacklists/delete/**").hasRole("OUR_SYSTEM_USER")
                // NEW ROLES BASED ON CiterneApp PASS APP
                .antMatchers("/users", "/users/{pageNumber}/{maxResult}", "/users/view").hasAnyRole("SYSTEM", "COMPANY", "OUR_SYSTEM_USER", "VIEW_USERS", "ADD_USERS", "EDIT_USERS", "DELETE_USERS", "VIEW_GROUPS")
                .antMatchers("/users/addCompanyUser/**").hasAnyRole("SYSTEM", "COMPANY", "OUR_SYSTEM_USER", "ADD_USERS")
                .antMatchers("/users/add").hasAnyRole("SYSTEM", "COMPANY", "OUR_SYSTEM_USER", "ADD_USERS")
                .antMatchers("/users/update").hasAnyRole("SYSTEM", "OUR_SYSTEM_USER", "EDIT_USERS")
                .antMatchers("/users/delete").hasAnyRole("SYSTEM", "OUR_SYSTEM_USER", "DELETE_USERS")
                .antMatchers("/eventClasses/add").hasAnyRole("SYSTEM", "OUR_SYSTEM_USER")
                .antMatchers("/eventClasses/edit").hasAnyRole("SYSTEM", "OUR_SYSTEM_USER")
                .antMatchers("/profiles/add").hasAnyRole("SYSTEM", "OUR_SYSTEM_USER")
                .antMatchers("/profiles/edit").hasAnyRole("SYSTEM", "OUR_SYSTEM_USER")
                // Allow anonymous access to "/" path
                .antMatchers("/**").permitAll()
                .and()
                .addFilterBefore(allowCustomHeadersBean(), UsernamePasswordAuthenticationFilter.class)
                // Custom filter for logging in users at "/login"
                .addFilterBefore(loginFilterBean(), UsernamePasswordAuthenticationFilter.class)
                // Custom filter for authenticating users using tokens
                .addFilterBefore(requestAuthenticationFilterBean(), UsernamePasswordAuthenticationFilter.class)
                // Disable resource caching
                .headers().cacheControl();
    }
}
