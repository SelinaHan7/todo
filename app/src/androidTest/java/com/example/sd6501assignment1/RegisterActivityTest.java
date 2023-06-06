package com.example.sd6501assignment1;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertNotNull;

public class RegisterActivityTest {

    @Rule
    public ActivityScenarioRule<RegisterActivity> activityScenarioRule = new ActivityScenarioRule<>(RegisterActivity.class);

    private RegisterActivity registerActivity;

    @Before
    public void setUp() {
        activityScenarioRule.getScenario().onActivity(activity -> {
            registerActivity = activity;
        });
    }

    @After
    public void tearDown() {

        registerActivity=null;
    }

    @Test
    public void registerWithValidCredentials() {
        assertNotNull(registerActivity);

        onView(withId(R.id.inputUserName)).perform(typeText("JohnDoe"), closeSoftKeyboard());
        onView(withId(R.id.inputEmail)).perform(typeText("johndoe@example.com"), closeSoftKeyboard());
        onView(withId(R.id.inputPassword)).perform(typeText("password123"), closeSoftKeyboard());

        onView(withId(R.id.btnSignUp)).perform(click());

        // Perform assertions or verifications as needed
        // For example, check if the dialog is displayed
        onView(withText("You have signup successfully!")).check(matches(isDisplayed()));
    }

    @Test
    public void registerWithInvalidCredentials() {
        assertNotNull(registerActivity);

        onView(withId(R.id.inputUserName)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.inputEmail)).perform(typeText("invalidemail"), closeSoftKeyboard());
        onView(withId(R.id.inputPassword)).perform(typeText("pass"), closeSoftKeyboard());

        onView(withId(R.id.btnSignUp)).perform(click());

        // Perform assertions or verifications as needed
        // For example, check if the appropriate error messages are displayed
        onView(withId(R.id.inputUserName)).check(matches(hasErrorText("Must enter 4-12 characters")));
        onView(withId(R.id.inputEmail)).check(matches(hasErrorText("Invalid email address")));
        onView(withId(R.id.inputPassword)).check(matches(hasErrorText("Password must be at least 6 characters long")));
    }
}
