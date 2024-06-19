import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.mmartosdev.medium.App
import com.mmartosdev.medium.Emojis
import com.mmartosdev.medium.HelloWorld
import kotlinx.browser.window
import org.jetbrains.skiko.wasm.onWasmReady
import org.w3c.dom.url.URLSearchParams

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    onWasmReady {
        CanvasBasedWindow("MediumCompose") {
            val queryParams = URLSearchParams(window.location.search)
            when (queryParams.get("sample")) {
                "hello_world" -> HelloWorld()
                "emojis" -> Emojis()
                else -> App(queryParams.get("title"))
            }

        }
    }
}
