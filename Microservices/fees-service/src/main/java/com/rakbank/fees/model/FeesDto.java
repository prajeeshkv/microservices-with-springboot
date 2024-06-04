package com.rakbank.fees.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class FeesDto {

    @Schema(hidden = true)
    private Long id;
    private Long studentId;
    private String grade;
    private BigDecimal amount;
    private String currency;

    @Schema(hidden = true)
    private String referenceId;
    private String cardNumber;
    private String cardType;

    @Schema(hidden = true)
    private LocalDateTime date;

    @Schema(hidden = true)
    private LocalDateTime createdOn;

    @Schema(hidden = true)
    private LocalDateTime updatedOn;

}
