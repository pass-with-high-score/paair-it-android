package app.pwhs.paair_it

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Alignment
import androidx.navigation.compose.rememberNavController
import app.pwhs.paair_it.ui.theme.PaairItTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.generated.NavGraphs
import com.ramcosta.composedestinations.rememberNavHostEngine

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PaairItTheme {
                val navController = rememberNavController()
                val navHostEngine = rememberNavHostEngine(
                    navHostContentAlignment = Alignment.TopCenter,
                )
                DestinationsNavHost(
                    navGraph = NavGraphs.root,
                    navController = navController,
                    engine = navHostEngine,
                )
            }
        }
    }
}
