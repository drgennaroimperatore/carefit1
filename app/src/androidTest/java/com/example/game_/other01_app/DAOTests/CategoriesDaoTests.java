package com.example.game_.other01_app.DAOTests;

import android.content.Context;

import com.example.game_.other01_app.Database.AppDatabase;
import com.example.game_.other01_app.Database.daos.CategoriesDao;
import com.example.game_.other01_app.Database.entities.Category;
import com.example.game_.other01_app.LiveDataTestUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class CategoriesDaoTests {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    private AppDatabase db;
    private CategoriesDao categoriesDao;
    private final String CAT_NAME = "Sitting";

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        categoriesDao = db.categoriesDao();
        categoriesDao.insertAll(Category.populateData());
    }

    @After
    public void tearDown(){
        db.close();
    }

    @Test
    public void getAllReturnsCorrectCategories() throws InterruptedException {
        List<Category> categories = LiveDataTestUtil.getValue(categoriesDao.getAll());
        assertNotNull(categories);
        assertEquals(Category.populateData().length, categories.size());
        assertEquals(Category.populateData()[0].getCategory(), categories.get(0).getCategory());
    }

    @Test
    public void assertCategoryPreferenceChanged() throws InterruptedException {
        categoriesDao.updateInterest(CAT_NAME, true);
        List<Category> categories = LiveDataTestUtil.getValue(categoriesDao.getAll());
        Category category = getByName(categories);
        assertNotNull(categories);
        assertNotNull(category);
        assertTrue(category.isInterested());
        assertFalse(!category.isInterested());
    }

    @Test
    public void assertDeleteAllWorks() throws InterruptedException {
        categoriesDao.deleteAll();
        List<Category> categories = LiveDataTestUtil.getValue(categoriesDao.getAll());
        assertTrue(categories.isEmpty());
    }

    @Test
    public void assetInsertCategoriesWorks() throws InterruptedException {
        Category[] categoriesToAdd = new Category[]{
                new Category(99, "TEST", false)
        };
        categoriesDao.insertAll(categoriesToAdd);
        List<Category> categories = LiveDataTestUtil.getValue(categoriesDao.getAll());
        assertEquals(Category.populateData().length + 1, categories.size());
    }

    private Category getByName(List<Category> categories) {
        for(Category category : categories){
            if (category.getCategory().matches(CAT_NAME)) {
                return category;
            }
        }
        return null;
    }

}
