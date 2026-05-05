package com.tdea.go.repository;

import com.tdea.go.model.Viaje;
import com.tdea.go.model.EstadoViaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long> {

    List<Viaje> findByPasajeroIdOrderByFechaSolicitudDesc(Long pasajeroId);

    List<Viaje> findByConductorIdOrderByFechaSolicitudDesc(Long conductorId);

    List<Viaje> findByEstado(EstadoViaje estado);
}