package com.subot.profile.screens.change_password

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.subot.core.ui.components.AppPasswordTextField
import com.subot.core.ui.components.AppPrimaryButton
import com.subot.core.ui.components.AppScaffold
import com.subot.core.ui.components.icons.ArrowLeft
import com.subot.core.ui.components.icons.Hicon
import com.subot.core.ui.components.icons.LockOutlined

@Composable
fun ChangePasswordScreen(
    viewModel: ChangePasswordViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.successMessage) {
        uiState.successMessage?.let {
            snackbarHostState.showSnackbar(
                message = it,
                duration = SnackbarDuration.Short
            )
            viewModel.onEvent(ChangePasswordEvent.ClearSuccess)
        }
    }

    AppScaffold(
        modifier = modifier,
        topBarTitle = "Ubah Kata Sandi",
        navigationIcon = Hicon.ArrowLeft,
        onNavigationClick = onBack,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // General Error Banner
            if (uiState.error != null) {
                ErrorBanner(
                    message = uiState.error!!,
                    onDismiss = { viewModel.onEvent(ChangePasswordEvent.ClearError) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp)
                )
            }

            Text(
                text = "Silakan perbarui kata sandi Anda di bawah ini.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Password fields
            AppPasswordTextField(
                value = uiState.currentPassword,
                onValueChange = { viewModel.onEvent(ChangePasswordEvent.CurrentPasswordChanged(it)) },
                label = "Kata Sandi Saat Ini",
                placeholder = "Masukkan kata sandi saat ini",
                leadingIcon = Hicon.LockOutlined,
                visibilityIcon = Icons.Default.Visibility,
                visibilityOffIcon = Icons.Default.VisibilityOff,
                isError = uiState.currentPasswordError != null,
                errorMessage = uiState.currentPasswordError,
                imeAction = ImeAction.Next,
                enabled = !uiState.isLoading,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            AppPasswordTextField(
                value = uiState.password,
                onValueChange = { viewModel.onEvent(ChangePasswordEvent.PasswordChanged(it)) },
                label = "Kata Sandi Baru",
                placeholder = "Minimal 8 karakter",
                leadingIcon = Hicon.LockOutlined,
                visibilityIcon = Icons.Default.Visibility,
                visibilityOffIcon = Icons.Default.VisibilityOff,
                isError = uiState.passwordError != null,
                errorMessage = uiState.passwordError,
                imeAction = ImeAction.Next,
                enabled = !uiState.isLoading,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            AppPasswordTextField(
                value = uiState.confirmPassword,
                onValueChange = { viewModel.onEvent(ChangePasswordEvent.ConfirmPasswordChanged(it)) },
                label = "Konfirmasi Kata Sandi Baru",
                placeholder = "Masukkan kembali kata sandi baru",
                leadingIcon = Hicon.LockOutlined,
                visibilityIcon = Icons.Default.Visibility,
                visibilityOffIcon = Icons.Default.VisibilityOff,
                isError = uiState.confirmPasswordError != null,
                errorMessage = uiState.confirmPasswordError,
                imeAction = ImeAction.Done,
                onImeAction = { viewModel.onEvent(ChangePasswordEvent.Submit) },
                enabled = !uiState.isLoading,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Submit Button
            AppPrimaryButton(
                text = if (uiState.isLoading) "Menyimpan..." else "Ubah Kata Sandi",
                onClick = { viewModel.onEvent(ChangePasswordEvent.Submit) },
                enabled = !uiState.isLoading,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun ErrorBanner(
    message: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.errorContainer
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Error",
                tint = MaterialTheme.colorScheme.onErrorContainer
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = message,
                color = MaterialTheme.colorScheme.onErrorContainer,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = onDismiss, modifier = Modifier.size(24.dp)) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Tutup",
                    tint = MaterialTheme.colorScheme.onErrorContainer
                )
            }
        }
    }
}
