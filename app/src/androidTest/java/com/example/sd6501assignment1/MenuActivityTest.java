package com.example.sd6501assignment1;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.click;

public class MenuActivityTest {

    @Rule
    public ActivityScenarioRule<MenuActivity> activityScenarioRule = new ActivityScenarioRule<>(MenuActivity.class);

    private MenuActivity menuActivity;

    @Before
    public void setUp() {
        activityScenarioRule.getScenario().onActivity(activity -> {
            menuActivity = activity;
        });
    }

    @Test
    public void testBottomNavigationBar_HomeFragment() {

        onView(withId(R.id.home)).perform(click());
        onView(withId(R.id.AddTaskActivity)).check(matches(ViewMatchers.isDisplayed()));
    }
    @Test
    public void testBottomNavigationBar_ProfileFragment() {

        onView(withId(R.id.profile)).perform(click());
        onView(withId(R.id.ProfileFragment)).check(matches(ViewMatchers.isDisplayed()));
    }
    @Test
    public void testBottomNavigationBar_CalendarFragment() {

        onView(withId(R.id.calendar)).perform(click());
        onView(withId(R.id.CalendarFragment)).check(matches(ViewMatchers.isDisplayed()));
    }
    @Test
    public void testBottomNavigationBar_SettingFragment() {

        onView(withId(R.id.settings)).perform(click());
        onView(withId(R.id.SettingsFragment)).check(matches(ViewMatchers.isDisplayed()));
    }
}
