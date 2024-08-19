package com.example.Prueba2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="vehiculo")
public class Vehiculo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelo;
    private String anio;
    @Column(nullable = false)
    @DecimalMin(value = "0.00", inclusive = true, message = "El sueldo debe ser no negativo y tener dos decimales.")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal precio;

    private String descripcion;
    private String estado;
}
