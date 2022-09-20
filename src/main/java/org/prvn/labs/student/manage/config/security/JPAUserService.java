package org.prvn.labs.student.manage.config.security;

import java.util.Optional;
import org.prvn.labs.student.manage.domain.entity.jpa.User;
import org.prvn.labs.student.manage.repository.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class JPAUserService implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username)  {
    Optional<User> byUserName = userRepository.findByUserName(username);
    User user = byUserName.orElseThrow(
        () -> new UsernameNotFoundException("no user found"));
    return new JPAUserDetails(user);
  }

}
