package com.cydeo.day12;


import com.cydeo.utilities.SpartanNewBase;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

    public class SpartanSpecTest extends SpartanNewBase {
        private ResponseSpecification responseSpec;
        private RequestSpecification userSpec;



        //all tests in this class will use admin credentials
        //all test in this class will expect json result from response

        //all test in this class response status code is 200
        //all test in this class response conten type header is json


        @Test
        public void test1(){

        RequestSpecification reqSpec = given()
                                         .accept(ContentType.JSON)
                                         .and()
                                         .auth().basic("admin", "admin")
                                          .log().all();

        ResponseSpecification responseSpec = expect().statusCode(200)
                                                      .and()
                                                 .contentType(ContentType.JSON);


            given()
                    .spec(reqSpec)
                    .when()
                    .get("/spartans")
                    .then()
                    .spec(responseSpec);



        }

        @Test
        public void test2(){


            given()
                    .spec(userSpec)
                    .and()
                    .pathParam("id",8)
                    .when()
                    .get("/spartans/{id}")
                    .then()
                    .spec(responseSpec)
                    .body("gender",is("Male"));


        }


    }

