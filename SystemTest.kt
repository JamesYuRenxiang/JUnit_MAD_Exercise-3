package com.example.kotlinpractice

import android.view.View
import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SystemTest {

    @Test
    fun SystemTest() {

        ActivityScenario.launch(ListActivity::class.java)

        Espresso.onView(ViewMatchers.withId(R.id.imageView)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withText("MADness"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withText("View"))
            .inRoot(RootMatchers.isDialog())
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .perform(ViewActions.click())

        val user = User("MAD 1","Lorem ipsum dolor sit amet, consectetur " +
                "adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua", 0, false)

        Espresso.onView(ViewMatchers.withId(R.id.txtUserName))
            .check { view: View, noViewFoundException: NoMatchingViewException? ->
            val name = (view as TextView).text.toString()
                Assert.assertNotEquals(user.Name, name)

        }

        Espresso.onView(ViewMatchers.withId(R.id.txtUserDescription))
            .check { view: View, noViewFoundException: NoMatchingViewException? ->
            val description = (view as TextView).text.toString()
                Assert.assertEquals(user.Description, description)
        }

        Espresso.onView(ViewMatchers.withId(R.id.btnFollow)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.btnFollow))
            .check(ViewAssertions.matches(ViewMatchers.withText("Unfollow")))

        Espresso.onView(ViewMatchers.withId(R.id.btnFollow)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.btnFollow))
            .check(ViewAssertions.matches(ViewMatchers.withText("Follow")))

        Espresso.onView(ViewMatchers.withId(R.id.btnFollow)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.btnFollow))
            .check(ViewAssertions.matches(ViewMatchers.withText("Unfollow")))

    }

}