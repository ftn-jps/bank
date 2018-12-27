# Bank simulation server

## Compiling from source

```
./mvnw package
```

## Usage

```
java -jar target/*.jar
```

Server listens on port `8085` by default. Use command line option `-Dserver.port=` to change it.

Default bank name is: `Bank-A`  
Default bank IIN is: `444444`  
Default database file is: `./dbtest`  
Use command line options `-Dbank.name=<name> -Dbank.iin=<iin> -Dspring.datasource.url=jdbc:h2:file:` to change the defaults.

Default URL where frontend is running (used for redirection): `https://localhost:4205`  
Use command line option `-Dfrontend.url=` to change it.

For example, to start a second bank, run the following command:
```bash
./mvnw spring-boot:run \
-Dbank.name=Bank-B \
-Dbank.iin=555555 \
-Dfrontend.url=https://localhost:4206 \
-Dserver.port=8086 \
-Dspring.datasource.url=jdbc:h2:file:./dbtest2
```
