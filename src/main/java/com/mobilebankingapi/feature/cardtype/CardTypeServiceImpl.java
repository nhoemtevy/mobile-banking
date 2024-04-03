package com.mobilebankingapi.feature.cardtype;

import com.mobilebankingapi.domain.CardType;
import com.mobilebankingapi.feature.cardtype.dto.CardTypeResponse;
import com.mobilebankingapi.mapper.CardTypeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardTypeServiceImpl implements CardTypeService{

    private final CardTypeRepository cardTypeRepository;
    private final CardTypeMapper cardTypeMapper;


    @Override
    public List<CardTypeResponse> findAll() {
        List<CardType> cardTypes = cardTypeRepository.findAll();
        return cardTypeMapper.toListCardTypeResponse(cardTypes);
    }

    @Override
    public CardTypeResponse findByName(String name) {
        CardType cardType = cardTypeRepository.findByNameIgnoreCase(name)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "CardType does not exist!"
                ));

        return cardTypeMapper.toCardTypeResponse(cardType);
    }
}
