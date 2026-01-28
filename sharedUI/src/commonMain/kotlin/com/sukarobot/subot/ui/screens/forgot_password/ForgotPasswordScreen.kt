package com.sukarobot.subot.ui.screens.forgot_password

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.subot.core.ui.components.AppDialog
import com.subot.core.ui.components.AppPrimaryButton
import com.subot.core.ui.components.AppScaffold
import com.subot.core.ui.components.AppTextField
import com.subot.core.ui.components.FullScreenLoading
import com.subot.core.ui.components.icons.ArrowLeft
import com.subot.core.ui.components.icons.Hicon
import org.jetbrains.compose.resources.stringResource
import subot.core.ui.generated.resources.Res
import subot.core.ui.generated.resources.forgot_password_instruction
import subot.core.ui.generated.resources.forgot_password_title
import subot.core.ui.generated.resources.ok
import subot.core.ui.generated.resources.password_reset_sent
import subot.core.ui.generated.resources.phone_number_label
import subot.core.ui.generated.resources.phone_number_placeholder
import subot.core.ui.generated.resources.reset_your_password
import subot.core.ui.generated.resources.submit
import subot.core.ui.generated.resources.success

@Composable
fun ForgotPasswordScreen(
    uiState: ForgotPasswordUiState,
    onEvent: (ForgotPasswordEvent) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (uiState.isSuccess) {
        AppDialog(
            title = stringResource(Res.string.success),
            message = stringResource(Res.string.password_reset_sent),
            confirmText = stringResource(Res.string.ok),
            onConfirm = {
                onEvent(ForgotPasswordEvent.ClearSuccess)
                onBack()
            }
        )
    }

    AppScaffold(
        modifier = modifier,
        topBarTitle = stringResource(Res.string.forgot_password_title),
        navigationIcon = Hicon.ArrowLeft,
        onNavigationClick = onBack,
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                
                Text(
                    text = stringResource(Res.string.reset_your_password),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = stringResource(Res.string.forgot_password_instruction),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(32.dp))

                AppTextField(
                    value = uiState.phoneNumber,
                    onValueChange = { onEvent(ForgotPasswordEvent.PhoneNumberChanged(it)) },
                    label = stringResource(Res.string.phone_number_label),
                    placeholder = stringResource(Res.string.phone_number_placeholder),
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Done,
                    onImeAction = { onEvent(ForgotPasswordEvent.Submit) },
                    isError = uiState.error != null,
                    errorMessage = uiState.error,
                    enabled = !uiState.isLoading
                )

                Spacer(modifier = Modifier.height(32.dp))

                AppPrimaryButton(
                    text = stringResource(Res.string.submit),
                    onClick = { onEvent(ForgotPasswordEvent.Submit) },
                    enabled = !uiState.isLoading && uiState.phoneNumber.isNotBlank(),
                    modifier = Modifier.fillMaxWidth()
                )
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
                            .background(Color.Black.copy(alpha = 0.3f)),
                        contentAlignment = Alignment.Center
                    ) {
                        FullScreenLoading()
                    }
                }
            }
        }
    }
}
