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

    /**
     * **Objetivo:** Validar que al seleccionar un álbum de la lista, la pantalla de detalle
     * se muestra correctamente con la información principal del álbum.
     *
     * **Alineación con Objetivos:**
     * - **HU01 - Criterio de Aceptación:** Cumple con "Dado que toco un álbum, cuando selecciono
     *   un ítem, entonces navego al detalle del álbum (HU02)".
     * - **HU02 - Criterio de Aceptación:** Valida "Dado un álbum válido, cuando abro su detalle,
     *   entonces veo portada, nombre, fecha de lanzamiento y género".
     * - **TNT (Plan de Pruebas):** Se clasifica como una "Prueba de integración cliente-API"
     *   y una "Prueba de usabilidad y flujo de interfaz", ya que verifica la navegación
     *   (flujo E2E) y la correcta renderización de los datos obtenidos de la API real.
     */
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

    /**
     * **Objetivo:** Validar que si un álbum tiene tracks, la sección correspondiente para
     * mostrarlos es visible en la pantalla de detalle.
     *
     * **Alineación con Objetivos:**
     * - **HU02 - Criterio de Aceptación:** Valida parcialmente "Dado un álbum con tracks,
     *   cuando estoy en el detalle, entonces veo listado de tracks con nombre y duración".
     *   Esta prueba confirma que los componentes de la UI (el título y la lista) están visibles.
     * - **TNT (Plan de Pruebas):** Al igual que la prueba anterior, es una "Prueba de integración
     *   cliente-API", ya que depende de que el backend real devuelva un álbum que contenga
     *   tracks para que la prueba sea exitosa y los elementos se muestren.
     */
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
