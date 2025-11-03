
package com.example.appvinilos

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.appvinilos.ui.album.AlbumAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AlbumFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    /**
     * Espera un tiempo fijo para que la llamada a la API real finalice.
     * NOTA: Esta es una solución temporal. La forma robusta y profesional de manejar
     * esto en Espresso es usando IdlingResource.
     */
    private fun waitForNetwork() {
        try {
            Thread.sleep(2500) // Aumentamos un poco el tiempo por si la red es lenta
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    /**
     * **Objetivo:** Validar que, tras una llamada exitosa a la API, la lista de álbumes se muestra
     * en la pantalla y contiene al menos un elemento.
     *
     * **Alineación con Objetivos:**
     * - **HU01 - Criterio de Aceptación:** Cumple con "Dado que abro la sección 'Álbumes',
     *   cuando estoy en la aplicación, entonces veo una lista paginada con portada,
     *   título y artista". Esta prueba verifica que la lista no está vacía.
     * - **TNT (Plan de Pruebas):** Es una "Prueba de integración cliente-API" que valida que
     *   la petición HTTP al backend es exitosa y los datos se renderizan correctamente.
     */
    @Test
    fun test_isAlbumListVisible_and_hasItems() {
        waitForNetwork()
        onView(withId(R.id.recyclerAlbums)).check(matches(isDisplayed()))

        // Verifica que el RecyclerView tiene al menos un elemento. Fallará si la lista está vacía.
        onView(withId(R.id.recyclerAlbums)).check(matches(hasMinimumChildCount(1)))
    }

    /**
     * **Objetivo:** Validar el flujo de navegación principal desde la lista de álbumes hacia
     * la pantalla de detalle de un álbum específico.
     *
     * **Alineación con Objetivos:**
     * - **HU01 - Criterio de Aceptación:** Cumple directamente con "Dado que toco un álbum,
     *   cuando selecciono un ítem, entonces navego al detalle del álbum (HU02)".
     * - **TNT (Plan de Pruebas):** Se considera una "Prueba de usabilidad y flujo de interfaz",
     *   ya que simula la interacción E2E (End-to-End) del usuario al navegar entre
     *   dos pantallas principales de la aplicación.
     */
    @Test
    fun test_navigateToAlbumDetail_onItemClick() {
        waitForNetwork()

        // Hace clic en el primer elemento de la lista de álbumes.
        onView(withId(R.id.recyclerAlbums)).perform(
            RecyclerViewActions.actionOnItemAtPosition<AlbumAdapter.AlbumViewHolder>(0, click())
        )

        // Verifica que se ha navegado a la pantalla de detalle.
        // Lo comprobamos buscando una vista que solo existe en la pantalla de detalle.
        onView(withId(R.id.albumNameDetail)).check(matches(isDisplayed()))
    }

    /**
     * **Objetivo:** Validar que el usuario puede hacer scroll verticalmente en la lista de álbumes,
     * lo cual es un indicador de que la lista es funcional y maneja más elementos de los que
     * caben en la pantalla.
     *
     * **Alineación con Objetivos:**
     * - **HU01 - Criterio de Aceptación:** Apoya el criterio "Dado que la respuesta de la API
     *   es grande, cuando hago scroll, entonces la lista debe paginarse sin bloqueos".
     *   Esta prueba no valida la paginación en sí, pero sí el prerrequisito de que la
     *   lista permite hacer scroll.
     * - **TNT (Plan de Pruebas):** Es una "Prueba de usabilidad y flujo de interfaz" que
     *   verifica una interacción básica y esencial del usuario con un componente clave.
     */
    @Test
    fun test_scrollAlbumList() {
        waitForNetwork()

        // Hace scroll en la lista hasta la posición 10.
        // Si la lista tiene menos de 10 elementos, esta prueba fallará, indicando
        // que el conjunto de datos de prueba no es suficientemente grande.
        onView(withId(R.id.recyclerAlbums)).perform(
            RecyclerViewActions.scrollToPosition<AlbumAdapter.AlbumViewHolder>(10)
        )

        // Verifica que el RecyclerView sigue siendo visible después del scroll.
        onView(withId(R.id.recyclerAlbums)).check(matches(isDisplayed()))
    }

    /**
     * **Objetivo:** Validar que la funcionalidad de búsqueda en la pantalla de álbumes filtra
     * correctamente la lista según el texto introducido por el usuario.
     *
     * **Alineación con Objetivos:**
     * - **HU01 - Funcionalidad Implícita:** Aunque no está en los criterios de aceptación explícitos,
     *   el diseño de UI incluye una barra de búsqueda, por lo que probarla es clave para
     *   garantizar la calidad de la experiencia de usuario.
     * - **TNT (Plan de Pruebas):** Es una "Prueba de usabilidad y flujo de interfaz" que valida
     *   una interacción fundamental del usuario para encontrar contenido específico.
     */
    @Test
    fun test_searchFunctionality_filtersAlbumList() {
        waitForNetwork()

        // Escribe "Buscando" en la barra de búsqueda y cierra el teclado.
        onView(withId(R.id.searchEditText)).perform(typeText("Buscando"), closeSoftKeyboard())

        // Pequeña espera para que el filtro se aplique en la UI.
        try { Thread.sleep(1000) } catch (e: InterruptedException) { e.printStackTrace() }

        // Verifica que un álbum que contiene "Buscando América" (texto en el item del RecyclerView)
        // es visible después de filtrar. Esto asume que dicho álbum existe en los datos de la API.
        onView(withText("Buscando América")).check(matches(isDisplayed()))
    }
}
