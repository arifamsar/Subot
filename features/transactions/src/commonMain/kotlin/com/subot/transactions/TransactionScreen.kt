package com.subot.transactions

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionScreen(
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Mitra",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "Pembayaran",
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
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Summary Cards Grid
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                PaymentSummaryCard(
                    title = "Sudah Terbayar",
                    amount = "Rp 0",
                    icon = Icons.Default.History,
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.weight(1f)
                )
                PaymentSummaryCard(
                    title = "Belum Terselesaikan",
                    amount = "Rp 0",
                    icon = Icons.Default.AccountBalanceWallet,
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer,
                    modifier = Modifier.weight(1f)
                )
            }

            // Main Content Section
            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Pembayaran yang Dibayar",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    EmptyStatePlaceholder(text = "Tidak ada tagihan yang perlu dibayar saat ini.")
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Group, 
                            contentDescription = null, 
                            modifier = Modifier.size(20.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Pilih Siswa yang Dibayar",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    EmptyStatePlaceholder(text = "Tidak ada siswa yang perlu dibayar untuk SPP ini.")
                }
            }

            // Payment Summary Hint
            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Ringkasan Pembayaran",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Pilih salah satu tagihan pada daftar untuk melihat ringkasan pembayaran.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Finalize Payment Section
            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Selesaikan Pembayaran",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Alert Info
                    Surface(
                        color = Color(0xFFFFF9C4).copy(alpha = 0.5f),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, Color(0xFFFFF176))
                    ) {
                        Text(
                            text = "Tidak ada tagihan yang perlu dibayar saat ini. Pilih metode akan aktif ketika tagihan tersedia.",
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFFF57F17)
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    Text(
                        text = "Metode Pembayaran",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    PaymentMethodItem(name = "BCA Virtual Account", provider = "Midtrans", icon = Icons.Default.FlashOn)
                    PaymentMethodItem(name = "BNI Virtual Account", provider = "Midtrans", icon = Icons.Default.FlashOn)
                    PaymentMethodItem(name = "GoPay", provider = "Midtrans", icon = Icons.Default.FlashOn)
                    PaymentMethodItem(name = "Midtrans Payment Link", provider = "Midtrans", icon = Icons.Default.FlashOn)
                    PaymentMethodItem(name = "Bank Transfer", provider = "Transfer / Manual", icon = Icons.Default.AccountBalance)
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun PaymentSummaryCard(
    title: String,
    amount: String,
    icon: ImageVector,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = containerColor, contentColor = contentColor),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = title, style = MaterialTheme.typography.labelMedium)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = amount, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                }
                Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(24.dp))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Lihat Rincian...", style = MaterialTheme.typography.labelSmall, textDecoration = null)
        }
    }
}

@Composable
fun EmptyStatePlaceholder(text: String) {
    OutlinedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)),
        colors = CardDefaults.outlinedCardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.1f))
    ) {
        Box(
            modifier = Modifier.padding(24.dp).fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun PaymentMethodItem(name: String, provider: String, icon: ImageVector) {
    OutlinedCard(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                modifier = Modifier.size(40.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = name, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
                Text(text = provider, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionScreenPreview() {
    MaterialTheme {
        TransactionScreen()
    }
}
