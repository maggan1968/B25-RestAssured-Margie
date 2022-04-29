package com.cydeo.utilities;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanAuthTestBase;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

    public class spartanUtils extends SpartanAuthTestBase {

        private static final String endpoint = "/api/spartans";
        static String spartanName, spartanGender;
        static int spartanID = 0;

        /**
         * Method creates random spartan
         *
         * @return
         */
        public static Spartan createSpartan() {
            Faker faker = new Faker();

            int rand = faker.number().numberBetween(0, 1);
            String gender = "";
            if (rand == 0) gender = "Male";
            else gender = "Female";
            long phoneNum = Long.parseLong(faker.number().digits(10));

            Spartan spartan = new Spartan();

            spartan.setName(faker.name().firstName());
            spartan.setGender(gender);
            spartan.setPhone(phoneNum);

            return spartan;
        }

        /**
         * Method GET json from given endpoint
         *
         * @param username
         * @param password
         * @return
         */
        public static Response get(String username, String password) {

            return given().auth().basic(username, password)
                    .and().contentType(ContentType.JSON)
                    .and().accept(ContentType.JSON)
                    .when().get(endpoint);
        }

        /**
         * Method GET json from endpoint by given ID
         *
         * @param username
         * @param password
         * @param id
         * @return
         */
        public static Response get(String username, String password, int id) {

            return given().auth().basic(username, password)
                    .and().contentType(ContentType.JSON)
                    .and().accept(ContentType.JSON)
                    .and().pathParam("id", id)
                    .when().get(endpoint + "/{id}");
        }

        /**
         * Method will post spartan to given endpoint
         *
         * @param username
         * @param password
         * @param spartan
         * @return
         */
        public static Response post(String username, String password, Spartan spartan) {

            return given().auth().basic(username, password)
                    .and().contentType(ContentType.JSON)
                    .and().accept(ContentType.JSON)
                    .and().body(spartan)
                    .when().post(endpoint);
        }

        /**
         * Method puts spartan to given id
         *
         * @param username
         * @param password
         * @param spartan
         * @param id
         * @return
         */
        public static Response put(String username, String password, Spartan spartan, int id) {

            return given().auth().basic(username, password)
                    .and().contentType(ContentType.JSON)
                    .and().accept(ContentType.JSON)
                    .and().pathParam("id", id)
                    .and().body(spartan)
                    .when().put(endpoint + "/{id}");
        }

        /**
         * Method delete spartan by given id
         *
         * @param username
         * @param password
         * @param id
         * @return
         */
        public static Response delete(String username, String password, int id) {
            return given().auth().basic(username, password)
                    .and().pathParam("id", id)
                    .when().delete(endpoint + "/{id}");
        }

        /**
         * Method patches given id by key and value
         *
         * @param username
         * @param password
         * @param key
         * @param value
         * @param id
         * @return
         */
        public static Response patch(String username, String password, String key, String value, int id) {
            Map<String, Object> putRequestMap = new HashMap<>();
            putRequestMap.put(key, value);
            return given().auth().basic(username, password)
                    .and().contentType(ContentType.JSON)
                    .and().accept(ContentType.JSON)
                    .and().pathParam("id", id)
                    .and().body(putRequestMap)
                    .when().patch(endpoint + "/{id}");
        }

        /**
         * Method search amoung spartans with given gender and name contains
         *
         * @param username
         * @param password
         * @param gender
         * @param nameContains
         * @return
         */
        public static Response search(String username, String password, String gender, String nameContains) {

            return given().auth().basic(username, password)
                    .and().contentType(ContentType.JSON)
                    .and().accept(ContentType.JSON)
                    .and().queryParam("gender", gender)
                    .and().queryParam("nameContains", nameContains)
                    .when().get(endpoint + "/search");
        }

        /**
         * Method validates all the spartan end points by given user credentials and maps of access
         *
         * @param map
         * @param username
         * @param password
         */
        public static void validateEndpoints(Map<String, Boolean> map, String username, String password) {

            Spartan spartan = spartanUtils.createSpartan();
            spartanName = spartan.getName();
            spartanGender = spartan.getGender();

            for (Map.Entry<String, Boolean> entry : map.entrySet()) {

                Response response;

                switch (entry.getKey()) {
                    case "GET": {

                        response = spartanUtils.get(username, password);
                        System.out.println("GET - Status code = " + response.getStatusCode());
                        boolean actualStatus = String.valueOf(response.getStatusCode()).startsWith("2");
                        assertThat(actualStatus, is(entry.getValue()));

                        break;
                    }
                    case "POST": {

                        response = spartanUtils.post(username, password, spartan);
                        System.out.println("POST - Status code = " + response.getStatusCode());
                        boolean actualStatus = String.valueOf(response.getStatusCode()).startsWith("2");
                        sleep(0.2);
                        assertThat(actualStatus, is(entry.getValue()));

                        if (entry.getValue()) {
                            response = spartanUtils.search(username, password, spartanGender, spartanName);
                            assertThat(response.path("content[0].name"), is(spartanName));
                            spartanID = response.path("content[0].id");
                        }

                        break;
                    }
                    case "GET_ID": {

                        response = spartanUtils.get(username, password, 15);
                        System.out.println("GET{id} - Status code = " + response.getStatusCode());
                        boolean actualStatus = String.valueOf(response.getStatusCode()).startsWith("2");
                        assertThat(actualStatus, is(entry.getValue()));

                        break;
                    }
                    case "PUT": {

                        response = spartanUtils.put(username, password, spartan, spartanID);
                        System.out.println("PUT - Status code = " + response.getStatusCode());
                        boolean actualStatus = String.valueOf(response.getStatusCode()).startsWith("2");
                        assertThat(actualStatus, is(entry.getValue()));

                        if (entry.getValue()) {
                            response = spartanUtils.get(username, password, spartanID);
                            assertThat(response.path("name"), is(spartanName));
                        }

                        break;
                    }
                    case "DELETE": {

                        response = spartanUtils.delete(username, password, spartanID);
                        System.out.println("DELETE - Status code = " + response.getStatusCode());
                        boolean actualStatus = String.valueOf(response.getStatusCode()).startsWith("2");
                        assertThat(actualStatus, is(entry.getValue()));

                        if (entry.getValue()) {
                            response = spartanUtils.get(username, password, spartanID);
                            assertThat(response.statusCode(), is(404));
                        }

                        break;
                    }
                    case "PATCH": {

                        Faker faker = new Faker();
                        String randomName = faker.name().firstName();

                        response = spartanUtils.patch(username, password, "name", randomName, spartanID);
                        System.out.println("PATCH - Status code = " + response.getStatusCode());
                        boolean actualStatus = String.valueOf(response.getStatusCode()).startsWith("2");
                        assertThat(actualStatus, is(entry.getValue()));

                        if (entry.getValue()) {
                            response = spartanUtils.get(username, password, spartanID);
                            assertThat(response.path("name"), is(randomName));
                        }

                        break;
                    }
                    case "SEARCH": {

                        response = spartanUtils.get(username, password, 55);
                        System.out.println("SEARCH - Status code = " + response.getStatusCode());
                        boolean actualStatus = String.valueOf(response.getStatusCode()).startsWith("2");
                        assertThat(actualStatus, is(entry.getValue()));

                        break;
                    }
                }

            }
        }

        /**
         * This method will accept int (in seconds) and execute Thread.sleep
         * for given duration
         *
         * @param second
         */
        public static void sleep(double second) {

            try {
                Thread.sleep((int) (second * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

