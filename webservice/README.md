# holiday Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/holiday-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Related Guides

- Hibernate ORM ([guide](https://quarkus.io/guides/hibernate-orm)): Define your persistent model with Hibernate ORM and JPA

## Provided Code

### Hibernate ORM

Create your first JPA entity

[Related guide section...](https://quarkus.io/guides/hibernate-orm)


./mvnw quarkus:add-extension -Dextensions="io.quarkus:quarkus-resteasy-reactive-jackson"

podman run --rm=true --name postgres-quarkus-hibernate -e POSTGRES_USER=hibernate -e POSTGRES_PASSWORD=hibernate -e POSTGRES_DB=hibernate_db -p 5432:5432 postgres:13.1

# Delete container
podman rm id

pg_dump --host=localhost --username=hibernate --table=country --data-only --column-inserts --dbname=hibernate_db  > country.sql
pg_dump --host=localhost --username=hibernate --table=country_holiday --data-only --column-inserts --dbname=hibernate_db  > country_holiday.sql
pg_dump --host=localhost --username=hibernate --table=holiday --data-only --column-inserts --dbname=hibernate_db  > holiday.sql

INSERT INTO public.country (countrypk, country_code, country_name) VALUES (1, 'IL', 'Israel');

INSERT INTO public.holiday (id, date, name) VALUES (1, '2022-04-15', 'First day of Passover');
INSERT INTO public.holiday (id, date, name) VALUES (2, '2022-04-16', 'First day of Passover');
INSERT INTO public.holiday (id, date, name) VALUES (3, '2022-04-21', 'Seventh day of Passover');
INSERT INTO public.holiday (id, date, name) VALUES (4, '2022-04-22', 'Seventh day of Passover');
INSERT INTO public.holiday (id, date, name) VALUES (5, '2022-05-04', 'Yom Ha''atzmaut');
INSERT INTO public.holiday (id, date, name) VALUES (6, '2022-05-05', 'Yom Ha''atzmaut');
INSERT INTO public.holiday (id, date, name) VALUES (7, '2022-06-04', 'Shavuot');
INSERT INTO public.holiday (id, date, name) VALUES (8, '2022-06-05', 'Shavuot');
INSERT INTO public.holiday (id, date, name) VALUES (9, '2022-09-25', 'Rosh Hashanah');
INSERT INTO public.holiday (id, date, name) VALUES (10, '2022-09-26', 'Rosh Hashanah');
INSERT INTO public.holiday (id, date, name) VALUES (11, '2022-09-27', 'Rosh Hashanah');
INSERT INTO public.holiday (id, date, name) VALUES (12, '2022-10-04', 'Yom Kippur');
INSERT INTO public.holiday (id, date, name) VALUES (13, '2022-10-05', 'Yom Kippur');
INSERT INTO public.holiday (id, date, name) VALUES (14, '2022-10-09', 'First day of Sukkot');
INSERT INTO public.holiday (id, date, name) VALUES (15, '2022-10-10', 'First day of Sukkot');
INSERT INTO public.holiday (id, date, name) VALUES (16, '2022-10-16', 'Simchat Torah');
INSERT INTO public.holiday (id, date, name) VALUES (17, '2022-10-16', 'Shemini Atzeret');
INSERT INTO public.holiday (id, date, name) VALUES (18, '2022-10-17', 'Simchat Torah');
INSERT INTO public.holiday (id, date, name) VALUES (19, '2022-10-17', 'Shemini Atzeret');

INSERT INTO public.country_holiday (countryentity_countrypk, holidayentities_id) VALUES (1, 1);
INSERT INTO public.country_holiday (countryentity_countrypk, holidayentities_id) VALUES (1, 2);
INSERT INTO public.country_holiday (countryentity_countrypk, holidayentities_id) VALUES (1, 3);
INSERT INTO public.country_holiday (countryentity_countrypk, holidayentities_id) VALUES (1, 4);
INSERT INTO public.country_holiday (countryentity_countrypk, holidayentities_id) VALUES (1, 5);
INSERT INTO public.country_holiday (countryentity_countrypk, holidayentities_id) VALUES (1, 6);
INSERT INTO public.country_holiday (countryentity_countrypk, holidayentities_id) VALUES (1, 7);
INSERT INTO public.country_holiday (countryentity_countrypk, holidayentities_id) VALUES (1, 8);
INSERT INTO public.country_holiday (countryentity_countrypk, holidayentities_id) VALUES (1, 9);
INSERT INTO public.country_holiday (countryentity_countrypk, holidayentities_id) VALUES (1, 10);
INSERT INTO public.country_holiday (countryentity_countrypk, holidayentities_id) VALUES (1, 11);
INSERT INTO public.country_holiday (countryentity_countrypk, holidayentities_id) VALUES (1, 12);
INSERT INTO public.country_holiday (countryentity_countrypk, holidayentities_id) VALUES (1, 13);
INSERT INTO public.country_holiday (countryentity_countrypk, holidayentities_id) VALUES (1, 14);
INSERT INTO public.country_holiday (countryentity_countrypk, holidayentities_id) VALUES (1, 15);
INSERT INTO public.country_holiday (countryentity_countrypk, holidayentities_id) VALUES (1, 16);
INSERT INTO public.country_holiday (countryentity_countrypk, holidayentities_id) VALUES (1, 17);
INSERT INTO public.country_holiday (countryentity_countrypk, holidayentities_id) VALUES (1, 18);
INSERT INTO public.country_holiday (countryentity_countrypk, holidayentities_id) VALUES (1, 19);