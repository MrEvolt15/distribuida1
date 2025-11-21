plugins {
    id("java")
    //id("application")
    id("io.freefair.lombok") version "9.1.0"
    id("com.gradleup.shadow") version "9.2.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    implementation("io.helidon.webserver:helidon-webserver:4.3.2")
    implementation("io.helidon.http.media:helidon-http-media-jsonp:4.3.2")
    implementation("io.helidon.http.media:helidon-http-media-jsonb:4.3.2")

    // https://mvnrepository.com/artifact/io.helidon.config/helidon-config-yaml
    runtimeOnly("io.helidon.config:helidon-config-yaml:4.3.2")
    // https://mvnrepository.com/artifact/io.helidon.dbclient/helidon-dbclient-jdbc
    implementation("io.helidon.dbclient:helidon-dbclient-jdbc:4.3.2")
    implementation("io.helidon.dbclient:helidon-dbclient:4.3.2")
    implementation("io.helidon.dbclient:helidon-dbclient-hikari:4.3.2")
    // https://mvnrepository.com/artifact/org.postgresql/postgresql
    implementation("org.postgresql:postgresql:42.7.8")


}

tasks.test {
    useJUnitPlatform()
}

tasks.jar{
    manifest{
        attributes["Main-Class"] = "com.prog.distribuida.MiAplicacionMain"
    }
}

tasks.shadowJar{
    mergeServiceFiles()
}