package org.grunkspin.testapp

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
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
        mainActivityRobot {
            verifyEmptyStateSelected()
        }
    }

    @Test
    fun testSelectItem() {
        mainActivityRobot {
            openDropDown {
                selectOptionWithText("Option one")
            }

            verifyOptionWithTextIsSelected("Option one")
        }
    }

    @Test
    fun testNoEmptyOption() {
        mainActivityRobot {
            openDropDown {
                verifyDoesNotContainEmptyState()
            }
        }
    }

    @Test
    fun testKeepEmptyState() {
        mainActivityRobot {
            openDropDown {
                closeDropDown()
            }

            verifyEmptyStateSelected()
        }
    }
}
