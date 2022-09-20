package org.prvn.labs.student.manage.config.security;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.prvn.labs.student.manage.domain.entity.jpa.Otp;
import org.prvn.labs.student.manage.repository.jpa.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class UserNamePasswordFilter  extends OncePerRequestFilter {

  final AuthenticationManager manager;

  @Autowired
  OtpRepository repository;

  public UserNamePasswordFilter(
      AuthenticationManager manager) {
    this.manager = manager;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    String username = request.getHeader("username");
    String password = request.getHeader("password");
    String otp = request.getHeader("otp");

    if(otp==null){
      UsernamePasswordAuthentication usernamePasswordAuthentication = new UsernamePasswordAuthentication(
          username, password);
      try {
        Authentication authenticate = manager.authenticate(usernamePasswordAuthentication);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        Otp saveOtp = new Otp();
        saveOtp.setId(1L);
        saveOtp.setUsername(username);
        saveOtp.setOtp(new Random().nextInt() +2000 + "");
        repository.save(saveOtp);
        filterChain.doFilter(request,response);
      }catch (AuthenticationException exception){
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
      }


    }else {
      OTPAuthentication otpAuthentication = new OTPAuthentication(username, otp);
      try{
        Authentication authenticate = manager.authenticate(otpAuthentication);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        response.setHeader("Authorization", UUID.randomUUID().toString());
        filterChain.doFilter(request,response);
      }catch (AuthenticationException exception){
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
      }

    }
  }
}
