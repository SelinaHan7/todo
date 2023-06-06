package com.example.sd6501assignment1;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.widget.Button;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class IncorrectRegister {

    @Rule
    public ActivityScenarioRule<RegisterActivity> activityScenarioRule = new ActivityScenarioRule<>(RegisterActivity.class);

    RegisterActivity registerActivity;
    String inputUsername, inputEmail, inputPassword;
    Button btnSignup;


    @Before
    public void setUp() throws Exception {
        inputUsername = "";
        inputEmail="";
        inputPassword="";
    }

    @After
    public void tearDown() throws Exception {
        registerActivity=null;
    }

    @Test
    public void IncorrectUserName() {
        onView(withId(R.id.inputUserName)).perform(typeText(String.valueOf(inputUsername)));
        onView(withId(R.id.inputEmail)).perform(typeText(String.valueOf(inputEmail)));
        onView(withId(R.id.inputPassword)).perform(typeText(String.valueOf(inputPassword)));
        closeSoftKeyboard();
        onView(withId(R.id.btnSignUp)).perform(click());
        onView(withId(R.id.inputUserName)).check(matches(hasErrorText("Must enter 4-12 characters")));
    }

}
