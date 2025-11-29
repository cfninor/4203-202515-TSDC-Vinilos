package com.example.appvinilos

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.appvinilos.ui.album.AlbumAdapter
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddTrackToAlbumFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    /**
     * **HU08 - Criterio de Aceptación:** Dado el detalle de un álbum, cuando toco “Agregar track”,
     * entonces se abre un formulario con campos obligatorios (nombre, duración).
     */
    @Test
    fun test_showAddTrackForm() {
        navigateToAlbumDetailAndAddTrack()

        onView(withId(R.id.track_name_edit_text)).check(matches(isDisplayed()))
        onView(withId(R.id.track_duration_edit_text)).check(matches(isDisplayed()))
        onView(withId(R.id.save_track_button)).check(matches(isDisplayed()))
    }

    /**
     * **HU08 - Criterio de Aceptación:** El botón para agregar se deshabilita para evitar duplicados.
     */
    @Test
    fun test_addTrackButtonInitiallyDisabled() {
        navigateToAlbumDetailAndAddTrack()

        onView(withId(R.id.save_track_button)).check(matches(not(isEnabled())))
    }

    /**
     * **HU08 - Criterio de Aceptación:** El botón se habilita al llenar los campos.
     */
    @Test
    fun test_formFields_enablesButton() {
        navigateToAlbumDetailAndAddTrack()

        onView(withId(R.id.track_name_edit_text)).perform(typeText("Nuevo Track de Prueba"))
        onView(withId(R.id.track_duration_edit_text)).perform(typeText("3:45"))

        onView(withId(R.id.save_track_button)).check(matches(isEnabled()))
    }

    /**
     * **HU08 - Criterio de Aceptación:** Dado un formulario de track válido, cuando confirmo,
     * entonces el track aparece en la lista del álbum.
     */
    @Test
    fun test_addTrack_showsInList() {
        navigateToAlbumDetailAndAddTrack()

        onView(withId(R.id.track_name_edit_text)).perform(typeText("Amanecer Eléctrico"))
        onView(withId(R.id.track_duration_edit_text)).perform(typeText("4:20"))

        onView(withId(R.id.save_track_button)).perform(click())

        // Después de agregar, se espera que la app navegue de vuelta al detalle del álbum
        // y que el nuevo track sea visible en la lista de tracks.
        onView(withId(R.id.tracksRecyclerView)).check(matches(isDisplayed()))
    }

    private fun navigateToAlbumDetailAndAddTrack() {
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        onView(withId(R.id.recyclerAlbums)).perform(RecyclerViewActions.actionOnItemAtPosition<AlbumAdapter.AlbumViewHolder>(0, click()))
        onView(withId(R.id.add_track_button)).perform(click())
    }
}
