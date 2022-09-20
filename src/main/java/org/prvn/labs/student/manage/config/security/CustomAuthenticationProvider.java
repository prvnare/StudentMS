package org.prvn.labs.student.manage.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

  @Value("${key}")
  String key;

  @Override
  public Authentication authenticate(Authentication authentication) {
    String name = authentication.getName();
    if(key.equals(name)){
      return new CustomAuthentication(name,null,null);
    }
    throw new BadCredentialsException("Not authenticated");
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return CustomAuthentication.class.equals(authentication);
  }
}
