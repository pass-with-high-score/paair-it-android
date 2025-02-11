package app.pwhs.paair_it.presentation.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.pwhs.paair_it.R
import app.pwhs.paair_it.ui.theme.PaairItTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination<RootGraph>
@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = koinViewModel(),
    navigator: DestinationsNavigator,
) {
    AuthUI(modifier = modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AuthUI(
    modifier: Modifier = Modifier,
    onSignInGoogleClick: () -> Unit = {},
    onSignInFacebookClick: () -> Unit = {},
    onContinueWithEmail: () -> Unit = {},
    onChoseLanguageClick: () -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                ),
                title = {},
                actions = {
                    Button(
                        onClick = onChoseLanguageClick,
                        contentPadding = PaddingValues(horizontal = 8.dp),
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "English",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    color = MaterialTheme.colorScheme.background,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.ic_language),
                                contentDescription = null,
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.auth),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(380.dp)
                    .width(200.dp)
            )

            val annotatedText = buildAnnotatedString {
                append("By signing in, you agree to our ")
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    withLink(LinkAnnotation.Url(url = "https://google.com")) {
                        append("Terms of Service")
                    }
                }
                append(" and ")
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    withLink(LinkAnnotation.Url(url = "https://google.com")) {
                        append("Privacy Policy")
                    }
                }
            }
            Text(
                text = annotatedText,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                )
            )

            Spacer(modifier = Modifier.height(16.dp))
            OutlinedButton(
                onClick = onSignInGoogleClick,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_google),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        "Sign in with Google",
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Medium
                        ),
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedButton(
                onClick = onSignInFacebookClick,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_facebook),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        "Sign in with Facebook",
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            TextButton(
                onClick = onContinueWithEmail,
            ) {
                Text(
                    text = "Continue with Email",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    ),
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
}

@Preview
@Composable
private fun AuthUIPreview() {
    PaairItTheme {
        AuthUI()
    }
}