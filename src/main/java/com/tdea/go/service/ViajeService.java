package com.tdea.go.service;

import com.tdea.go.dto.*;
import com.tdea.go.model.*;
import com.tdea.go.repository.*;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ViajeService {

    private final ViajeRepository viajeRepo;
    private final UsuarioRepository usuarioRepo;
    private final ConductorRepository conductorRepo;
    private final PagoRepository pagoRepo;

    public Viaje solicitarViaje(
            SolicitarViajeRequest req,
            Long pasajeroId) {

        Usuario p = usuarioRepo.findById(pasajeroId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Pasajero no encontrado"));

        Viaje v = new Viaje();

        v.setPasajero(p);

        v.setOrigen(req.getOrigen());
        v.setDestino(req.getDestino());

        v.setLatitudOrigen(req.getLatitudOrigen());
        v.setLongitudOrigen(req.getLongitudOrigen());

        v.setLatitudDestino(req.getLatitudDestino());
        v.setLongitudDestino(req.getLongitudDestino());

        return viajeRepo.save(v);
    }

    public Viaje completarViaje(
            Long viajeId,
            CompletarViajeRequest req) {

        Viaje v = viajeRepo.findById(viajeId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Viaje no encontrado"));

        v.setEstado(EstadoViaje.COMPLETADO);
        v.setFechaFin(LocalDateTime.now());

        Pago pago = new Pago();

        pago.setViaje(v);
        pago.setMonto(req.getMonto());
        pago.setMetodoPago(req.getMetodoPago());
        pago.setEstadoPago(EstadoPago.COMPLETADO);

        pagoRepo.save(pago);

        if (v.getConductor() != null) {

            Conductor c = v.getConductor();

            c.setDisponible(true);
            c.setEstado(EstadoConductor.ACTIVO);

            conductorRepo.save(c);
        }

        return viajeRepo.save(v);
    }

    public List<Viaje> historialPasajero(Long id) {

        return viajeRepo
                .findByPasajeroIdOrderByFechaSolicitudDesc(id);
    }

    public List<Conductor> conductoresDisponibles() {

        return conductorRepo
                .findByDisponibleTrueAndEstado(
                        EstadoConductor.ACTIVO
                );
    }
}