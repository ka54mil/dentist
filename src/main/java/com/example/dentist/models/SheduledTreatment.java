package com.example.dentist.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Entity
@Table(name = "sheduled_treatments")
@Getter @Setter
@NoArgsConstructor
public class SheduledTreatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id", nullable = false)
    private Price price;

    @DecimalMin("0")
    @DecimalMax("1000000")
    private BigDecimal paid;

    @Valid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id", nullable = false)
    private Visit visit;

    @Valid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id", nullable = false)
    private Status status;
}
