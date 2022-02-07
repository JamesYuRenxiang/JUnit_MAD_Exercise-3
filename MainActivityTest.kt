package com.example.kotlinpractice

import android.view.View
import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import junit.framework.Assert.assertEquals
import org.hamcrest.CoreMatchers.containsString


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    fun displayName() {

        launch(MainActivity::class.java)

        onView(withId(R.id.txtUserName)).check { view: View, noViewFoundException: NoMatchingViewException? ->
            val name = (view as TextView).text.toString()
            assertThat(name, containsString("MAD"))
        }

    }

    @Test
    fun displayDescription() {

        launch(MainActivity::class.java)

        onView(withId(R.id.txtUserDescription)).check { view: View, noViewFoundException: NoMatchingViewException? ->
            val description = (view as TextView).text.toString()
            assertEquals(description, "Lorem ipsum dolor sit amet, consectetur " +
                    "adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua")
        }

    }

    @Test
    fun followTest() {

        launch(MainActivity::class.java)
        onView(withId(R.id.btnFollow)).perform(click())
        //onView(withText("Followed"))
            //.inRoot(ToastMatcher())
            //.check(matches(isDisplayed()))
        onView(withId(R.id.btnFollow)).check(matches(withText("Unfollow")))
        onView(withId(R.id.btnFollow)).perform(click())
        onView(withId(R.id.btnFollow)).check(matches(withText("Follow")))
        onView(withId(R.id.btnFollow)).perform(click())
        onView(withId(R.id.btnFollow)).check(matches(withText("Unfollow")))


    }

}