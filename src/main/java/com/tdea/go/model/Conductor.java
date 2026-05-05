package com.tdea.go.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "conductores")
public class Conductor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "numero_licencia", length = 20)
    private String numeroLicencia;

    private Boolean disponible = false;

    @Enumerated(EnumType.STRING)
    private EstadoConductor estado = EstadoConductor.INACTIVO;

    @OneToOne(mappedBy = "conductor", cascade = CascadeType.ALL)
    private Vehiculo vehiculo;
}