package com.mobilebankingapi.feature.user;

import com.mobilebankingapi.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}
