package com.cydeo.homeWorks;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;



public class Practice1 extends HrTestBase{

    //Q1:
// - Given accept type is Json
//- Path param value- US
//- When users sends request to /countries
//- Then status code is 200
//- And Content - Type is Json
//- And country_id is US
//- And Country_name is United States of America
//- And Region_id is 2
    @Test
    public void test1(){
        Response response = get("REGIONS") ;
        response.prettyPrint();
    }
@DisplayName("GET request to /countries with path param")
    @Test
    public void test2(){
        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .pathParams("id","US")
                .when()
                .get("/countries/{id}");

      //- Then status code is 200
        // verify status code
    assertEquals(200,response.statusCode());
    //- And Content - Type is Json
    //verify content type
    assertEquals("application/json",response.contentType());

    //- And country_id is US
    JsonPath jsonPath = response.jsonPath();
    assertEquals("US",jsonPath.getString("country_id"));

//- And Country_name is United States of America
    assertEquals("United States of America",jsonPath.getString("country_name"));

//- And Region_id is 2
    assertEquals(2, jsonPath.getInt("region_id"));

}

}
