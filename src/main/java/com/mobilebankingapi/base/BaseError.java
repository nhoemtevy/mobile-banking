package com.mobilebankingapi.base;

import lombok.*;

@Builder
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class BaseError {
    private String code;
    private String description;
}
