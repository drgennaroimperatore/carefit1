package com.example.game_.other01_app.EntityTests;

import com.example.game_.other01_app.Database.entities.Category;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class CategoryTests {

    private static final int testId = 1;
    private static final String testCategory = "testName";
    private static final boolean testInterested = true;

    private static final Category[] testData = {
            new Category(1, "Arms", false),
            new Category(2, "Legs", false),
            new Category(3, "Back", false),
            new Category(4, "Sitting", false),
            new Category(5, "Strength", false),
            new Category(6, "Flexibility", false),
            new Category(7, "Balance", false),
            new Category(8, "Aerobic", false),
            new Category(9, "Stretch", false)
    };

    private Category category;

    @Before
    public void setUp(){
        category = new Category(testId, testCategory,
                testInterested);
    }

    @Test
    public void testGetCategory(){
        assertEquals(testCategory, category.getCategory());
    }

    @Test
    public void testSetCategory() {
        category.setCategory("blue");
        assertEquals("blue", category.getCategory());
    }

    @Test
    public void testIsInterested(){
        assertEquals(testInterested, category.isInterested());
    }

    @Test
    public void testGetId(){
        assertEquals(testId, category.getCatid());
    }

    @Test
    public void testPopulateData() {
        assertTrue(Category.populateData().length == testData.length);
        for(int i = 0; i < testData.length; i++){
            assertTrue(Category.populateData()[i].getCategory()
            .equals(testData[i].getCategory()));
        }
    }
}
