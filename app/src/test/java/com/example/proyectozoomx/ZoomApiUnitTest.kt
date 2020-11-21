import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.proyectozoomx.entities.Credenciales
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

}
