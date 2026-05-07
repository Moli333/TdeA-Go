package com.tdea.go.controller;

import com.tdea.go.dto.*;
import com.tdea.go.model.*;
import com.tdea.go.service.ViajeService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viajes")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class ViajeController {

    private final ViajeService viajeService;

    // POST /api/viajes/solicitar?pasajeroId=1
    @PostMapping("/solicitar")
    public Viaje solicitar(
            @RequestBody SolicitarViajeRequest req,
            @RequestParam Long pasajeroId) {

        return viajeService.solicitarViaje(
                req,
                pasajeroId
        );
    }

    // PUT /api/viajes/5/completar
    @PutMapping("/{id}/completar")
    public Viaje completar(
            @PathVariable Long id,
            @RequestBody CompletarViajeRequest req) {

        return viajeService.completarViaje(id, req);
    }

    // GET /api/viajes/historial/1
    @GetMapping("/historial/{pasajeroId}")
    public List<Viaje> historial(
            @PathVariable Long pasajeroId) {

        return viajeService
                .historialPasajero(pasajeroId);
    }

    // GET /api/viajes/conductores-disponibles
    @GetMapping("/conductores-disponibles")
    public List<Conductor> conductoresDisponibles() {

        return viajeService.conductoresDisponibles();
    }
}