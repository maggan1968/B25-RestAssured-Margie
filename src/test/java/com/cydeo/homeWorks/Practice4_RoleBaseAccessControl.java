package com.cydeo.homeWorks;

import com.cydeo.utilities.SpartanAuthTestBase;
import com.cydeo.utilities.spartanUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

public class Practice4_RoleBaseAccessControl extends SpartanAuthTestBase {

    /*
        As a homework,write a detailed test for Role Base Access Control(RBAC)
            in Spartan Auth app (7000)
            Admin should be able take all CRUD
            Editor should be able to take all CRUD
                other than DELETE
            User should be able to only READ data
                not update,delete,create (POST,PUT,PATCH,DELETE)
       --------------------------------------------------------
        Can guest even read data ? 401 for all

     */

    @DisplayName("1-ADMIN - test for Role Base Access Control(RBAC)")
    @Test
    void test1() {

        String username = "admin";
        String password = "admin";

        Map<String, Boolean> adminRights = new LinkedHashMap<>();
        adminRights.put("GET", true);
        adminRights.put("POST", true);
        adminRights.put("GET_ID", true);
        adminRights.put("PUT", true);
        adminRights.put("PATCH", true);
        adminRights.put("SEARCH", true);
        adminRights.put("DELETE", true);

        System.out.println("\n====== Validating Admin endpoints ======");
        spartanUtils.validateEndpoints(adminRights, username, password);
    }

    @DisplayName("2-EDITOR - test for Role Base Access Control(RBAC)")
    @Test
    void test2() {

        String username = "editor";
        String password = "editor";

        Map<String, Boolean> editorRights = new LinkedHashMap<>();
        editorRights.put("GET", true);
        editorRights.put("POST", true);
        editorRights.put("GET_ID", true);
        editorRights.put("PUT", true);
        editorRights.put("PATCH", true);
        editorRights.put("SEARCH", true);
        editorRights.put("DELETE", false);

        System.out.println("\n====== Validating Editor endpoints ======");
        spartanUtils.validateEndpoints(editorRights, username, password);
    }

    @DisplayName("3-USER - test for Role Base Access Control(RBAC)")
    @Test
    void test3() {

        String username = "user";
        String password = "user";

        Map<String, Boolean> userRights = new LinkedHashMap<>();
        userRights.put("GET", true);
        userRights.put("POST", false);
        userRights.put("GET_ID", true);
        userRights.put("PUT", false);
        userRights.put("PATCH", false);
        userRights.put("SEARCH", true);
        userRights.put("DELETE", false);

        System.out.println("\n====== Validating User endpoints ======");
        spartanUtils.validateEndpoints(userRights, username, password);
    }

    @DisplayName("4-GUEST - test for Role Base Access Control(RBAC)")
    @Test
    void test4() {

        String username = "";
        String password = "";

        Map<String, Boolean> guestRights = new LinkedHashMap<>();
        guestRights.put("GET", false);
        guestRights.put("POST", false);
        guestRights.put("GET_ID", false);
        guestRights.put("PUT", false);
        guestRights.put("PATCH", false);
        guestRights.put("SEARCH", false);
        guestRights.put("DELETE", false);

        System.out.println("\n====== Validating Guest endpoints ======");
        spartanUtils.validateEndpoints(guestRights, username, password);
    }
}