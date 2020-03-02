package com.example.game_.other01_app.AssistanceTests;

import com.example.game_.other01_app.AssistanceClasses.StringsAssist;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class StringsAssistTests {

    @Test
    public void testConcatInts(){
        assertEquals(111, StringsAssist.concatInts(1,1,1));
    }

    @Test
    public void testConcatZeroes(){
        assertEquals(0, StringsAssist.concatInts(0,0,0));
    }

}
