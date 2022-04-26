package com.cydeo.homeWorks;

import com.cydeo.utilities.DBUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class SpartanGetRequests_homeWork {

     /*
        Given Accept type is application/json
        When user send GET request to  /api/spartans end point
        Then status code must be 200
        And response content type must be application/json
     */
     String url = "http://3.83.127.158:8000" ;
    @DisplayName("Spartan get requests")
    @Test
    public void test1(){
       //we need to get api information
        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get(url + "/api/spartans")
                .then()
                .statusCode(200).extract().response();

       String query = "SELECT SPARTAN_ID,NAME,GENDER,PHONE\n" +
               "FROM SPARTANS\n";
        Map<String, Object> dbMap = DBUtils.getRowMap(query);

        System.out.println(dbMap);

        }




    }

