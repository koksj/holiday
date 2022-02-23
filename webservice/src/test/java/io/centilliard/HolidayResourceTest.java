package io.centilliard;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import javax.ws.rs.core.MediaType;

import com.google.common.net.HttpHeaders;

import org.junit.jupiter.api.Test;

import io.centilliard.rest.Country;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;

@QuarkusTest
public class HolidayResourceTest {

    @Test
    public void testHolidayByCountryCode() {

        Response response = given()
                .body(new Country("", "IL"))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                .post("http://localhost:8080/api/v1/public/holidays/find")
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_JSON)
                .extract().response();

        assertThat(response.asPrettyString()).contains("Israel");
    }

    @Test
    public void testHolidayByCountryCodeNull() {

        Response response = given()
                .body(new Country("Israel", null))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                .post("http://localhost:8080/api/v1/public/holidays/find")
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_JSON)
                .extract().response();

        assertThat(response.asPrettyString()).contains("Israel");
    }

    @Test
    public void testHolidayByCountryName() {

        Response response = given()
                .body(new Country("Israel", ""))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                .post("http://localhost:8080/api/v1/public/holidays/find")
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_JSON)
                .extract().response();

        assertThat(response.asPrettyString()).contains("First day of Passover");
    }

    @Test
    public void testHolidayByCountryNameNull() {

        Response response = given()
                .body(new Country(null, "IL"))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                .post("http://localhost:8080/api/v1/public/holidays/find")
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_JSON)
                .extract().response();

        assertThat(response.asPrettyString()).contains("First day of Passover");
    }

}
