package com.prog.distribuida.authors.servicios;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.vertx.core.Vertx;
import io.vertx.ext.consul.ConsulClient;
import io.vertx.ext.consul.ConsulClientOptions;
import io.vertx.ext.consul.ServiceOptions;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.net.InetAddress;
import java.util.UUID;

@ApplicationScoped
public class AuthorsLifecycle {
    @Inject
    @ConfigProperty(name = "consul.host", defaultValue = "127.0.0.1")
    String consulHost;

    @Inject
    @ConfigProperty(name = "consul.port", defaultValue = "8500")
    Integer consulPort;

    @Inject
    @ConfigProperty(name = "quarkus.http.port", defaultValue = "8070")
    Integer appPort;

    String serviceId;

    public void init(@Observes StartupEvent event, Vertx vertx) {
        System.out.println("*************************AuthorsLifecycle.init()");
        System.out.println(vertx);

        try {
            ConsulClientOptions options = new ConsulClientOptions()
                    .setHost(consulHost)
                    .setPort(consulPort);
            ConsulClient client = ConsulClient.create(vertx, options);

            serviceId = UUID.randomUUID().toString();
            var ipAddress = InetAddress.getLocalHost().getHostAddress();
            ServiceOptions serviceOptions = new ServiceOptions()
                    .setName("app-authors")
                    .setId(serviceId)
                    .setAddress(ipAddress)
                    .setPort(appPort);
            client.registerService(serviceOptions)
                    .onSuccess(it -> {
                        System.out.println("Authors Registered service: " + serviceId);
                    })
                    .onFailure(err -> {
                        err.printStackTrace();
                    });

        }
        catch (Exception e){e.printStackTrace();}
    }

    public void stop(@Observes ShutdownEvent event, Vertx vertx) {
        System.out.println("*************************AuthorsLifecycle.stop()");
        ConsulClientOptions options = new ConsulClientOptions()
                .setHost(consulHost)
                .setPort(consulPort);
        ConsulClient client = ConsulClient.create(vertx, options);

        client.deregisterService(serviceId)
                .onSuccess(it -> {
                    System.out.println("Authors service deregistred: " + serviceId);
                })
                .onFailure(err -> {
                    err.printStackTrace();
                });
    }
}
