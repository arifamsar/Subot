package com.subot.schedule

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.EventNote
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.subot.core.domain.model.Schedule
import com.subot.core.ui.components.AppPrimaryButton
import com.subot.core.ui.components.AppLoadingIndicator
import com.subot.core.ui.components.AppPullToRefresh
import com.subot.core.ui.components.rememberPlatformPdfHelper
import kotlin.time.Clock
import kotlin.time.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreen(
    onScheduleClick: (Int) -> Unit = {},
    modifier: Modifier = Modifier,
    viewModel: ScheduleViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val pdfHelper = rememberPlatformPdfHelper()
    val snackbarHostState = remember { SnackbarHostState() }
    var showExportDialog by remember { mutableStateOf(false) }

    LaunchedEffect(uiState.exportedPdfBytes) {
        uiState.exportedPdfBytes?.let { bytes ->
            val timestamp = Clock.System.now().toEpochMilliseconds()
            pdfHelper.saveAndOpenPdf("laporan_pertemuan_$timestamp.pdf", bytes)
            viewModel.onEvent(ScheduleEvent.ClearExportResult)
        }
    }

    LaunchedEffect(uiState.exportError) {
        uiState.exportError?.let { error ->
            snackbarHostState.showSnackbar(error)
            viewModel.onEvent(ScheduleEvent.ClearExportResult)
        }
    }

    if (showExportDialog) {
        ExportReportDialog(
            onDismissRequest = { showExportDialog = false },
            onExport = { startDate, endDate ->
                viewModel.onEvent(ScheduleEvent.ExportReport(null, startDate, endDate))
            }
        )
    }

    AppPullToRefresh(
        isRefreshing = uiState.isRefreshing,
        onRefresh = { viewModel.onEvent(ScheduleEvent.Refresh) },
        modifier = modifier.fillMaxSize()
    ) {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            snackbarHost = { SnackbarHost(snackbarHostState) },
            topBar = {
                TopAppBar(
                    title = {
                        Column {
                            Text(
                                text = uiState.userRole?.replaceFirstChar { it.uppercase() } ?: "Role",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = "Jadwal",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior,
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        scrolledContainerColor = MaterialTheme.colorScheme.surface,
                        titleContentColor = MaterialTheme.colorScheme.onSurface
                    )
                )
            },
            contentWindowInsets = WindowInsets(0.dp)
        ) { paddingValues ->
            Box(
                modifier = Modifier.padding(paddingValues).fillMaxSize()
            ) {
                if (uiState.isLoading) {
                    AppLoadingIndicator(modifier = Modifier.align(Alignment.Center))
                } else if (uiState.error != null) {
                    Column(
                        modifier = Modifier.align(Alignment.Center).padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = uiState.error!!, color = MaterialTheme.colorScheme.error)
                        AppPrimaryButton(
                            onClick = { viewModel.onEvent(ScheduleEvent.Refresh) },
                            text = "Coba Lagi"
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        item {
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        // Summary Cards Grid (2x2)
                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                SummaryCard(
                                    title = "Pertemuan Terdekat",
                                    value = uiState.schedules.firstOrNull()?.time ?: "Belum ada",
                                    icon = Icons.Default.Timer,
                                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                    modifier = Modifier.weight(1f)
                                )
                                SummaryCard(
                                    title = "Jadwal Total",
                                    value = uiState.schedules.size.toString(),
                                    icon = Icons.Default.CalendarMonth,
                                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }

                        // Schedules List
                        item {
                            Text(
                                text = "Daftar Jadwal",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        if (uiState.schedules.isEmpty()) {
                            item {
                                EmptySchedulePlaceholder()
                            }
                        } else {
                            items(
                                items = uiState.schedules,
                                key = { it.id }
                            ) { schedule ->
                                ScheduleItemCard(
                                    schedule = schedule,
                                    onClick = { onScheduleClick(schedule.id) },
                                    modifier = Modifier.animateItem()
                                )
                            }
                        }

                        // Download Report Section
                        item {
                            DownloadReportCard(
                                isExporting = uiState.isExporting,
                                onExportClick = { showExportDialog = true }
                            )
                        }
                        
                        item {
                            Spacer(modifier = Modifier.height(24.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ScheduleItemCard(
    schedule: Schedule,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val statusColor = when (schedule.status.lowercase()) {
        "tercatat" -> MaterialTheme.colorScheme.primary
        "berlangsung" -> MaterialTheme.colorScheme.secondary
        "selesai" -> Color(0xFF10B981)
        "batal" -> MaterialTheme.colorScheme.error
        else -> MaterialTheme.colorScheme.onSurfaceVariant
    }
    val statusBgColor = when (schedule.status.lowercase()) {
        "tercatat" -> MaterialTheme.colorScheme.primaryContainer
        "berlangsung" -> MaterialTheme.colorScheme.secondaryContainer
        "selesai" -> Color(0xFF10B981).copy(alpha = 0.15f)
        "batal" -> MaterialTheme.colorScheme.errorContainer
        else -> MaterialTheme.colorScheme.surfaceVariant
    }

    OutlinedCard(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                modifier = Modifier.size(48.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Event,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = schedule.description,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    if (schedule.status.isNotEmpty()) {
                        Surface(
                            color = statusBgColor,
                            contentColor = statusColor,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = schedule.status.replaceFirstChar { it.uppercase() },
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
                Text(
                    text = schedule.time,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Trainer: ${schedule.trainerName}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.outline
            )
        }
    }
}

@Composable
fun EmptySchedulePlaceholder() {
    OutlinedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)),
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.1f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.CalendarToday,
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.outline
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Tidak ada jadwal yang tersedia.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun SummaryCard(
    title: String,
    value: String,
    icon: ImageVector,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Column {
                Text(
                    text = value,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelSmall,
                    lineHeight = 14.sp
                )
            }
        }
    }
}

@Composable
fun DownloadReportCard(
    isExporting: Boolean,
    onExportClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Unduh Laporan Pertemuan",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Pilih rentang tanggal, lalu unduh laporan catatan.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            
            AppPrimaryButton(
                text = if (isExporting) "MENGUNDUH..." else "BUKA PENGATURAN UNDUH",
                onClick = onExportClick,
                enabled = !isExporting
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialogWrapper(
    onDismissRequest: () -> Unit,
    onDateSelected: (String) -> Unit
) {
    val datePickerState = rememberDatePickerState()
    DatePickerDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                onClick = {
                    datePickerState.selectedDateMillis?.let { millis ->
                        val localDate = Instant.fromEpochMilliseconds(millis)
                            .toLocalDateTime(TimeZone.UTC).date
                        onDateSelected(localDate.toString())
                    }
                    onDismissRequest()
                }
            ) {
                Text("Pilih")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text("Batal")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExportReportDialog(
    onDismissRequest: () -> Unit,
    onExport: (startDate: String?, endDate: String?) -> Unit
) {
    var exportType by remember { mutableStateOf(0) } // 0: Semua Jadwal, 1: Rentang Tanggal
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    
    var showStartDatePicker by remember { mutableStateOf(false) }
    var showEndDatePicker by remember { mutableStateOf(false) }
    
    if (showStartDatePicker) {
        DatePickerDialogWrapper(
            onDismissRequest = { showStartDatePicker = false },
            onDateSelected = { startDate = it }
        )
    }
    
    if (showEndDatePicker) {
        DatePickerDialogWrapper(
            onDismissRequest = { showEndDatePicker = false },
            onDateSelected = { endDate = it }
        )
    }
    
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = "Unduh Laporan Pertemuan",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Pilih jenis laporan yang ingin Anda unduh.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    RadioButton(
                        selected = exportType == 0,
                        onClick = { exportType = 0 }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Semua Jadwal",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    RadioButton(
                        selected = exportType == 1,
                        onClick = { exportType = 1 }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Rentang Tanggal",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                
                if (exportType == 1) {
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedButton(
                        onClick = { showStartDatePicker = true },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = if (startDate.isEmpty()) "Pilih Tanggal Mulai" else "Mulai: $startDate",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    
                    OutlinedButton(
                        onClick = { showEndDatePicker = true },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = if (endDate.isEmpty()) "Pilih Tanggal Selesai" else "Selesai: $endDate",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (exportType == 0) {
                        onExport(null, null)
                    } else {
                        onExport(
                            startDate.takeIf { it.isNotEmpty() },
                            endDate.takeIf { it.isNotEmpty() }
                        )
                    }
                    onDismissRequest()
                },
                enabled = exportType == 0 || (startDate.isNotEmpty() && endDate.isNotEmpty())
            ) {
                Text("Unduh PDF")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text("Batal")
            }
        }
    )
}
