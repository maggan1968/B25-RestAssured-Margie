package com.cydeo.utilities;



    import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

    public class SpartanNewBase {


        @BeforeAll
        public static void init(){
            baseURI ="http://44.201.121.105";
            port = 7000;
            basePath ="/api";
        }

        @AfterAll
        public static void close(){
            //reste the info we set above, method comes from restassured
            reset();
        }
    }

