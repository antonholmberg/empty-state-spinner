package org.grunkspin.testapp

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.pressBack
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EmptyStateSpinnerTest {

    @JvmField
    @Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testEmptyState() {
        onView(withSpinnerText("Empty")).check(matches(isDisplayed()))
    }

    @Test
    fun testSelectItem() {
        onView(withId(R.id.spinner)).perform(click())
        onView(withText("Option one")).perform(click())
        onView(allOf(withSpinnerText("Option one"), withId(R.id.spinner))).check(matches(isDisplayed()))
    }

    @Test
    fun testNoEmptyOption() {
        onView(withId(R.id.spinner)).perform(click())
        onView(withText("Empty")).check(doesNotExist())
    }

    @Test
    fun testKeepEmptyState() {
        onView(withId(R.id.spinner)).perform(click())
        onView(withText("Option one")).perform(pressBack())
        onView(withText("Empty")).check(matches(isDisplayed()))
    }
}
