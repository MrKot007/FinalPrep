package com.example.finalprep

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @JvmField
    @Rule
    val scenario = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkButtonText() {
        val button = onView(withId(R.id.nextText))
        button.check(matches(withText("Next")))
    }
    @Test
    fun checkButtonChange() {
        val button = onView(withId(R.id.next))
        val buttonToAppear = onView(withId(R.id.signUp))
        button.perform(click())
        button.perform(click())
        buttonToAppear.check(matches(isDisplayed()))
    }
    @Test
    fun checkTransferToRegistration() {
        val button = onView(withId(R.id.signUp))
        val reg = onView(withId(R.id.reg))
        val next = onView(withId(R.id.next))
        next.perform(click())
        next.perform(click())
        button.perform(click())
        reg.check(matches(isDisplayed()))
    }
    @Test
    fun checkTransferToEntrance() {
        val button = onView(withId(R.id.goToSignIn))
        val enter = onView(withId(R.id.enter))
        val next = onView(withId(R.id.next))
        next.perform(click())
        next.perform(click())
        button.perform(click())
        enter.check(matches(isDisplayed()))
    }
}