package com.example.appvinilos

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CreateAlbumFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    /**
     * **HU07 - Criterio de Aceptación:** Valida que al navegar a la pantalla de creación
     * de álbum, el formulario se muestra correctamente.
     */
    @Test
    fun test_showCreateAlbumForm() {
        // Navega a la pantalla de álbumes y hace clic en el botón de agregar
        onView(withId(R.id.navigation_albums)).perform(click())
        onView(withId(R.id.addButton)).perform(click())

        onView(withId(R.id.name_edit_text)).check(matches(isDisplayed()))
        onView(withId(R.id.cover_edit_text)).check(matches(isDisplayed()))
        onView(withId(R.id.release_date_edit_text)).check(matches(isDisplayed()))
        onView(withId(R.id.description_edit_text)).check(matches(isDisplayed()))
        onView(withId(R.id.genre_auto_complete)).check(matches(isDisplayed()))
        onView(withId(R.id.record_label_auto_complete)).check(matches(isDisplayed()))
        onView(withId(R.id.save_button)).check(matches(isDisplayed()))
    }

    /**
     * **HU07 - Criterio de Aceptación:** Dado un formulario con campos obligatorios (título, fecha, género),
     * cuando los diligencio correctamente, entonces el botón “Crear” se habilita.
     */
    @Test
    fun test_createAlbumButtonInitiallyDisabled() {
        onView(withId(R.id.navigation_albums)).perform(click())
        onView(withId(R.id.addButton)).perform(click())

        onView(withId(R.id.save_button)).check(matches(not(isEnabled())))
    }

    /**
     * **HU07 - Criterio de Aceptación:** Verifica que el botón de crear se habilita después de
     * llenar los campos obligatorios.
     */
    @Test
    fun test_formFields_enablesButton() {
        onView(withId(R.id.navigation_albums)).perform(click())
        onView(withId(R.id.addButton)).perform(click())

        // Llena los campos obligatorios
        onView(withId(R.id.name_edit_text)).perform(replaceText("Nuevo Álbum de Prueba"))
        onView(withId(R.id.release_date_edit_text)).perform(replaceText("2024-05-23"))
        onView(withId(R.id.description_edit_text)).perform(replaceText("Descripción de prueba"))
        onView(withId(R.id.genre_auto_complete)).perform(replaceText("Rock"))

        // Verifica que el botón está habilitado
        onView(withId(R.id.save_button)).check(matches(isEnabled()))
    }

    /**
     * **HU07 - Criterio de Aceptación:** Dado que envío un formulario válido, cuando la API responde éxito,
     * entonces se muestra confirmación y el nuevo álbum aparece en el catálogo.
     */
    @Test
    fun test_createAlbum_showsSuccess() {
        onView(withId(R.id.navigation_albums)).perform(click())
        onView(withId(R.id.addButton)).perform(click())

        // Llena el formulario
        onView(withId(R.id.name_edit_text)).perform(replaceText("Viaje al Corazón del Rock"))
        onView(withId(R.id.cover_edit_text)).perform(replaceText("http://example.com/cover.jpg"))
        onView(withId(R.id.release_date_edit_text)).perform(replaceText("2024-01-15"))
        onView(withId(R.id.description_edit_text)).perform(replaceText("Un álbum que explora las raíces del rock."))
        onView(withId(R.id.genre_auto_complete)).perform(replaceText("Rock"))

        onView(withId(R.id.save_button)).perform(click())

        // Espera una confirmación o la navegación de regreso a la lista de álbumes.
        // Esto también depende de la implementación final.
        // Por ejemplo, si se navega de vuelta y se muestra la lista:
        onView(withId(R.id.recyclerAlbums)).check(matches(isDisplayed()))
    }
}
