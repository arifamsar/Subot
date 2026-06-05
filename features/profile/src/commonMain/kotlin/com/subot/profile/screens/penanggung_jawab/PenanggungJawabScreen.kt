package com.subot.profile.screens.penanggung_jawab

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.subot.core.ui.components.AppPrimaryButton
import com.subot.core.ui.components.AppPullToRefresh
import com.subot.core.ui.components.AppScaffold
import com.subot.core.ui.components.AppTextField
import com.subot.core.ui.components.icons.ArrowLeft
import com.subot.core.ui.components.icons.Hicon
import com.subot.core.ui.components.icons.EmailOutlined
import com.subot.core.ui.components.icons.ProfileOutlined

@Composable
fun PenanggungJawabScreen(
    viewModel: PenanggungJawabViewModel,
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
            viewModel.onEvent(PenanggungJawabEvent.ClearSuccess)
        }
    }

    AppScaffold(
        modifier = modifier,
        topBarTitle = "Penanggung Jawab",
        navigationIcon = Hicon.ArrowLeft,
        onNavigationClick = onBack,
        floatingActionButton = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        AppPullToRefresh(
            isRefreshing = uiState.isRefreshing,
            onRefresh = { viewModel.onEvent(PenanggungJawabEvent.Refresh) },
            modifier = Modifier.padding(innerPadding).fillMaxSize()
        ) {
            val showInitialLoading = uiState.isInitialLoading &&
                uiState.namaPenanggungJawab.isBlank() &&
                uiState.emailPenanggungJawab.isBlank() &&
                uiState.telephonePenanggungJawab.isBlank()

            if (showInitialLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        // Error message
                        if (uiState.error != null) {
                            ErrorBanner(
                                message = uiState.error!!,
                                onDismiss = { viewModel.onEvent(PenanggungJawabEvent.ClearError) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 16.dp)
                            )
                        }

                        // Form title
                        Text(
                            text = "Informasi Penanggung Jawab",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(bottom = 24.dp)
                        )

                        // Name field
                        AppTextField(
                            value = uiState.namaPenanggungJawab,
                            onValueChange = { viewModel.onEvent(PenanggungJawabEvent.NameChanged(it)) },
                            label = "Nama Penanggung Jawab",
                            placeholder = "Masukkan nama lengkap",
                            leadingIcon = Hicon.ProfileOutlined,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            isError = uiState.nameError != null,
                            errorMessage = uiState.nameError,
                            enabled = !uiState.isLoading && !uiState.isInitialLoading
                        )

                        // Email field
                        AppTextField(
                            value = uiState.emailPenanggungJawab,
                            onValueChange = { viewModel.onEvent(PenanggungJawabEvent.EmailChanged(it)) },
                            label = "Email",
                            placeholder = "contoh@email.com",
                            leadingIcon = Hicon.EmailOutlined,
                            keyboardType = KeyboardType.Email,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            isError = uiState.emailError != null,
                            errorMessage = uiState.emailError,
                            enabled = !uiState.isLoading && !uiState.isInitialLoading
                        )

                        // Phone field
                        AppTextField(
                            value = uiState.telephonePenanggungJawab,
                            onValueChange = { viewModel.onEvent(PenanggungJawabEvent.PhoneChanged(it)) },
                            label = "Nomor Telepon",
                            placeholder = "08xxxxxxxxxx",
                            keyboardType = KeyboardType.Phone,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 32.dp),
                            isError = uiState.phoneError != null,
                            errorMessage = uiState.phoneError,
                            enabled = !uiState.isLoading && !uiState.isInitialLoading
                        )

                        // Submit button
                        AppPrimaryButton(
                            text = if (uiState.isLoading) "Menyimpan..." else "Simpan",
                            onClick = { viewModel.onEvent(PenanggungJawabEvent.Submit) },
                            enabled = !uiState.isLoading && !uiState.isInitialLoading,
                            modifier = Modifier.fillMaxWidth()
                        )

                        // Helper text
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceContainerLow
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(20.dp).padding(top = 2.dp)
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text(
                                        text = "Informasi Penting",
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Pastikan data penanggung jawab sekolah Anda sudah benar and aktif agar dapat dihubungi jika diperlukan.",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                }
            }
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
            Text(
                text = message,
                color = MaterialTheme.colorScheme.onErrorContainer,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = onDismiss, modifier = Modifier.size(24.dp)) {
                // We're using Info icon as a placeholder since there's no custom Close icon imported
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Tutup",
                    tint = MaterialTheme.colorScheme.onErrorContainer
                )
            }
        }
    }
}
