package com.mobilebankingapi.feature.media.dto;

import lombok.Builder;

@Builder
public record MediaResponse(
    String name,
    String contentType,
    String extension,
    String uri,
    Long size
) {
}
