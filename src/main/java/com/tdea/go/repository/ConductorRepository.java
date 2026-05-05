package com.tdea.go.repository;

import com.tdea.go.model.Conductor;
import com.tdea.go.model.EstadoConductor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConductorRepository extends JpaRepository<Conductor, Long> {

    List<Conductor> findByDisponibleTrueAndEstado(EstadoConductor estado);

    Optional<Conductor> findByUsuarioId(Long usuarioId);
}