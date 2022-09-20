package org.prvn.labs.student.manage.repository.jpa;

import java.util.Optional;
import org.prvn.labs.student.manage.domain.entity.jpa.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long> {

  Optional<Otp> findByUsername(String username);
}
