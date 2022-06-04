package com.culturebreathexhibitionsback.dto;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Value
public class ExhibitionDto implements Serializable {

    @NotNull
    UUID id;

    @NotBlank(message = "Exhibition name may not be blank")
    String exhibitionTopic;

    @NotBlank(message = "Exhibition holder name may not be blank")
    String exhibitionHolder;

    @NotNull(message = "Ticket price may not be blank")
    BigDecimal ticketPrice;

    @NotBlank(message = "Start date may not be blank")
    LocalDateTime startDate;

    @NotBlank(message = "End date may not be blank")
    LocalDateTime endDate;

    @NotNull(message = "Hall numbers may not be blank")
    int hall;
}
