package com.mobilebankingapi.feature.cardtype;

import com.mobilebankingapi.feature.cardtype.dto.CardTypeResponse;

import java.util.List;

public interface CardTypeService {
    List<CardTypeResponse> findAll();
    CardTypeResponse findByName(String name);
}
