package com.mobilebankingapi.mapper;

import com.mobilebankingapi.domain.CardType;
import com.mobilebankingapi.feature.cardtype.dto.CardTypeResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardTypeMapper {
    CardType fromCardTypeResponse(CardTypeResponse cardTypeResponse);

    CardTypeResponse toCardTypeResponse(CardType cardType);

    List<CardTypeResponse> toListCardTypeResponse(List<CardType> cardTypes);
}
