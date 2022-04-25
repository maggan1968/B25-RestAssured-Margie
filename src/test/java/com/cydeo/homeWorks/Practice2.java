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

public class Practice2 extends HrTestBase{

//- Given accept type is Json
//- Query param value - q={"department_id":80}
//- When users sends request to /employees
//- Then status code is 200
//- And Content - Type is Json
//- And all job_ids start with 'SA'
//- And all department_ids are 80
//- Count is 25

   @Test
   public void test1(){
       Response response = get("DEPARTMENTS") ;
   response.prettyPrint();

}
   @DisplayName("GET request to /employees with query param")
   @Test
   public void Test2(){
       Response response = given()
               .accept(ContentType.JSON)
               .and()
               .queryParam("q","{\"department_id\":80}")
               .log().all()
               .when()
               .get("/employees");
       //verify status code
       assertEquals(200, response.statusCode());
       //verify content type
       assertEquals("application/json",response.contentType());

       //And all job_ids start with 'SA'
       JsonPath jsonPath = response.jsonPath();
       List<String> list = jsonPath.getList("items.job_id");
       for (String each : list) {
           assertTrue(each.startsWith("SA"));
       }
       //And all department_ids are 80
       List<Integer> department_ids = jsonPath.getList("items.department_id");
       for (Integer each : department_ids) {
           assertTrue(each == 80);
       }
       //Count is 25
       assertEquals(25, jsonPath.getInt("count"));


   }
}