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
public class Practice3 extends HrTestBase {

//- Given accept type is Json
//-Query param value q= region_id 3
//- When users sends request to /countries
//- Then status code is 200
//- And all regions_id is 3
//- And count is 6
//- And hasMore is false
//- And Country_name are;
//-Australia,China,India,Japan,Malaysia,Singapore


    @DisplayName("GET request to /countries with query param")
    @Test
    public void test2(){

        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .queryParam("q","{\"region_id\":3}")
                .when()
                .get("/countries");
        //verify status code
        assertEquals(200, response.statusCode());
        //verify content type
        assertEquals("application/json",response.contentType());


        //- And all regions_id is 3
        JsonPath jsonPath = response.jsonPath();
        List<Integer>list = jsonPath.getList("item.REGION_ID");
        for (Integer each :list) {
            assertTrue(each == 3);
        }
        //- And count is 6
        assertEquals(6, jsonPath.getInt("count"));

        //- And hasMore is false
        assertFalse(jsonPath.getBoolean("hasMore"));
        //- And Country_name are;
        //-Australia,China,India,Japan,Malaysia,Singapore
        List<String>countryName = jsonPath.getList("items.COUNTRY_NAME");
        for (String each : countryName) {
            assertTrue(each.contains("Australia,China,India,Japan,Malaysia,Singapore"));

        }

        }

    }

