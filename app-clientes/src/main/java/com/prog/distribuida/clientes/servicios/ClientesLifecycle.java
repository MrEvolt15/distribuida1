package com.prog.distribuida.clientes.servicios;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.vertx.core.Vertx;
import io.vertx.ext.consul.CheckOptions;
import io.vertx.ext.consul.ConsulClient;
import io.vertx.ext.consul.ConsulClientOptions;
import io.vertx.ext.consul.ServiceOptions;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.net.InetAddress;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ClientesLifecycle {
    @Inject
    @ConfigProperty(name = "consul.host", defaultValue = "127.0.0.1")
    String consulHost;

    @Inject
    @ConfigProperty(name = "consul.port", defaultValue = "8500")
    Integer consulPort;

    @Inject
    @ConfigProperty(name = "quarkus.http.port", defaultValue = "8080")
    Integer appPort;

    String serviceId;

    public void init(@Observes StartupEvent event, Vertx vertx) {
        System.out.println("*************************ClientesLifecycle.init()");
        System.out.println(vertx);

        try {
            ConsulClientOptions options = new ConsulClientOptions()
                    .setHost(consulHost)
                    .setPort(consulPort);
            ConsulClient client = ConsulClient.create(vertx, options);

            serviceId = UUID.randomUUID().toString();
            var ipAddress = InetAddress.getLocalHost().getHostAddress();

            var urlCheck = String.format("http://%s:%d/ping", ipAddress, appPort);
            var checkOptions = new CheckOptions()
                    .setHttp(urlCheck)
                    .setInterval("10s")
                    .setDeregisterAfter("10s");

            var tags = List.of(
                    "traefik.enable=true",
                    "traefik.http.routers.authors.rule=PathPrefix(`/app-clientes`)",
                    "traefik.http.middlewares.authors-stripprefix.stripPrefix.prefixes=/app-clientes",
                    "traefik.http.routers.authors.middlewares=authors-stripprefix"
            );

            ServiceOptions serviceOptions = new ServiceOptions()
                    .setName("app-clientes")
                    .setId(serviceId)
                    .setAddress(ipAddress)
                    .setPort(appPort)
                    .setCheckOptions(checkOptions)
                    .setTags(tags);

            client.registerService(serviceOptions)
                    .onSuccess(it -> {
                        System.out.println("Clientes Registered service: " + serviceId);
                    })
                    .onFailure(err -> {
                        err.printStackTrace();
                    });

        }
        catch (Exception e){e.printStackTrace();}
    }

    public void stop(@Observes ShutdownEvent event, Vertx vertx) {
        System.out.println("*************************ClientesLifecycle.stop()");
        ConsulClientOptions options = new ConsulClientOptions()
                .setHost(consulHost)
                .setPort(consulPort);
        ConsulClient client = ConsulClient.create(vertx, options);

        client.deregisterService(serviceId)
                .onSuccess(it -> {
                    System.out.println("Clientes service deregistred: " + serviceId);
                })
                .onFailure(err -> {
                    err.printStackTrace();
                });
    }
}
