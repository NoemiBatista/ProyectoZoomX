import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.proyectozoomx.entities.Credenciales
import com.example.proyectozoomx.entities.Sala
import com.example.proyectozoomx.usescases.ClientZoomApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.Request
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.SoftAssertions.assertSoftly
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.io.IOException
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
class ZoomApiUnitTest {
    @Test
    @Throws(IOException::class)
    fun givenAuthUser_whenGetUsuario_thenGetResponseOk() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://zoomx.freeddns.org:8443/usuario")
            .header("Authorization", Credentials.basic("adm", "adm"))
            .build()
        val rolExpected = "ROLE_ADMIN"
        val response = client.newCall(request).execute()
        val body = response.body!!.string()
        //println(body)
        assertSoftly {
            it.assertThat(response.code).isEqualTo(200)
            it.assertThat(body).contains(rolExpected)
        }
        response.close()
    }

    @Test
    @Throws(IOException::class)
    fun givenUnauthUser_whenGetUsuario_thenGetResponseOk() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://zoomx.freeddns.org:8443/usuario")
            .header("Authorization", Credentials.basic("adm1", "adm1"))
            .build()
        val response = client.newCall(request).execute()
        val body = response.body!!.string()
        assertSoftly {
            it.assertThat(response.code).isEqualTo(401)
            it.assertThat(body).contains("Unauthorized")
        }
        response.close()
    }

    @Test
    @Throws(IOException::class)
    fun givenID_whenBuscarPorId_thenGetSala() {
        runBlocking {
            val credenciales = Credenciales("adm", "adm")
            val api = ClientZoomApi(credenciales, "https://zoomx.freeddns.org:8443")
            val sala = api.buscarPorId(1)

            assertThat(sala).isNotNull

        }
    }

    @Test
    @Throws(IOException::class)
    fun givenNombre_whenBuscarPorNombre_thenGetSalasList() {
        runBlocking {
            val credenciales = Credenciales("adm", "adm")
            val api = ClientZoomApi(credenciales, "https://zoomx.freeddns.org:8443")
            val salas = api.buscarPorNombre("Sala")

            assertThat(salas).isNotNull

        }
    }


    @Test
    @Throws(IOException::class)
    fun givenResponsable_whenBuscarPorResponsable_thenGetSalasList() {
        runBlocking {
            val credenciales = Credenciales("adm", "adm")
            val api = ClientZoomApi(credenciales, "https://zoomx.freeddns.org:8443")
            val salas = api.buscarPorResponsable(" ")

            assertThat(salas).isNotNull

        }
    }

    @Test
    @Throws(IOException::class)
    fun givenFecha_whenBuscarPorFecha_thenGetSalasList() {
        runBlocking {
            val credenciales = Credenciales("adm", "adm")
            val api = ClientZoomApi(credenciales, "https://zoomx.freeddns.org:8443")
            val salas = api.buscarPorFecha(LocalDateTime.parse("2020-11-26T00:56:00"))

            assertThat(salas)

        }
    }

    @Test
    @Throws(IOException::class)
    fun givenAdmin_whenPostSala_thenGetResponse201() {
        runBlocking {
            val credenciales = Credenciales("adm", "adm")
            val api = ClientZoomApi(credenciales, "https://zoomx.freeddns.org:8443")
            api.ingresarSala(
                sala = Sala(
                    "Otra Prueba Javier",
                    "Otro Responsable",
                    (LocalDateTime.parse("2020-11-26T00:56:00")),
                    2,
                    "https://cdn.pixabay.com/photo/2020/09/13/13/00/charles-bridge-556817"
                )
            )


        }
    }

    @Test
    @Throws(IOException::class)
    fun givenAdmin_whenDeleteSala_thenGetResponse204() {
        runBlocking {
            val credenciales = Credenciales("adm", "adm")
            val api = ClientZoomApi(credenciales, "https://zoomx.freeddns.org:8443")
            api.borrarSala(8)


        }
    }

    @Test
    @Throws(IOException::class)
    fun givenAdmin_whenPatchSala_thenGetResponse204() {
        runBlocking {
            val credenciales = Credenciales("adm", "adm")
            val api = ClientZoomApi(credenciales, "https://zoomx.freeddns.org:8443")
            api.modificarSala(
                10,
                sala = Sala(
                    "sala modificada",
                    "responsable modificado",
                    (LocalDateTime.parse("2020-11-26T00:56:00")),
                    10,
                    "https://cdn.pixabay.com/photo/2020/09/13/13/00/charles-bridge-556817"
                )
            )


        }
    }
}





