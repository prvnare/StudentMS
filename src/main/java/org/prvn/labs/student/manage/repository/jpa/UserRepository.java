package org.prvn.labs.student.manage.repository.jpa;

import java.util.Optional;
import org.prvn.labs.student.manage.domain.entity.jpa.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUserName(String userName);
}
