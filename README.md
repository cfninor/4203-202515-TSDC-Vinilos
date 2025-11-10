# Ingeniería de Software para Aplicaciones Móviles
## Universidad de los Andes

### Integrantes del Equipo

| Nombre | Correo |
| ------ | ------ |
| Daniel Beltrán Penagos | d.beltran@uniandes.edu.co |
| Víctor Alfonso Camacho Agudelo | v.camacho@uniandes.edu.co |
| Carlos Felipe Niño Rodríguez | cf.ninor1@uniandes.edu.co |
| Juan Sebastián Rodríguez Gómez | j.rodriguezg@uniandes.edu.co |x

---

###  Rama Actual: develop

> Este README corresponde a la rama **`main`**  
> Esta es la rama principal de desarrollo e integración continua

---

# AppVinilos 

AppVinilos es una aplicación Android nativa, desarrollada en Kotlin, que permite a los usuarios explorar una colección de álbumes de música, ver sus detalles y navegar entre artistas y coleccionistas. La aplicación está diseñada siguiendo las prácticas modernas de desarrollo de Android, utilizando componentes de Jetpack y una arquitectura MVVM.

## Características Implementadas

- **Galería de Álbumes**: Visualización de álbumes en una cuadrícula de dos columnas.
- **Búsqueda de Álbumes**: Filtrado de la galería de álbumes en tiempo real.
- **Detalle del Álbum**: Pantalla de detalle con la portada del álbum como fondo, artista, fecha, género y una galería de tracks.
- **Navegación Moderna**: Uso de `BottomNavigationView` y el Componente de Navegación de Jetpack para una experiencia de usuario fluida.
- **Pruebas de UI**: Pruebas de instrumentación con Espresso para validar la navegación y la funcionalidad de búsqueda.

## Prerrequisitos

Para construir y ejecutar este proyecto, necesitarás:

- **Android Studio**: Se recomienda la última versión estable (e.g., Koala o superior).
- **JDK 11**: El proyecto está configurado para usar Java 11.
- **Servidor Backend Local**: La aplicación depende de una API local para obtener los datos. Debes tener un servidor corriendo en `http://localhost:3000`.

## Cómo Empezar

Sigue estos pasos para poner en funcionamiento la aplicación en tu entorno de desarrollo:

1.  **Clonar el Repositorio**
    ```bash
    git clone <URL_DEL_REPOSITORIO>
    cd AppVinilos
    ```

2.  **Abrir en Android Studio**
    -   Abre Android Studio.
    -   Selecciona `File > Open` y navega hasta la carpeta del proyecto que acabas de clonar.
    -   Espera a que Gradle sincronice todas las dependencias del proyecto. Este proceso puede tardar unos minutos.

3.  **Ejecutar el Backend**
    -   **¡Paso crucial!** Antes de ejecutar la app, asegúrate de que tu servidor backend esté corriendo en tu máquina local, en el puerto `3000`.
    -   La aplicación está configurada para conectarse a la dirección `http://10.0.2.2:3000`. Esta es la IP especial que el emulador de Android usa para comunicarse con el `localhost` de la computadora anfitriona.

4.  **Ejecutar la Aplicación**
    -   Selecciona un emulador de Android o un dispositivo físico conectado.
    -   Haz clic en el botón **Run 'app'** (el triángulo verde) en la barra de herramientas superior.

## Ejecutar las Pruebas

El proyecto incluye pruebas unitarias y de instrumentación para garantizar la calidad del código.

-   **Pruebas de Instrumentación (Espresso)**: Para ejecutar las pruebas de UI, navega a `app/src/androidTest/java/com/example/appvinilos`, haz clic derecho en la clase `AppNavigationTest` y selecciona **Run 'AppNavigationTest'**.

## Tecnologías y Librerías Utilizadas

-   **Kotlin**: Como lenguaje principal de programación.
-   **Android Jetpack**:
    -   **ViewModel**: Para gestionar los datos de la UI de forma consciente del ciclo de vida.
    -   **LiveData**: Para notificar a las vistas sobre los cambios en los datos.
    -   **Navigation Component**: Para manejar toda la navegación entre fragmentos.
    -   **View Binding**: Para interactuar con las vistas de forma segura.
-   **Retrofit**: Como cliente HTTP para realizar las peticiones a la API REST.
-   **Coil**: Para la carga de imágenes de forma eficiente.
-   **Material Components**: Para los componentes de UI, como `CardView`, `BottomNavigationView` y `CollapsingToolbarLayout`.
-   **Espresso**: Para las pruebas de instrumentación de la UI.


### Proceso de Desarrollo

El proyecto sigue el modelo **GitFlow**:

- **`main`**: contiene las versiones estables listas para producción.
- **`release/*`**: integra los entregables que han pasado todas las pruebas y validaciones en múltiples dispositivos y APIs de Android.
- **`develop`**: rama principal de desarrollo e integración continua.
- **`feature/*`**: ramas dedicadas a nuevas funcionalidades; se integran a `develop` mediante Pull Requests usando la modalidad squash and merge tras revisión de código y pruebas locales.

---