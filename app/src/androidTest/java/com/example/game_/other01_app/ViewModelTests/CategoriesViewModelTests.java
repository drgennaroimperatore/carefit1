package com.example.game_.other01_app.ViewModelTests;

import android.app.Application;
import android.app.Instrumentation;
import android.icu.util.ULocale;

import com.example.game_.other01_app.Database.entities.Category;
import com.example.game_.other01_app.LiveDataTestUtil;
import com.example.game_.other01_app.Repositories.CategoriesRepository;
import com.example.game_.other01_app.ViewModels.CategoriesViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;


public class CategoriesViewModelTests {

    CategoriesViewModel categoriesViewModel;
    ArrayList<String> allCategories;
    ArrayList<String> allNotInterested;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp(){
         categoriesViewModel = new CategoriesViewModel(
                 (Application)
                InstrumentationRegistry.getInstrumentation()
                 .getTargetContext().getApplicationContext()
         );
         allCategories = new ArrayList<>();
         for (Category category : Category.populateData()){
             allCategories.add(category.getCategory());
         }
         allNotInterested = new ArrayList<>();
    }

    @Test
    public void assertListOfCategoriesNotEmpty(){
        assertNotNull(categoriesViewModel.getAllCategoriesNotLive());
        assertEquals(Category.populateData().length, categoriesViewModel.getAllCategoriesNotLive().size());
    }

    @Test
    public void assertLiveDataCategoriesNotEmpty() throws InterruptedException {
        assertEquals(Category.populateData().length, LiveDataTestUtil.getValue(categoriesViewModel.getAllCategories()).size());
    }

    @Test
    public void testSetPreferencesAllInterested() throws InterruptedException {
        categoriesViewModel.setPreferences(allCategories, allNotInterested);
        List<Category> fromDB = categoriesViewModel.getAllCategoriesNotLive();
        for(Category category : fromDB){
            assertTrue(allCategories.contains(category.getCategory()));
            assertTrue(category.isInterested());
        }
    }

    @Test
    public void testSetPreferencesNoneInterested(){
        categoriesViewModel.setPreferences(allNotInterested, allCategories);
        List<Category> fromDB = categoriesViewModel.getAllCategoriesNotLive();
        for(Category category : fromDB){
            assertTrue(allCategories.contains(category.getCategory()));
            assertFalse(category.isInterested());
        }
    }

}
