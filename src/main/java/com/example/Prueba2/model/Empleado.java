package com.example.Prueba2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="empleado")
public class Empleado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_empleado;

    @Column(nullable = false)
    @Size(max = 45)
    private String apellido;

    @Column(nullable = false)
    @Size(max = 45)
    private String nombre;

    @Column(nullable = false)
    @Size(max = 15)
    private String  telefono;

    @Column(nullable = false)
    @Size(max = 45)
    private String direccion;

    @Column(nullable = false)
    private Date fecha_nacimiento;

    @Column(nullable = false)
    private String observacion;

    @Column(nullable = false)
    private int dias_trabajo=0;

    @Column(nullable = false)
    @DecimalMin(value = "0.00", inclusive = true, message = "El sueldo debe ser no negativo y tener dos decimales.")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal sueldo;

    public void calcularSueldo() {
        if (dias_trabajo!=0) {
            BigDecimal pagoxdiaa = new BigDecimal("15");
            BigDecimal sueldoCalculado = pagoxdiaa.multiply(new BigDecimal(dias_trabajo));
            if (dias_trabajo >= 30) {
                sueldoCalculado = sueldoCalculado.multiply(new BigDecimal("1.05"));
            } else if (dias_trabajo >= 20) {
                sueldoCalculado = sueldoCalculado.multiply(new BigDecimal("1.02"));
            }
            this.sueldo = sueldoCalculado;
        }

    }
}
