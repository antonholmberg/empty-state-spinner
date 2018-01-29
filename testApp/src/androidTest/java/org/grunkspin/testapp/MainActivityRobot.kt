package org.grunkspin.testapp

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.pressBack
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.allOf

fun mainActivityRobot(actions: DropDownClosedRobot.() -> Unit) {
    DropDownClosedRobot().actions()
}

class DropDownClosedRobot {

    fun openDropDown(actions: DropDownOpenRobot.() -> Unit) {
        onView(withId(R.id.spinner)).perform(click())
        DropDownOpenRobot().actions()
    }

    fun verifyOptionWithTextIsSelected(text: String) {
        onView(allOf(withSpinnerText(text), withId(R.id.spinner))).check(matches(isDisplayed()))
    }

    fun verifyEmptyStateSelected() {
        verifyOptionWithTextIsSelected("Empty")
    }
}

class DropDownOpenRobot {
    fun selectOptionWithText(text: String) {
        onView(withText(text)).perform(click())
    }

    fun closeDropDown() {
        onView(withText("Option one")).perform(pressBack())
    }

    fun verifyDoesNotContainEmptyState() {
        verifyDoesNotContainItemWithText("Empty")
    }

    fun verifyDoesNotContainItemWithText(text: String) {
        onView(withText(text)).check(doesNotExist())
    }
}
