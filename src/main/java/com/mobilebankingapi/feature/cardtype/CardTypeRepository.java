package com.mobilebankingapi.feature.cardtype;

import com.mobilebankingapi.domain.CardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardTypeRepository extends JpaRepository<CardType,Integer> {
    Optional<CardType> findByNameIgnoreCase(String name);
}
