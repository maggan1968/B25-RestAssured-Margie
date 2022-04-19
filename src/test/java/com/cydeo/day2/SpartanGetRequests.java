package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpartanGetRequests {


    String url = "http://44.201.121.105:8000";

    /*
        Given Accept type is application/json
        When user send GET request to  /api/spartans end point
        Then status code must be 200
        And response content type must be application/json
     */
    @Test
    public void test1(){

        Response response = RestAssured.
                given().
                accept(ContentType.JSON)
                .when()
                .get(url + "/api/spartans");
        //print the status code
        System.out.println("response.statusCode() = " + response.statusCode());
        //print the content type
        System.out.println("response.contentType() = " + response.contentType());

        //how to test API ?
        //verify status code is 200
        Assertions.assertEquals(200,response.statusCode());

        //verify content type is application json
        Assertions.assertEquals("application/json",response.contentType());


    }
     /*
        Given accept header is application/json
        When users send a get request to /api/spartans/3
        Then status code must be 200
        And Content type must be application/json
        And json body should contain 'Fidole'
     */

    @Test
    public void test2(){

        Response response = RestAssured.
                given().
                accept(ContentType.JSON)
                .when()
                .get(url + "/api/spartans/3");
        //how to test API ?
        //verify status code is 200
        Assertions.assertEquals(200,response.statusCode());

        //verify content type is application json
        Assertions.assertEquals("application/json",response.contentType());

       //verify Fidole exist in json body
        Assertions.assertTrue(response.body().asString().contains("Fidole"));




    }
}
