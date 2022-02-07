package com.example.kotlinpractice

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.toPackage
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ListActivityTest {

    @get:Rule
    val intentsTestRule = IntentsTestRule(ListActivity::class.java)

    @Test
    fun IntentTest() {

        launch(ListActivity::class.java)
        // Build the result to return when the activity is launched.
        val resultData = Intent()
        val number = "1234567891"
        resultData.putExtra("Integer", number)
        val result = Instrumentation.ActivityResult(Activity.RESULT_OK, resultData)

        // Set up result stubbing when an intent sent to "contacts" is seen.
        intending(toPackage("Integer")).respondWith(result)

        // User action that results in "contacts" activity being launched.
        // Launching activity expects phoneNumber to be returned and displayed.
        onView(withId(R.id.imageView)).perform(click())
        onView(withText("View"))
            .perform(click())

        // Assert that the data we set up above is shown.
        intended(toPackage("com.example.kotlinpractice"))
        //onView(withId(R.id.txtUserName)).check(matches(withText("MAD $number")))

    }

    @Test
    fun alertDialogTest() {

        launch(ListActivity::class.java)
        onView(withId(R.id.imageView)).perform(click())
        onView(withText("MADness")).check(matches(isDisplayed()));
        onView(withText("View"))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))
            .perform(click())

    }

}