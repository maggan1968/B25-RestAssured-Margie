package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ResponseTimeTest extends SpartanAuthTestBase {



    @DisplayName("GET request to all spartans and verify response time")
    @Test
    public void test1() {

        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin","admin")
                .when().get("/api/spartans")
                .then().statusCode(200)
                .and()
                //.time(lessThanOrEqualTo(1300L))
                //.time(greaterThan(500L))
                .time(both(greaterThan(500L)).and(lessThanOrEqualTo(1300L)))
                .extract().response();
        System.out.println("response.getTime() = " + response.getTime());

    }
}
