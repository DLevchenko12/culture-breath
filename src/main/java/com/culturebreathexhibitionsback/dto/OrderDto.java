package com.culturebreathexhibitionsback.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Value
public class OrderDto implements Serializable {

    @NotNull
    UUID id;

    @NotNull(message = "Order price may not be equal to null")
    BigDecimal price;
}
