package com.example.sd6501assignment1;

import static android.icu.number.NumberRangeFormatter.with;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.rule.ActivityTestRule;

import com.google.firebase.database.FirebaseDatabase;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class LoginActivityTest {

    @Rule
    public androidx.test.ext.junit.rules.ActivityScenarioRule<LoginActivity> ActivityScenarioRule =
            new ActivityScenarioRule<>(LoginActivity.class);

    private LoginActivity loginActivity;
    String  testName, testPassword;
    FirebaseDatabase firebaseDatabase;

    @Before
    public void setUp() throws Exception{
        testName ="ann";
        testPassword = "Selina";

    }

    @After
    public void tearDown() throws Exception {
        loginActivity = null;
    }
//    @Test
//    public void testLoginButton_SuccessfulLogin() {
//        // Enter valid credentials in the username and password fields
//        onView(withId(R.id.edtUserName)).perform(typeText("Selina"), closeSoftKeyboard());
//        onView(withId(R.id.edtPassword)).perform(typeText("Selina"), closeSoftKeyboard());
//        onView(withId(R.id.btnLogin)).perform(click());
//        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void testLoginButton_EmptyUsername() {
//        // Leave the username field empty
//        onView(withId(R.id.edtPassword)).perform(typeText("Selina"), closeSoftKeyboard());
//        onView(withId(R.id.btnLogin)).perform(click());
//        onView(withId(R.id.edtUserName)).check(matches(hasErrorText("Username is required")));
//    }
//
//    @Test
//    public void testLoginButton_EmptyPassword() {
//        // Enter a valid username but leave the password field empty
//        onView(withId(R.id.edtUserName)).perform(typeText("Selina"), closeSoftKeyboard());
//        onView(withId(R.id.btnLogin)).perform(click());
//        onView(withId(R.id.edtPassword)).check(matches(hasErrorText("Password is required")));
//    }
//
//    @Test
//    public void testLoginButton_InvalidCredentials() {
//        // Enter invalid credentials in the username and password fields
//        onView(withId(R.id.edtUserName)).perform(typeText("Selina"), closeSoftKeyboard());
//        onView(withId(R.id.edtPassword)).perform(typeText("Iddfff"), closeSoftKeyboard());
//        onView(withId(R.id.btnLogin)).perform(click());
//        onView(withId(R.id.edtPassword)).check(matches(hasErrorText("Invalid Credentials")));
//    }

    @Test
    public void testLoginButton_UserDoesNotExist() {
        // Enter a username that does not exist in the database
        onView(withId(R.id.edtUserName)).perform(typeText(testName), closeSoftKeyboard());
        onView(withId(R.id.edtPassword)).perform(typeText(testPassword), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withId(R.id.edtUserName)).check(matches(hasErrorText("User does not exist")));
    }

}


