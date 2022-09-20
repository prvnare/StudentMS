package org.prvn.labs.student.manage.config.security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;


public class CustomFilter implements Filter {


  AuthenticationManager manager;

  public CustomFilter(AuthenticationManager manager){
    this.manager = manager;
  }


  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    HttpServletResponse httpServletResponse = (HttpServletResponse) response;

    String authorization = httpServletRequest.getHeader("Authorization");

    CustomAuthentication customAuthentication = new CustomAuthentication(authorization, null);
  try {
    Authentication authenticate = manager.authenticate(customAuthentication);

    if (authenticate.isAuthenticated()) {
      chain.doFilter(request, response);
    } else {
      httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
    }
  }catch (AuthenticationException exception){
    httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
  }

  }

}
