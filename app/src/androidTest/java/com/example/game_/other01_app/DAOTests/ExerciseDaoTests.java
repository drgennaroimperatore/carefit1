package com.example.game_.other01_app.DAOTests;

import android.content.Context;

import com.example.game_.other01_app.Database.AppDatabase;
import com.example.game_.other01_app.Database.daos.ExerciseDao;
import com.example.game_.other01_app.Database.entities.Exercise;
import com.example.game_.other01_app.LiveDataTestUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ExerciseDaoTests {
    @Mock
    private Observer<List<Exercise>> observer;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    private AppDatabase db;
    private ExerciseDao exerciseDao;
    private final Exercise TEST_EXERCISE = new Exercise(
            99, "Chest Stretch", "This stretch is good for posture.\n" +
            "A. Sit upright and away from the back of the chair.\n" +
            "Pull your shoulders back and down. Extend arms\n" +
            "out to the side.\n" +
            "B. Gently push your chest forwards and up until\n" +
            "you feel a stretch across your chest.\n" +
            "Hold for five to 10 seconds and repeat five times.", "cheststretch",
            "low", "sitting,strength"
    );
    private final String TEST_CATEGORY = "sitting";

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        exerciseDao = db.exerciseDao();
        exerciseDao.insertAll(Exercise.populateData());
    }

    @After
    public void tearDown(){
        db.close();
    }

    @Test
    public void testGetAllReturnsCorrectCategories() throws InterruptedException {
        List<Exercise> exercises = LiveDataTestUtil.getValue(exerciseDao.getAllExercises());
        assertNotNull(exercises);
        Exercise[] expectedExercises = Exercise.populateData();
        assertEquals(expectedExercises.length, exercises.size());
        assertEquals(expectedExercises[0].getName(), exercises.get(0).getName());
    }

    @Test
    public void testFindByNameWorks() {
        Exercise byNameExercise = exerciseDao.findByName(TEST_EXERCISE.getName());
        assertNotNull(byNameExercise);
        assertEquals(TEST_EXERCISE.getImage(), byNameExercise.getImage());
    }

    @Test
    public void testInsertAllWorks() throws InterruptedException {
        Exercise[] exercisesToAdd = new Exercise[]{
                TEST_EXERCISE
        };
        exerciseDao.insertAll(exercisesToAdd);
        List<Exercise> allExercises = LiveDataTestUtil.getValue(exerciseDao.getAllExercises());
        assertEquals(Exercise.populateData().length + 1, allExercises.size());
    }

    @Test
    public void testDeleteAllWorks() throws InterruptedException {
        exerciseDao.deleteAll();
        List<Exercise> allExercises = LiveDataTestUtil.getValue(exerciseDao.getAllExercises());
        assertTrue(allExercises.isEmpty());
    }

}
