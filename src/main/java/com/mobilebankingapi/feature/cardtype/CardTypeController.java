package com.mobilebankingapi.feature.cardtype;


import com.mobilebankingapi.feature.cardtype.dto.CardTypeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/card-types")
@RequiredArgsConstructor
@Slf4j
public class CardTypeController {

    private final CardTypeService cardTypeService;

    @GetMapping

    public List<CardTypeResponse> findCardTypes(){

        List<CardTypeResponse> cardTypeResponses = cardTypeService.findAll();
        return cardTypeResponses;
    }

    @GetMapping("/{name}")
    public CardTypeResponse findCardTypeByName(@PathVariable String name){
        return cardTypeService.findByName(name);
    }
}
