package org.prvn.labs.student.manage.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserDefinedAuthProvider implements AuthenticationProvider {

  @Autowired
  UserDetailsService userDetailsService;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    String name = authentication.getName();
    UserDetails userDetails = userDetailsService.loadUserByUsername(name);
    if(authentication.getCredentials()!=null && passwordEncoder.matches(authentication.getCredentials().toString(), userDetails.getPassword())){
      return  new UsernamePasswordAuthentication(userDetails.getUsername(),userDetails.getPassword(),userDetails.getAuthorities());
    }
    throw new BadCredentialsException("not valid credentials..");
  }

  @Override
  public boolean supports(Class<?> authenticationType) {
    return UsernamePasswordAuthentication.class.equals(authenticationType);
  }
}
