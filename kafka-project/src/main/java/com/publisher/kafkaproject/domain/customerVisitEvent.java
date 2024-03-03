package com.publisher.kafkaproject.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class customerVisitEvent {

    private String customerId;
    private LocalDateTime dateTime;

    
}
