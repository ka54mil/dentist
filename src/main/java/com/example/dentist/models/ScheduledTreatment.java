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
@Table(name = "scheduled_treatments")
@Getter @Setter
@NoArgsConstructor
public class ScheduledTreatment { //Models - baza danych
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @ManyToOne(fetch = FetchType.LAZY)
    //@Column(nullable=false)
    @JoinColumn(name="price_id", nullable = false) //
    private Price price;

    @DecimalMin("0")
    @DecimalMax("1000000")
    private BigDecimal paid;

    @Valid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="visit_id", nullable = false)
    private Visit visit;

    @Valid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="status_id", nullable = false)
    private Status status;
}
