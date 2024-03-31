package com.mobilebankingapi.feature.user;

import com.mobilebankingapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long > {
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByNationalCardId(String nationalCardId);
    boolean existsByStudentIdCard(String studentIdCard);
}
