package com.example.appvinilos

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.appvinilos.ui.collector.CollectorAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CollectorDetailFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    /**
     * **HU06 - Criterio de Aceptación:** Dado un coleccionista válido, cuando abro su detalle,
     * entonces veo nombre/alias y, si aplica, su biografía o datos adicionales.
     */
    @Test
    fun test_collectorDetail_isDisplayed() {
        // Navega a la sección de coleccionistas
        onView(withId(R.id.navigation_collectors)).perform(click())

        // Espera a que la lista se cargue
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        // Hace clic en el primer elemento de la lista de coleccionistas
        onView(withId(R.id.collectorsRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<CollectorAdapter.CollectorViewHolder>(0, click()))

        // Verifica que la información del coleccionista se muestra en la pantalla de detalle
        onView(withId(R.id.collectorNameDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.collectorTelephone)).check(matches(isDisplayed()))
        onView(withId(R.id.collectorEmail)).check(matches(isDisplayed()))
        onView(withId(R.id.favoriteArtistsRecyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.collectorAlbumsRecyclerView)).check(matches(isDisplayed()))
    }
}
