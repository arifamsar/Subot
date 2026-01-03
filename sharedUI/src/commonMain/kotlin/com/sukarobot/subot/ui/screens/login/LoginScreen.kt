package com.sukarobot.subot.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sukarobot.subot.ui.components.AppDialog
import com.sukarobot.subot.ui.components.AppPasswordTextField
import com.sukarobot.subot.ui.components.AppPrimaryButton
import com.sukarobot.subot.ui.components.AppScaffold
import com.sukarobot.subot.ui.components.AppTextButton
import com.sukarobot.subot.ui.components.AppTextField
import com.sukarobot.subot.ui.components.FullScreenLoading

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onEvent: (LoginEvent) -> Unit,
    uiState: LoginUiState,
    onLoginSuccess: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    var showSuccessDialog by remember { mutableStateOf(false) }

    LaunchedEffect(uiState.isLoginSuccessful) {
        if (uiState.isLoginSuccessful) {
            showSuccessDialog = true
        }
    }

    if (uiState.loginError != null) {
        AppDialog(
            title = "Login Failed",
            message = uiState.loginError,
            confirmText = "Retry",
            onConfirm = { onEvent(LoginEvent.ClearLoginError) },
            dismissText = "Close",
            onDismiss = { onEvent(LoginEvent.ClearLoginError) }
        )
    }

    if (showSuccessDialog) {
        AppDialog(
            title = "Login Successful",
            message = "You have been logged in successfully.",
            confirmText = "Continue",
            onConfirm = {
                onEvent(LoginEvent.ClearLoginSuccess)
                showSuccessDialog = false
                onLoginSuccess()
            },
            dismissText = null,
            onDismiss = null,
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    }

    AppScaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(48.dp))

                // Logo
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = MaterialTheme.shapes.large
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.SmartToy,
                        contentDescription = "Subot Logo",
                        modifier = Modifier.size(60.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Title
                Text(
                    text = "Welcome Back",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Sign in to continue",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(48.dp))

                // Email field
                AppTextField(
                    value = uiState.email,
                    onValueChange = { onEvent(LoginEvent.EmailChanged(it)) },
                    label = "Email",
                    placeholder = "Enter your email",
                    leadingIcon = Icons.Default.Email,
                    isError = uiState.emailError != null,
                    errorMessage = uiState.emailError,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                    enabled = !uiState.isLoading,
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Password field
                AppPasswordTextField(
                    value = uiState.password,
                    onValueChange = { onEvent(LoginEvent.PasswordChanged(it)) },
                    label = "Password",
                    placeholder = "Enter your password",
                    leadingIcon = Icons.Default.Lock,
                    visibilityIcon = Icons.Default.Visibility,
                    visibilityOffIcon = Icons.Default.VisibilityOff,
                    isError = uiState.passwordError != null,
                    errorMessage = uiState.passwordError,
                    imeAction = ImeAction.Done,
                    onImeAction = { onEvent(LoginEvent.ValidateAndLogin) },
                    enabled = !uiState.isLoading,
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Forgot password
                Box(modifier = Modifier.fillMaxWidth()) {
                    AppTextButton(
                        text = "Forgot Password?",
                        onClick = { onEvent(LoginEvent.ForgotPassword) },
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Login button
                AppPrimaryButton(
                    text = if (uiState.isLoading) "Signing in..." else "Sign In",
                    onClick = { onEvent(LoginEvent.ValidateAndLogin) },
                    enabled = !uiState.isLoading,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.weight(1f))

//                // Sign up link
//                Column(
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Text(
//                        text = "Don't have an account?",
//                        style = MaterialTheme.typography.bodyMedium,
//                        color = MaterialTheme.colorScheme.onSurfaceVariant
//                    )
//                    AppTextButton(
//                        text = "Sign Up",
//                        onClick = { /* Handle sign up */ }
//                    )
//                }

                Spacer(modifier = Modifier.height(24.dp))
            }

            // Loading overlay
            if (uiState.isLoading) {
                Dialog(
                    onDismissRequest = {},
                    properties = DialogProperties(
                        dismissOnBackPress = false,
                        dismissOnClickOutside = false,
                        usePlatformDefaultWidth = false
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Transparent),
                        contentAlignment = Alignment.Center
                    ) {
                        FullScreenLoading()
                    }
                }
            }

            // Snackbar
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}
