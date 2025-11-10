package com.example.appvinilos

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppNavigationTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_bottomNavigation() {
        // Navigate to Artists and verify the unique title is displayed
        onView(withId(R.id.navigation_artists)).perform(click())
        onView(withId(R.id.artistsTitle)).check(matches(isDisplayed()))

        // Navigate to Collectors and verify the unique title is displayed
        onView(withId(R.id.navigation_collectors)).perform(click())
        onView(withId(R.id.collectors_title)).check(matches(isDisplayed()))

        // Navigate to Profile and verify the unique title is displayed
        onView(withId(R.id.navigation_profile)).perform(click())
        onView(withId(R.id.profile_title)).check(matches(isDisplayed()))

        // Navigate back to Albums and verify the search bar is displayed
        onView(withId(R.id.navigation_albums)).perform(click())
        onView(withId(R.id.searchEditText)).check(matches(isDisplayed()))
    }

    @Test
    fun test_albumSearch() {
        // Espera a que los datos se carguen
        Thread.sleep(2000)

        // Usa replaceText para evitar problemas con el teclado
        onView(withId(R.id.searchEditText)).perform(replaceText("Buscando América"), closeSoftKeyboard())

        // Verifica que el álbum y el año son visibles, usando `allOf` para ser específicos
        onView(allOf(withId(R.id.album_name), withText("Buscando América"))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.album_year), withText("(1984)"))).check(matches(isDisplayed()))
    }
}
