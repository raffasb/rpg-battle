package com.avanade.rpgbattle.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
    private HttpStatus          statusCode;
    private LocalDateTime       timestamp;
    private String              message;
    private String              description;
    private Map<String, String> validators;
}

