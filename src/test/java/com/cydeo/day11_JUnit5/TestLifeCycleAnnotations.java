package com.cydeo.day11_JUnit5;

import org.junit.jupiter.api.*;

public class TestLifeCycleAnnotations {

    //beforeClass is testNg version of before,some logic
    @BeforeAll
    public static void init() {
        System.out.println("Before all is running");
    }
    //beforeMethod is testNg version of beforeEach,some logic
    @BeforeEach
    public void initEach() {
        System.out.println("\tBefore all is running");
    }

    @AfterEach
    public void closeEach() {
        System.out.println("\tAfter all is closing");
    }

    @Test
    public void test1(){
        System.out.println("Test 1 is running");

    }
    @Test
    public void test2(){
        System.out.println("Test 2 is running");

    }

    @AfterAll
    public static void close() {
        System.out.println("AfterAll is closing");
    }
}
