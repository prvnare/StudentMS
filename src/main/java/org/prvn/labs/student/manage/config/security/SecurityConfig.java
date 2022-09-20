package org.prvn.labs.student.manage.config.security;


import javax.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  CustomAuthenticationProvider provider;

  @Autowired
  OtpProvider otpProvider;

  @Bean
  public UserDetailsService userDetailsService(){
    return  new JPAUserService();
  }

  @Bean
  public AuthenticationProvider authenticationProvider(){
    return new UserDefinedAuthProvider();
  }

  @Bean
  public PasswordEncoder encoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Bean
  public Filter customFilter() throws Exception {
    return new UserNamePasswordFilter(authenticationManagerBean());
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider())
        .authenticationProvider(provider)
        .authenticationProvider(otpProvider);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.addFilterAfter(customFilter(), BasicAuthenticationFilter.class);
    http.authorizeRequests().anyRequest().permitAll();
  }


}
