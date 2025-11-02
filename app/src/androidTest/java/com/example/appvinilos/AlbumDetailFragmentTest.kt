
package com.example.appvinilos

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.appvinilos.ui.album.AlbumAdapter
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AlbumDetailFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_isAlbumDetailVisible() {
        // Espera a que el listado se muestre
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        onView(withId(R.id.recyclerAlbums)).perform(RecyclerViewActions.actionOnItemAtPosition<AlbumAdapter.AlbumViewHolder>(0, click()))

        // Revisa si la vista de detalle es correcta
        onView(withId(R.id.albumNameDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.performerNameDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.albumInfoDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.albumCoverDetail)).check(matches(isDisplayed()))
    }

    @Test
    fun test_tracksAreVisible() {
        // Espera a que el listado se muestre
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        onView(withId(R.id.recyclerAlbums)).perform(RecyclerViewActions.actionOnItemAtPosition<AlbumAdapter.AlbumViewHolder>(0, click()))

        // Revisa si los tacks el título y el recycler view son visibles
        onView(withId(R.id.tracksTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tracksRecyclerView)).check(matches(isDisplayed()))
    }
}
