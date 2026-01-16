package com.prog.distribuida.customers.config;

import org.springframework.cloud.consul.serviceregistry.ConsulRegistrationCustomizer;

public class ConsulCOnfig {
   /* public ConsulRegistrationCustomizer customizer(){
        return registration -> {
            var tags = registration.getService().getTags();
                        tags.add("traefik.enable=true");
                        tags.add("traefik.http.routers.books.rule=PathPrefix(`/app-customers`)");
                        tags.add("traefik.http.middlewares.books-stripprefix.stripPrefix.prefixes=/app-customers");
                        tags.add("traefik.http.routers.books.middlewares=books-stripprefix");
        };
    }*/
}
