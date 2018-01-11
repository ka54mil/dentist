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
@Table(name = "prices")
@Getter @Setter
@NoArgsConstructor
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DecimalMin("0")
    @DecimalMax("1000000")
    private BigDecimal price;

    @Valid
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id", nullable = false)
    private Treatment treatment;
}
