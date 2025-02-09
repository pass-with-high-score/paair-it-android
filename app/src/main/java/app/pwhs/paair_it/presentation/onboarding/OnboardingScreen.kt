package app.pwhs.paair_it.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import app.pwhs.paair_it.R
import app.pwhs.paair_it.core.presentation.util.ObserveAsEvents
import app.pwhs.paair_it.ui.theme.PaairItTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.NavGraphs
import com.ramcosta.composedestinations.generated.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination<RootGraph>(start = true)
@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel = koinViewModel(),
    navigator: DestinationsNavigator
) {
    ObserveAsEvents(viewModel.uiEvent) { event ->
        when (event) {
            OnboardingEvent.OnboardingCompleted -> {
                navigator.navigate(HomeScreenDestination()) {
                    popUpTo(NavGraphs.root) {
                        saveState = false
                    }
                    launchSingleTop = true
                    restoreState = false
                }
            }
        }
    }
    OnboardingUI(modifier)
}

@Composable
private fun OnboardingUI(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_onboarding),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        Image(
            painter = painterResource(id = R.drawable.app_name),
            contentDescription = null,
        )
    }

}

@Preview
@Composable
private fun OnboardingUiPreview() {
    PaairItTheme {
        OnboardingUI()
    }
}