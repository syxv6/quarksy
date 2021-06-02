# Developers-api project - Quarkus

This project implements the Developer Track API shoot-out project using Quarkus, the Supersonic Subatomic Java Framework.  If you want to learn more about Quarkus, please visit its website: https://quarkus.io/.

The app implements a REST api in accordance with the specification set out in the api-definition.yaml file.

## Running the application in dev mode

You can run this application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

### Running the H2 DB console

The H2 DB console is available at ```http://localhost:8080/h2```.  

Log in using jdbc:h2:mem:test with an empty username and password.  A Developers table is present with preloaded data.
