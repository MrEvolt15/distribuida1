package com.prog.distribuida;

import io.helidon.config.Config;
import io.helidon.dbclient.DbClient;
import io.helidon.dbclient.DbRow;
import io.helidon.http.media.jsonb.JsonbSupport;
import io.helidon.http.media.jsonp.JsonpSupport;
import io.helidon.webserver.WebServer;
import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;
import jakarta.json.Json;
import jakarta.json.JsonBuilderFactory;
import jakarta.json.JsonObject;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Stream;

public class MiAplicacionMain {

    static JsonBuilderFactory factory =Json.createBuilderFactory(Map.of());
    static DbClient dbClient = DbClient.builder()
            .config(Config.create().get("db"))
            .build();
    static void handleHola (ServerRequest req, ServerResponse res) {
        var name =req.path().pathParameters().get("name");

        JsonObject response = factory.createObjectBuilder()
            .add("name", "Hello "+name+" ")
                .add("hechaAhora", LocalDateTime.now().toString())
            .build();

        res.send(response);
    }
    static void handleHola2 (ServerRequest req, ServerResponse res) {
        var name =req.path().pathParameters().get("name");
        //var dbClient = DbClient.builder()
        //        .config(configDb)
        //        .build();
        //Stream<DbRow> rows = dbClient.execute()
        //        .createQuery("select * from books")
        //        .execute();
        //rows.forEach(row -> {
        //    System.out.println(row.column("isbn").getString());
        //});

        var books = dbClient.execute()
                .createQuery("select * from books")
                .execute()
                .map(row -> {
                    Book b = new Book();
                    b.setIsbn(row.column("isbn").getString());
                    b.setTitle(row.column("title").getString());
                    return b;
                })
                .toList();

        Persona p = new Persona();
        p.setName("Hola "+name);
        p.setHechaAhora(LocalDateTime.now());
        res.send(p);
    }
    static void handleBook (ServerRequest req, ServerResponse res) {
        var isbn = req.path().pathParameters().get("isbn");
        //var dbClient = DbClient.builder()
        //        .config(configDb)
        //        .build();
        //Stream<DbRow> rows = dbClient.execute()
        //        .createQuery("select * from books")
        //        .execute();
        //rows.forEach(row -> {
        //    System.out.println(row.column("isbn").getString());
        //});

        dbClient.execute()
                .createQuery("select * from books where isbn = ?")
                .params(isbn)
                .execute()
                .map(row -> {
                    Book b = new Book();
                    b.setIsbn(row.column("isbn").getString());
                    b.setTitle(row.column("title").getString());
                    return b;
                })
                .ifPresentOrElse(
                        res::send,
                        () -> res.send("no encontrado")
                );


        //Persona p = new Persona();
        //p.setName("Hola "+name);
        //p.setHechaAhora(LocalDateTime.now());
        //res.send(books);
    }

    public static void main(String[] args) {

        Config config = Config.create();
        var configHttp = config.get("server");
        var configDb = config.get("db");
        System.out.println("conection.url");

        var dbClient = DbClient.builder()
                        .config(configDb)
                                .build();
        Stream<DbRow> rows = dbClient.execute()
                        .createQuery("select * from books")
                                .execute();
        rows.forEach(row -> {
            System.out.println(row.column("isbn").getString());
        });



        WebServer.builder()
                //.port(8080)
                .config(configHttp)
                .mediaContext(it ->it
                        .mediaSupportsDiscoverServices(true)
                        .addMediaSupport(JsonpSupport.create())
                        .addMediaSupport(JsonbSupport.create())
                )
                .routing(it -> it
                        .get("/hola1/{name}",MiAplicacionMain::handleHola)
                        .get("/hola2/{name}",MiAplicacionMain::handleHola2)

                )

                .build()
                .start();
    }
}
