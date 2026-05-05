package com.tdea.go.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "vehiculos")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "conductor_id")
    private Conductor conductor;

    @Column(nullable = false, length = 50)
    private String marca;

    @Column(nullable = false, length = 50)
    private String modelo;

    private String anio;

    @Column(nullable = false, unique = true, length = 10)
    private String placa;

    private String color;

    @Column(name = "capacidad_pasajeros")
    private Integer capacidadPasajeros = 4;
}