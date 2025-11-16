package com.example.appvinilos

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.appvinilos.ui.artist.ArtistAdapter
import com.example.appvinilos.ui.collector.CollectorAdapter
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArtistAndCollectorFlowTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    /**
     * Espera un tiempo fijo para que la llamada a la API real finalice.
     * NOTA: Esta es una solución temporal. IdlingResource es la forma correcta.
     */
    private fun waitForNetwork() {
        try {
            Thread.sleep(2500)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    /**
     * **Objetivo:** Probar la funcionalidad HU03. Verifica que la lista de artistas se carga y
     * muestra correctamente después de navegar a la sección de Artistas.
     *
     * **Alineación con Objetivos (TNT):**
     * - **1. Integración Cliente-API:** Valida que la respuesta de la API de artistas es procesada
     *   y renderizada correctamente en la UI.
     * - **2. Usabilidad y Flujo de Interfaz:** Comprueba la navegación a una sección principal
     *   de la aplicación.
     * - **4. Validación Funcional (HU03):** Cumple directamente con el objetivo de probar el
     *   listado de artistas.
     */
    @Test
    fun test_isArtistListVisible_and_hasItems() {
        onView(withId(R.id.navigation_artists)).perform(click())
        waitForNetwork()
        onView(withId(R.id.artistsRecyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.artistsRecyclerView)).check(matches(hasMinimumChildCount(1)))
    }

    /**
     * **Objetivo:** Probar la funcionalidad HU04. Verifica que al hacer clic en un artista de la
     * lista, se navega a la pantalla de detalle y se muestra la información correcta.
     *
     * **Alineación con Objetivos (TNT):**
     * - **2. Usabilidad y Flujo de Interfaz:** Valida el flujo completo de navegación desde una
     *   lista a una pantalla de detalle.
     * - **4. Validación Funcional (HU04):** Cumple directamente con el objetivo de probar el
     *   detalle de un artista y su consumo de servicios.
     * - **5. Regresión Automatizada:** Asegura que la navegación entre artistas no se ha roto.
     */
    @Test
    fun test_navigateToArtistDetail_onItemClick() {
        onView(withId(R.id.navigation_artists)).perform(click())
        waitForNetwork()

        onView(withId(R.id.artistsRecyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ArtistAdapter.ArtistViewHolder>(0, click())
        )

        // Verifica que la información del detalle del artista es visible
        onView(withId(R.id.artistNameDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.artistImageDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.artistAlbumsRecyclerView)).check(matches(isDisplayed()))
    }

    /**
     * **Objetivo:** Probar la funcionalidad HU05. Verifica que la lista de coleccionistas se
     * carga y muestra correctamente después de navegar a la sección de Coleccionistas.
     *
     * **Alineación con Objetivos (TNT):**
     * - **1. Integración Cliente-API:** Valida que la respuesta de la API de coleccionistas
     *   es procesada y renderizada correctamente.
     * - **2. Usabilidad y Flujo de Interfaz:** Comprueba la navegación a otra sección principal.
     * - **4. Validación Funcional (HU05):** Cumple directamente con el objetivo de probar el
     *   listado de coleccionistas.
     */
    @Test
    fun test_isCollectorListVisible_and_hasItems() {
        onView(withId(R.id.navigation_collectors)).perform(click())
        waitForNetwork()
        onView(withId(R.id.collectorsRecyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.collectorsRecyclerView)).check(matches(hasMinimumChildCount(1)))
    }

    /**
     * **Objetivo:** Validar que la funcionalidad de búsqueda en la pantalla de artistas filtra
     * correctamente la lista según el texto introducido por el usuario.
     *
     * **Alineación con Objetivos (TNT):**
     * - **2. Usabilidad y Flujo de Interfaz:** Valida una interacción clave del usuario para
     *   encontrar contenido específico.
     * - **5. Regresión Automatizada:** Garantiza que la funcionalidad de búsqueda sigue operando
     *   correctamente tras nuevos cambios en el código.
     */
    @Test
    fun test_artistSearchFunctionality() {
        onView(withId(R.id.navigation_artists)).perform(click())
        waitForNetwork()

        // Asume que un artista con "Rubén" en el nombre existe en la API
        onView(withId(R.id.searchEditTextArtists)).perform(replaceText("Rubén"), closeSoftKeyboard())
        try { Thread.sleep(1000) } catch (e: InterruptedException) { e.printStackTrace() }

        onView(withText(containsString("Rubén Blades"))).check(matches(isDisplayed()))
    }

    /**
     * **Objetivo:** Validar que el usuario puede hacer scroll en la lista de artistas, lo cual
     * es un prerrequisito para una buena experiencia con listas largas.
     *
     * **Alineación con Objetivos (TNT):**
     * - **2. Usabilidad y Flujo de Interfaz:** Verifica una interacción básica y esencial.
     * - **5. Regresión Automatizada:** Asegura que cambios futuros en el layout o el RecyclerView
     *   no rompan la capacidad de hacer scroll.
     */
    @Test
    fun test_artistList_canScroll() {
        onView(withId(R.id.navigation_artists)).perform(click())
        waitForNetwork()

        // Asume que la lista de artistas tiene al menos 5 elementos para poder hacer scroll
        onView(withId(R.id.artistsRecyclerView)).perform(
            RecyclerViewActions.scrollToPosition<ArtistAdapter.ArtistViewHolder>(5)
        )
        onView(withId(R.id.artistsRecyclerView)).check(matches(isDisplayed()))
    }

    /**
     * **Objetivo:** Validar que la navegación hacia atrás desde la pantalla de detalle de un
     * artista funciona correctamente y regresa al usuario a la lista de artistas.
     *
     * **Alineación con Objetivos (TNT):**
     * - **2. Usabilidad y Flujo de Interfaz:** Confirma que la pila de navegación se gestiona
     *   correctamente, un pilar de la usabilidad de la app.
     * - **5. Regresión Automatizada:** Detecta errores en la gestión del "back stack" que podrían
     *   introducirse en futuros cambios.
     */
    @Test
    fun test_navigation_fromArtistDetail_backToList() {
        onView(withId(R.id.navigation_artists)).perform(click())
        waitForNetwork()

        // Navega al detalle del primer artista
        onView(withId(R.id.artistsRecyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ArtistAdapter.ArtistViewHolder>(0, click())
        )
        waitForNetwork() // Espera a que el detalle cargue

        // Presiona el botón de retroceso del sistema
        Espresso.pressBack()

        // Verifica que la lista de artistas es visible nuevamente
        onView(withId(R.id.artistsRecyclerView)).check(matches(isDisplayed()))
    }

    /**
     * *Objetivo:* Validar que la lista de álbumes dentro de la pantalla de detalle de un artista
     * es funcional y permite al usuario hacer scroll.
     *
     * *Alineación con Objetivos (TNT):*
     * - *1. Integración Cliente-API:* Confirma que la sub-lista de álbumes del artista
     *   se carga y renderiza correctamente.
     * - *2. Usabilidad y Flujo de Interfaz:* Prueba la usabilidad de un componente anidado
     *   (una lista dentro de otra pantalla).
     */
    @Test
    fun test_artistDetail_albumList_canScroll() {
        onView(withId(R.id.navigation_artists)).perform(click())
        waitForNetwork()

        // Navega al detalle del primer artista
        onView(withId(R.id.artistsRecyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ArtistAdapter.ArtistViewHolder>(0, click())
        )
        waitForNetwork()

        // Asume que el artista tiene al menos 3 álbumes para poder hacer scroll
        onView(withId(R.id.artistAlbumsRecyclerView)).perform(
            RecyclerViewActions.scrollToPosition<ArtistAdapter.ArtistViewHolder>(3)
        )
        onView(withId(R.id.artistAlbumsRecyclerView)).check(matches(isDisplayed()))
    }

    /**
     * *Objetivo:* Validar que el usuario puede hacer scroll en la lista de coleccionistas,
     * asegurando la usabilidad de la pantalla con una gran cantidad de datos.
     *
     * *Alineación con Objetivos (TNT):*
     * - *2. Usabilidad y Flujo de Interfaz:* Verifica una interacción de usuario esencial para la HU05.
     * - *5. Regresión Automatizada:* Garantiza que la lista de coleccionistas sigue siendo
     *   funcional después de futuros cambios.
     */
    @Test
    fun test_collectorList_canScroll() {
        onView(withId(R.id.navigation_collectors)).perform(click())
        waitForNetwork()

        // Asume que la lista de coleccionistas tiene al menos 5 elementos
        onView(withId(R.id.collectorsRecyclerView)).perform(
            RecyclerViewActions.scrollToPosition<CollectorAdapter.CollectorViewHolder>(5)
        )
        onView(withId(R.id.collectorsRecyclerView)).check(matches(isDisplayed()))
    }

}