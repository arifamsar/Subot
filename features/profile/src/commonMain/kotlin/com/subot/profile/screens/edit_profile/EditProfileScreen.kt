package com.subot.profile.screens.edit_profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.subot.core.ui.components.AppCircleImage
import com.subot.core.ui.components.AppPullToRefresh
import com.subot.core.ui.components.AppPrimaryButton
import com.subot.core.ui.components.AppScaffold
import com.subot.core.ui.components.AppTextField
import com.subot.core.ui.components.icons.ArrowLeft
import com.subot.core.ui.components.icons.Hicon
import com.subot.core.ui.components.icons.ProfileCircleFilled
import com.subot.core.ui.components.rememberImagePicker

@Composable
fun EditProfileScreen(
    viewModel: EditProfileViewModel,
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
            viewModel.onEvent(EditProfileEvent.ClearSuccess)
        }
    }

    val imagePicker = rememberImagePicker { bytes, fileName ->
        viewModel.onEvent(EditProfileEvent.FotoProfileChanged(bytes, fileName))
    }

    AppScaffold(
        modifier = modifier,
        topBarTitle = "Edit Profil",
        navigationIcon = Hicon.ArrowLeft,
        onNavigationClick = onBack,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        val showInitialLoading = uiState.isInitialLoading &&
                uiState.namaLengkap.isBlank()

        if (showInitialLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        } else {
            AppPullToRefresh(
                isRefreshing = uiState.isInitialLoading,
                onRefresh = { viewModel.onEvent(EditProfileEvent.Refresh) },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                    // Error message
                    if (uiState.error != null) {
                        ErrorBanner(
                            message = uiState.error!!,
                            onDismiss = { viewModel.onEvent(EditProfileEvent.ClearError) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp)
                        )
                    }

                    // Profile Image Selector
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .clickable {
                                if (!uiState.isLoading) {
                                    imagePicker.launch()
                                }
                            }
                    ) {
                        AppCircleImage(
                            url = uiState.fotoProfileBytes ?: uiState.profileImageUrl,
                            contentDescription = "Foto Profil",
                            size = 100.dp,
                            fallbackIcon = Hicon.ProfileCircleFilled,
                            fallbackIconTint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                        )

                        // Camera overlay
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(32.dp)
                                .background(Color.Black.copy(alpha = 0.5f))
                                .align(Alignment.BottomCenter),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.CameraAlt,
                                contentDescription = "Ubah Foto",
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Form Fields
                    AppTextField(
                        value = uiState.namaLengkap,
                        onValueChange = { viewModel.onEvent(EditProfileEvent.NameChanged(it)) },
                        label = "Nama Lengkap",
                        placeholder = "Masukkan nama lengkap",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        isError = uiState.nameError != null,
                        errorMessage = uiState.nameError,
                        enabled = !uiState.isLoading
                    )

                    Row(modifier = Modifier.fillMaxWidth()) {
                        AppTextField(
                            value = uiState.tempatLahir,
                            onValueChange = { viewModel.onEvent(EditProfileEvent.TempatLahirChanged(it)) },
                            label = "Tempat Lahir",
                            placeholder = "Tempat lahir",
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 8.dp, bottom = 16.dp),
                            enabled = !uiState.isLoading
                        )

                        AppTextField(
                            value = uiState.tanggalLahir,
                            onValueChange = { viewModel.onEvent(EditProfileEvent.TanggalLahirChanged(it)) },
                            label = "Tanggal Lahir",
                            placeholder = "YYYY-MM-DD",
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 8.dp, bottom = 16.dp),
                            isError = uiState.dateError != null,
                            errorMessage = uiState.dateError,
                            enabled = !uiState.isLoading
                        )
                    }

                    AppTextField(
                        value = uiState.kelas,
                        onValueChange = { viewModel.onEvent(EditProfileEvent.KelasChanged(it)) },
                        label = "Kelas",
                        placeholder = "Kelas sekolah",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        enabled = !uiState.isLoading
                    )

                    AppTextField(
                        value = uiState.telephone,
                        onValueChange = { viewModel.onEvent(EditProfileEvent.TelephoneChanged(it)) },
                        label = "Nomor Telepon",
                        placeholder = "081xxxxxxxxx",
                        keyboardType = KeyboardType.Phone,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        isError = uiState.phoneError != null,
                        errorMessage = uiState.phoneError,
                        enabled = !uiState.isLoading
                    )

                    AppTextField(
                        value = uiState.alamat,
                        onValueChange = { viewModel.onEvent(EditProfileEvent.AlamatChanged(it)) },
                        label = "Alamat Rumah",
                        placeholder = "Alamat lengkap",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        enabled = !uiState.isLoading
                    )

                    AppTextField(
                        value = uiState.namaOrtu,
                        onValueChange = { viewModel.onEvent(EditProfileEvent.NamaOrtuChanged(it)) },
                        label = "Nama Orang Tua",
                        placeholder = "Nama orang tua",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        enabled = !uiState.isLoading
                    )

                    AppTextField(
                        value = uiState.workOrtu,
                        onValueChange = { viewModel.onEvent(EditProfileEvent.WorkOrtuChanged(it)) },
                        label = "Pekerjaan Orang Tua",
                        placeholder = "Pekerjaan orang tua",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp),
                        enabled = !uiState.isLoading
                    )

                    // Submit Button
                    AppPrimaryButton(
                        text = if (uiState.isLoading) "Menyimpan..." else "Simpan Perubahan",
                        onClick = { viewModel.onEvent(EditProfileEvent.Submit) },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !uiState.isLoading
                    )
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
                    imageVector = Icons.Default.Info, // Placeholder since Close isn't imported
                    contentDescription = "Tutup",
                    tint = MaterialTheme.colorScheme.onErrorContainer
                )
            }
        }
    }
}
