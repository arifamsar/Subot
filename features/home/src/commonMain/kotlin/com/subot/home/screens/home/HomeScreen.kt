package com.subot.home.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
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
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Badge
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.subot.core.ui.components.AppPrimaryButton
import com.subot.core.ui.components.AppTextButton

// --- Multiplatform Resources (Simulated for this context) ---
object Res {
    object String {
        const val mitra_label = "Mitra"
        const val dasbor_title = "Dasbor Sekolah"
        const val welcome_title = "Selamat Datang,"
        const val representative_label = "Perwakilan dari Sekolah:"
        const val empty_schedule = "Belum ada jadwal pertemuan terdekat"
        const val empty_meetings = "Belum ada sisa pertemuan"
        const val schedule_header = "Ringkasan Jadwal"
        const val no_schedule_details = "Tidak ada detail jadwal saat ini."
        const val trainer_column = "TRAINER"
        const val time_column = "WAKTU"
        const val view_more_details = "Lihat Semua"
        const val view_more_payment = "Lihat Detail Pembayaran Lengkap..."
        const val all_paid = "Semua tagihan Anda telah lunas."
        const val total_payment = "Total Tagihan Berjalan"
        const val upcoming_meeting_card = "Pertemuan Terdekat"
        const val remaining_meetings_card = "Sisa Pertemuan"
        const val payment_overview_card = "Ringkasan Pembayaran"
    }
}

// --- Domain Models ---
data class Meeting(
    val trainerName: String,
    val time: String,
    val description: String
)

data class DashboardData(
    val userName: String,
    val schoolName: String,
    val upcomingMeeting: Meeting?,
    val remainingMeetingsCount: Int,
    val hasUnpaidBills: Boolean,
    val totalUnpaidAmount: Long
)

// --- Color Palette ---
val UnpaidAlert = Color(0xFFB3261E) 
val PaidSuccess = Color(0xFF2E7D32)

// --- Sample Data ---
val sampleDashboardWithMeetings = DashboardData(
    userName = "Andi Prasetyo",
    schoolName = "SUKAROBOT ACADEMY",
    upcomingMeeting = Meeting("Coach Budi", "Senin, 10:00 WIB", "Robotika Lanjutan"),
    remainingMeetingsCount = 12,
    hasUnpaidBills = false,
    totalUnpaidAmount = 0
)

val sampleDashboardUnpaid = DashboardData(
    userName = "Andi Prasetyo",
    schoolName = "SUKAROBOT ACADEMY",
    upcomingMeeting = Meeting("Coach Budi", "Senin, 10:00 WIB", "Robotika Lanjutan"),
    remainingMeetingsCount = 12,
    hasUnpaidBills = true,
    totalUnpaidAmount = 1500000
)

// --- Composables ---

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(dashboardData: DashboardData = sampleDashboardWithMeetings) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    Column {
                        Text(
                            text = Res.String.welcome_title,
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = dashboardData.userName,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* Handle Notifications */ }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notifications")
                    }
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    scrolledContainerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                ),
            )
        },
        contentWindowInsets = WindowInsets(0.dp)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            SchoolProfileSection(dashboardData.schoolName)
            
            MetricsSection(dashboardData)

            SectionHeader(
                title = Res.String.schedule_header,
                onActionClick = { /* Navigate to Schedule */ }
            )
            ScheduleCard(dashboardData.upcomingMeeting)

            SectionHeader(
                title = Res.String.payment_overview_card,
                onActionClick = { /* Navigate to Payments */ }
            )
            PaymentCard(dashboardData)
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun SectionHeader(
    title: String,
    onActionClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        AppTextButton(onClick = onActionClick, text = Res.String.view_more_details)
    }
}

@Composable
fun SchoolProfileSection(schoolName: String) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.secondaryContainer,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.1f),
                modifier = Modifier.size(48.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Business,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "Sekolah Terdaftar",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
                )
                Text(
                    text = schoolName,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
    }
}

@Composable
fun MetricsSection(dashboardData: DashboardData) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        MetricCardM3(
            title = Res.String.remaining_meetings_card,
            value = dashboardData.remainingMeetingsCount.toString(),
            unit = "Sesi",
            icon = Icons.Default.Schedule,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.weight(1f)
        )
        MetricCardM3(
            title = "Status Tagihan",
            value = if (dashboardData.hasUnpaidBills) "Belum Lunas" else "Lunas",
            unit = if (dashboardData.hasUnpaidBills) "Menunggu" else "Terkendali",
            icon = Icons.Default.Payments,
            containerColor = if (dashboardData.hasUnpaidBills) 
                MaterialTheme.colorScheme.errorContainer 
            else 
                MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = if (dashboardData.hasUnpaidBills) 
                MaterialTheme.colorScheme.onErrorContainer 
            else 
                MaterialTheme.colorScheme.onTertiaryContainer,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun MetricCardM3(
    title: String,
    value: String,
    unit: String,
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
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(24.dp))
            Column {
                Text(
                    text = value,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = unit,
                    style = MaterialTheme.typography.labelSmall
                )
            }
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun ScheduleCard(upcomingMeeting: Meeting?) {
    OutlinedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        if (upcomingMeeting == null) {
            Box(
                modifier = Modifier.padding(24.dp).fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = Res.String.no_schedule_details,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                        modifier = Modifier.size(40.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = upcomingMeeting.trainerName.firstOrNull()?.toString() ?: "",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = upcomingMeeting.trainerName,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = upcomingMeeting.description,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 16.dp),
                    color = MaterialTheme.colorScheme.outlineVariant
                )
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Schedule,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = upcomingMeeting.time,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    AssistChip(
                        onClick = { },
                        label = { Text("Siap") },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Business,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun PaymentCard(dashboardData: DashboardData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Text(
                        text = Res.String.total_payment,
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = if (dashboardData.hasUnpaidBills) 
                            "Rp ${dashboardData.totalUnpaidAmount}" 
                        else "Rp 0",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = if (dashboardData.hasUnpaidBills) 
                            MaterialTheme.colorScheme.error 
                        else 
                            MaterialTheme.colorScheme.primary
                    )
                }
                if (dashboardData.hasUnpaidBills) {
                    Badge(
                        containerColor = MaterialTheme.colorScheme.errorContainer,
                        contentColor = MaterialTheme.colorScheme.onErrorContainer,
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        Text("Segera Bayar", modifier = Modifier.padding(4.dp))
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))

            AppPrimaryButton(
                onClick = { /* Handle Payment */ },
                modifier = Modifier.fillMaxWidth(),
                enabled = dashboardData.hasUnpaidBills,
                text = "Bayar Sekarang",
                icon = Icons.Default.Payments,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen(sampleDashboardWithMeetings)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenUnpaidPreview() {
    MaterialTheme {
        HomeScreen(sampleDashboardUnpaid)
    }
}
