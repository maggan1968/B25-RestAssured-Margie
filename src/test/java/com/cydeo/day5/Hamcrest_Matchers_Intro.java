package com.cydeo.day5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Hamcrest_Matchers_Intro {


    @DisplayName("Assertion with Numbers")
    @Test
    public void test1(){

        assertThat(5+5,is(10));
        assertThat(5+5,equalTo(10));

        //matchers has 2 overloaded value
        //first that accept actual value
        //second that accept another matchers
        assertThat(5+5,is(equalTo(10)));

        assertThat(5+5,not(9));
        assertThat(5+5,is(not(9)));
        assertThat(5+5,is(not(equalTo(9))));

        //greaterThan()
        //greaterThanOrEqual()
        //lessThan()
        //lessThanOrEqual()
        assertThat(5+5,is(greaterThan(9)));

    }

    @DisplayName("Assortion with String")
    @Test
    public void test2(){

        String text = "B25 is learning Hamcrest";

        //checking for equality
        assertThat(text,is("B25 is learning Hamcrest"));
        assertThat(text,equalTo("B25 is learning Hamcrest"));
        assertThat(text,is(equalTo("B25 is learning Hamcrest")));

        //check if this text starts with B25
        assertThat(text,startsWith("B25"));
        //case insensitive
        assertThat(text,startsWithIgnoringCase("b25"));
        //ends with
        assertThat(text,endsWith("rest"));

        //check if text contains String learning

        assertThat(text,containsString("learning"));
        //with ignoring case
        assertThat(text,containsStringIgnoringCase("LEARNING"));

        String str ="   ";

        //check if above str is blank
        assertThat(str,blankString());
        //check if trimmed str is empty string
        assertThat(str.trim(),emptyString());
    }

    @DisplayName("Hamcrest for Collection")
    @Test
    public void testCollection(){

        List<Integer>listOfNumbers = Arrays.asList(1,2,3,4,4,555,5,6,77,66,76);

        //check size of the list
        assertThat(listOfNumbers,hasSize(11));

        //check if the list has Item 54
        assertThat(listOfNumbers,hasItem(54));

        //check if that list hatItems 3,555,6
        assertThat(listOfNumbers,hasItems(3,555,6));

        //check if all numbers are grater than 0;
        assertThat(listOfNumbers,everyItem(greaterThan(0)));

    }
}

