package com.cydeo.day10;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
public class SSLTest {

    @Test
    public void test1(){
        given().relaxedHTTPSValidation().
                when()
                .get("https://untrusted-root.badssl.com/")
                .prettyPrint();
    }


    @Test
    public void test2(){

        given().keyStore("","")
                .when().get("url");

    }


}
