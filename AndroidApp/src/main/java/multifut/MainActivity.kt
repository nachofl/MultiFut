package multifut

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent { AppNavigation() }
	}
}

@Composable
fun AppNavigation() {
	// TODO: navega o muestra tu UI aquí
}
