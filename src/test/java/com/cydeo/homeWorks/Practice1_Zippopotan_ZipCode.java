package com.cydeo.homeWorks;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Practice1_Zippopotan_ZipCode {

    /*
    Given Accept application/json
And path zipcode is 22031
When I send a GET request to /us endpoint
Then status code must be 200
And content type must be application/json
And Server header is cloudflare
And Report-To header exists
And body should contains following information
    post code is 22031
    country  is United States
    country abbreviation is US
    place name is Fairfax
    state is Virginia
    latitude is 38.8604
     */

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "https://api.zippopotam.us/";
    }
    @DisplayName("GET Zippopatamus hamcrest chaining matcher")
    @Test
    void test1() {
        given().contentType(ContentType.JSON)
                .and().pathParam("zipcode", 22031)
                .when().get("/us/{zipcode}")
                .then().statusCode(200).log().all()
                .and().contentType("application/json")
                .and().header("Server", "cloudflare")
                .and().header("Report-To", notNullValue())
                .and().body("'post code'", is("22031"))
                .and().body("country", is("United States"))
                .and().body("'country abbreviation'", is("US"))
                .and().body("places[0].'place name'", is("Fairfax"))
                .and().body("places[0].state", is("Virginia"))
                .and().body("places[0].latitude", is("38.8604"));
    }

    @DisplayName("GET Zippopatamus with POJO")
    @Test
    <Country>
    void test2() {
        Response response = given().contentType(ContentType.JSON)
                .and().pathParam("zipcode", 22031)
                .when().get("/us/{zipcode}")
                .then().statusCode(200)
                .and().contentType("application/json")
                .and().header("Server", "cloudflare")
                .and().header("Report-To", notNullValue())
                .and().extract().response();


    }


    /*
    Given Accept application/json
    And path zipcode is 50000
    When I send a GET request to /us endpoint
    Then status code must be 404
    And content type must be application/json
     */
    @DisplayName("GET status code 404")
    @Test
    void test3() {
        given().contentType(ContentType.JSON)
                .and().pathParam("zipcode", 50000)
                .when().get("/us/{zipcode}")
                .then().statusCode(404).log().status()
                .and().contentType("application/json");
    }


    /*
    Given Accepts application/json
    And path state is va
    And path city is fairfax
    When I send a GET request to /us endpoint
    Then status code must be 200
    And content type must be application/json
    And payload should contain following information
    country abbreviation is US
    country  United States
    place name  Fairfax
    each places must contain fairfax as a value
    each post code must start with 22
     */

    @DisplayName("GET VA and Fairfax hamcrest chaining matcher")
    @Test
    void test4() {

        given().contentType(ContentType.JSON)
                .and().pathParam("state", "va")
                .and().pathParam("city","fairfax")
                .when().get("/us/{state}/{city}")
                .then().statusCode(200).log().all()
                .and().contentType("application/json")
                .and().body("'country abbreviation'", is("US"))
                .and().body("country", is("United States"))
                .and().body("'place name'", is("Fairfax"))
                .and().body("places.'place name'", everyItem(containsString("Fairfax")))
                .and().body("places.'post code'", everyItem(startsWith("22")));
    }

    @DisplayName("GET VA and Fairfax with POJO")
    @Test
    void test5() {

        Response response = given().contentType(ContentType.JSON)
                .and().pathParam("state", "va")
                .and().pathParam("city", "fairfax")
                .when().get("/us/{state}/{city}")
                .then().statusCode(200)
                .and().contentType("application/json")
                .and().extract().response();


    }
}