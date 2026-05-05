package com.tdea.go.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "viajes")
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pasajero_id")
    private Usuario pasajero;

    @ManyToOne
    @JoinColumn(name = "conductor_id")
    private Conductor conductor;

    @Column(nullable = false, length = 200)
    private String origen;

    @Column(nullable = false, length = 200)
    private String destino;

    private Double latitudOrigen;
    private Double longitudOrigen;
    private Double latitudDestino;
    private Double longitudDestino;

    @Enumerated(EnumType.STRING)
    private EstadoViaje estado = EstadoViaje.SOLICITADO;

    @Column(name = "fecha_solicitud")
    private LocalDateTime fechaSolicitud;

    @Column(name = "fecha_inicio")
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDateTime fechaFin;

    @Column(name = "calificacion_pasajero")
    private Integer calificacionPasajero;

    @Column(name = "calificacion_conductor")
    private Integer calificacionConductor;

    @PrePersist
    protected void onCreate() {
        fechaSolicitud = LocalDateTime.now();
    }
}