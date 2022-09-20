package org.prvn.labs.student.manage.config.security;

import java.util.List;
import java.util.Optional;
import org.prvn.labs.student.manage.domain.entity.jpa.Otp;
import org.prvn.labs.student.manage.repository.jpa.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class OtpProvider implements AuthenticationProvider {

  @Autowired
  OtpRepository repository;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    //pull the otp from  DB and match it with authentication
    Optional<Otp> byUsername = repository.findByUsername(authentication.getName());
    if(byUsername.isPresent()){
      if(byUsername.get().getOtp().equals((String) authentication.getCredentials())){
        return new OTPAuthentication(authentication.getName(),authentication.getCredentials(), List.of(()-> "read"));
      }
    }

    throw new BadCredentialsException("OTP not matched");
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return OTPAuthentication.class.equals(authentication);
  }
}
