package com.sukarobot.subot.ui.screens.login

import androidx.compose.foundation.Image
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
import com.subot.core.ui.components.AppDialog
import com.subot.core.ui.components.AppPasswordTextField
import com.subot.core.ui.components.AppPrimaryButton
import com.subot.core.ui.components.AppScaffold
import com.subot.core.ui.components.AppTextButton
import com.subot.core.ui.components.AppTextField
import com.subot.core.ui.components.FullScreenLoading
import com.subot.core.ui.components.icons.EmailOutlined
import com.subot.core.ui.components.icons.Hicon
import com.subot.core.ui.components.icons.LockOutlined
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import subot.core.ui.generated.resources.Res
import subot.core.ui.generated.resources.close
import subot.core.ui.generated.resources.continue_text
import subot.core.ui.generated.resources.email_label
import subot.core.ui.generated.resources.email_placeholder
import subot.core.ui.generated.resources.email_required
import subot.core.ui.generated.resources.forgot_password
import subot.core.ui.generated.resources.halo
import subot.core.ui.generated.resources.logged_in_successfully
import subot.core.ui.generated.resources.login_failed
import subot.core.ui.generated.resources.login_failed_general
import subot.core.ui.generated.resources.login_subtitle
import subot.core.ui.generated.resources.login_successful
import subot.core.ui.generated.resources.logo_horizontal
import subot.core.ui.generated.resources.number_required
import subot.core.ui.generated.resources.password_label
import subot.core.ui.generated.resources.password_length_requirement
import subot.core.ui.generated.resources.password_placeholder
import subot.core.ui.generated.resources.password_required
import subot.core.ui.generated.resources.retry
import subot.core.ui.generated.resources.sign_in
import subot.core.ui.generated.resources.signing_in
import subot.core.ui.generated.resources.uppercase_required
import subot.core.ui.generated.resources.valid_email_required

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onEvent: (LoginEvent) -> Unit,
    uiState: LoginUiState,
    navigateToForgot: () -> Unit,
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
            title = stringResource(Res.string.login_failed),
            message = stringResource(Res.string.login_failed_general),
            confirmText = stringResource(Res.string.retry),
            onConfirm = { onEvent(LoginEvent.ClearLoginError) },
            dismissText = stringResource(Res.string.close),
            onDismiss = { onEvent(LoginEvent.ClearLoginError) }
        )
    }

    if (showSuccessDialog) {
        AppDialog(
            title = stringResource(Res.string.login_successful),
            message = stringResource(Res.string.logged_in_successfully),
            confirmText = stringResource(Res.string.continue_text),
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
                        text = stringResource(Res.string.halo),
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(Res.string.login_subtitle),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Bottom Section (White Card)
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
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
                                text = stringResource(Res.string.sign_in),
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onBackground
                            )

                            Spacer(modifier = Modifier.height(24.dp))

                            // Email field
                            AppTextField(
                                value = uiState.email,
                                onValueChange = { onEvent(LoginEvent.EmailChanged(it)) },
                                label = stringResource(Res.string.email_label),
                                placeholder = stringResource(Res.string.email_placeholder),
                                leadingIcon = Hicon.EmailOutlined,
                                isError = uiState.emailError != null,
                                errorMessage = getErrorMessage(uiState.emailError),
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
                                label = stringResource(Res.string.password_label),
                                placeholder = stringResource(Res.string.password_placeholder),
                                leadingIcon = Hicon.LockOutlined,
                                visibilityIcon = Icons.Default.Visibility,
                                visibilityOffIcon = Icons.Default.VisibilityOff,
                                isError = uiState.passwordError != null,
                                errorMessage = getErrorMessage(uiState.passwordError),
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
                                    text = stringResource(Res.string.forgot_password),
                                    onClick = { navigateToForgot() }
                                )
                            }

                            Spacer(modifier = Modifier.height(32.dp))

                            // Login button
                            AppPrimaryButton(
                                text = if (uiState.isLoading) stringResource(Res.string.signing_in) else stringResource(Res.string.sign_in),
                                onClick = { onEvent(LoginEvent.ValidateAndLogin) },
                                enabled = !uiState.isLoading,
                                modifier = Modifier.fillMaxWidth()
                            )
                            
                            Spacer(modifier = Modifier.height(24.dp))
                        }

                        // Footer
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(Res.drawable.logo_horizontal),
                                contentDescription = "Logo",
                                modifier = Modifier.height(24.dp)
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

/**
 * Helper function to map validation error codes to string resources
 */
@Composable
private fun getErrorMessage(error: ValidationError?): String? {
    if (error == null) return null
    return when (error) {
        ValidationError.EMAIL_REQUIRED -> stringResource(Res.string.email_required)
        ValidationError.INVALID_EMAIL_FORMAT -> stringResource(Res.string.valid_email_required)
        ValidationError.PASSWORD_REQUIRED -> stringResource(Res.string.password_required)
        ValidationError.PASSWORD_TOO_SHORT -> stringResource(Res.string.password_length_requirement)
        ValidationError.PASSWORD_NO_UPPERCASE -> stringResource(Res.string.uppercase_required)
        ValidationError.PASSWORD_NO_NUMBER -> stringResource(Res.string.number_required)
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen(
        onEvent = {},
        uiState = LoginUiState(),
        onLoginSuccess = {},
        navigateToForgot = {}
    )
}
