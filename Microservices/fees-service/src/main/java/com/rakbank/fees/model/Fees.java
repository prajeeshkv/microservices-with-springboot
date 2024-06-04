package com.rakbank.fees.model;


import com.rakbank.fees.audit.AuditorEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Data
public class Fees extends AuditorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @NotNull(message = "Student Id cannot be null")
    @Column(name="fki_student_id")
    private Long studentId;

    @Column(name="grade")
    @Pattern(regexp = "^(1[0-2]?|[1-9])$", message = "Grade allows only from 1 to 12 only")
    private String grade;

    @Column(name="amount")
    private BigDecimal amount;

    @Column(name="currency")
    private String currency;

    @Column(name="reference_id")
    private String referenceId;

    @Pattern(regexp = "^((?:(?:\\d{4}[- ]){3}\\d{4}|\\d{16}))(?![\\d])$", message = "card number should be xxxx-xxxx-xxxx-xxxx")
    @Column(name="card_number")
    private String cardNumber;

    @Column(name="card_type")
    private String cardType;

    @Column(name="date")
    private LocalDateTime date;

}
