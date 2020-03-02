package com.example.game_.other01_app.EntityTests;

import com.example.game_.other01_app.Database.entities.Exercise;

import net.bytebuddy.build.ToStringPlugin;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ExerciseTests {
    private static final int testId = 1;
    private static final String testName = "testName";
    private static final String testDesc = "testDesc";
    private static final String testImage = "testImage";
    private static final String testIntensity = "testEntity";
    private static final String testCategories = "test,category";

    private Exercise testExercise;

    @Before
    public void setUp(){
        testExercise = new Exercise(testId, testName,
               testDesc, testImage, testIntensity,
                testCategories);
    }

    @Test
    public void testGetId(){
        assertEquals(testId, testExercise.getId());
    }

    @Test
    public void testGetName(){
        assertEquals(testName, testExercise.getName());
    }

    @Test
    public void testGetDesc(){
        assertEquals(testDesc, testExercise.getDesc());
    }

    @Test
    public void testGetIntensity(){
        assertEquals(testIntensity, testExercise.getIntensity());
    }

    @Test
    public void testGetImage(){
        assertEquals(testImage, testExercise.getImage());
    }

    @Test
    public void testGetCategories(){
        assertEquals(testCategories, testExercise.getCategories());
    }
}
