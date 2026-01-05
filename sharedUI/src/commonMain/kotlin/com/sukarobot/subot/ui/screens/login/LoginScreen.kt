package com.sukarobot.subot.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.sukarobot.subot.ui.components.AppDialog
import com.sukarobot.subot.ui.components.AppPasswordTextField
import com.sukarobot.subot.ui.components.AppPrimaryButton
import com.sukarobot.subot.ui.components.AppScaffold
import com.sukarobot.subot.ui.components.AppTextButton
import com.sukarobot.subot.ui.components.AppTextField
import com.sukarobot.subot.ui.components.FullScreenLoading
import com.sukarobot.subot.ui.components.icons.EmailOutlined
import com.sukarobot.subot.ui.components.icons.Hicon
import com.sukarobot.subot.ui.components.icons.LockOutlined

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
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primary
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                // Top Section (Blue Background)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 24.dp)
                ) {
                    Text(
                        text = "Halo!",
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Masuk dengan aman menggunakan email dan kata sandi Anda.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Bottom Section (White Card)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .verticalScroll(rememberScrollState())
                                .padding(horizontal = 24.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Spacer(modifier = Modifier.height(32.dp))
                            
                            Text(
                                text = "Sign in",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onBackground
                            )

                            Spacer(modifier = Modifier.height(24.dp))

                            // Email field
                            AppTextField(
                                value = uiState.email,
                                onValueChange = { onEvent(LoginEvent.EmailChanged(it)) },
                                label = "Email",
                                placeholder = "Enter your mail",
                                leadingIcon = Hicon.EmailOutlined,
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
                                placeholder = "Enter your Password",
                                leadingIcon = Hicon.LockOutlined,
                                visibilityIcon = Icons.Default.Visibility,
                                visibilityOffIcon = Icons.Default.VisibilityOff,
                                isError = uiState.passwordError != null,
                                errorMessage = uiState.passwordError,
                                imeAction = ImeAction.Done,
                                onImeAction = { onEvent(LoginEvent.ValidateAndLogin) },
                                enabled = !uiState.isLoading,
                                modifier = Modifier
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            // Forgot Password
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                AppTextButton(
                                    text = "Forgot password?",
                                    onClick = { onEvent(LoginEvent.ForgotPassword) }
                                )
                            }

                            Spacer(modifier = Modifier.height(32.dp))

                            // Login button
                            AppPrimaryButton(
                                text = if (uiState.isLoading) "Signing in..." else "Sign in",
                                onClick = { onEvent(LoginEvent.ValidateAndLogin) },
                                enabled = !uiState.isLoading,
                                modifier = Modifier.fillMaxWidth()
                            )
                            
                            Spacer(modifier = Modifier.height(24.dp))
                        }

                        // Footer (Sign up link)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Don't have an account? ",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            AppTextButton(
                                text = "Sign up",
                                onClick = { /* Handle sign up */ }
                            )
                        }
                    }
                }
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

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen(
        onEvent = {},
        uiState = LoginUiState(),
        onLoginSuccess = {}
    )
}
