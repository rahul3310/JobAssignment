package process.com.jobassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import process.com.jobassignment.navigation.MainNavGraph
import process.com.jobassignment.navigation.Routes
import process.com.jobassignment.ui.theme.JobAssignmentTheme
import process.com.jobassignment.ui.utils.BottomBar

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContent {
            val navController: NavHostController = rememberNavController()
            JobAssignmentTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomBar(navController)
                    }
                ) {
                    Box(modifier = Modifier.padding(it)) {
                        MainNavGraph(navController)
                    }
                }
            }
        }
    }

}
