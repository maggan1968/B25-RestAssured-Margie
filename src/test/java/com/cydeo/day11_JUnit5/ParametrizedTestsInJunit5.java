package com.cydeo.day11_JUnit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;

public class ParametrizedTestsInJunit5 {

    @ParameterizedTest
    @ValueSource (ints = {1,3,4,5,6,7,8,21,32,43})
    public void testMultipleNumbers(int number){
        System.out.println("number = " + number);
        Assertions.assertTrue(number > 5);


    }
    @ParameterizedTest
    @ValueSource (strings ={"Muhtar","Asya", "Adam", "Ahmet"})
    public void testMultipleNames(String name){
        System.out.println("name = " + name);
    }

    // SEND GET REQUEST TO https://api.zippopotam.us/us/{zipCode}
    //you put in Postman https://api.zippopotam.us/us/:zipcode
    // with these zipcodes 22030,22031, 22032, 22033 , 22034, 22035, 22036
    // check status code 200

    @ParameterizedTest
    @ValueSource(ints = {22030,22031, 22032, 22033 , 22034, 22035, 22036})
    public void zipCodeTest(int zipCode){

        given()
                .baseUri("https://api.zippopotam.us")
                .pathParam("zipCode",zipCode)
                .log().all()
                .when()
                .get("/us/{zipCode}")
                .then().log().all()
                .statusCode(200);
    }
}