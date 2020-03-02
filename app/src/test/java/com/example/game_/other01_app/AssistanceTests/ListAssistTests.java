package com.example.game_.other01_app.AssistanceTests;


import com.example.game_.other01_app.AssistanceClasses.ListAssist;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class ListAssistTests {

    @Test
    public void testStringTurnedToList(){
        String testString = "a,b,c";
        List<String> testList = ListAssist.convertStringToListOf(testString);
        assertNotNull(testList);
        assertEquals(3, testList.size());
        assertEquals("a", testList.get(0));
        assertEquals("b", testList.get(1));
        assertEquals("c", testList.get(2));
        assertTrue(testList.contains("a"));
    }

    @Test
    public void testListTurnedToString(){
        List<String> testList = new ArrayList<>();
        testList.add("a");
        testList.add("b");
        testList.add("c");
        String actualString = ListAssist.convertListToString(testList);
        assertEquals("a,b,c", actualString);
    }

    @Test
    public void testInstructionConversion(){
        String testString = "Test\nTEST\ntest";
        List<String> testList = new ArrayList<>();
        testList.add("Test");
        testList.add("TEST");
        testList.add("test");
        assertEquals(testList.get(0), ListAssist.convertInstructionsToListOfStrings(testString).get(0));
        assertEquals(testList.get(1), ListAssist.convertInstructionsToListOfStrings(testString).get(1));
        assertEquals(testList.get(2), ListAssist.convertInstructionsToListOfStrings(testString).get(2));
    }

}
