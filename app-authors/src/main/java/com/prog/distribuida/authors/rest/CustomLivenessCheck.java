package com.prog.distribuida.authors.rest;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
@ApplicationScoped
public class CustomLivenessCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        // Lógica para verificar el estado de la aplicación
        boolean appStatus = true; // Por ejemplo, verificar si un servicio esencial está activo

        if (appStatus) {
            return HealthCheckResponse.up("Servicio esencial activo");
        } else {
            return HealthCheckResponse.down("Servicio esencial inactivo");
        }
    }
}