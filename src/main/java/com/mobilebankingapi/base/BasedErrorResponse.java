package com.mobilebankingapi.base;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BasedErrorResponse{
    private Error error;
}
