package com.subot.profile.screens.members

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.subot.core.domain.model.Member
import com.subot.core.ui.components.AppCircleImage
import com.subot.core.ui.components.AppPullToRefresh
import com.subot.core.ui.components.AppScaffold
import com.subot.core.ui.components.AppTextField
import com.subot.core.ui.components.ShimmerBox
import com.subot.core.ui.components.ShimmerCircle
import com.subot.core.ui.components.icons.ArrowLeft
import com.subot.core.ui.components.icons.Hicon
import com.subot.core.ui.components.icons.ProfileCircleFilled

@Composable
fun MembersScreen(
    viewModel: MembersViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var searchQuery by remember { mutableStateOf(uiState.searchQuery) }

    AppScaffold(
        modifier = modifier,
        topBarTitle = "Daftar Anggota",
        navigationIcon = Hicon.ArrowLeft,
        onNavigationClick = onBack
    ) { innerPadding ->
        AppPullToRefresh(
            isRefreshing = uiState.refreshing,
            onRefresh = { viewModel.onEvent(MembersEvent.RefreshMembers) },
            modifier = Modifier.padding(innerPadding).fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Search bar
                SearchBar(
                    searchQuery = searchQuery,
                    onSearchChanged = { newQuery ->
                        searchQuery = newQuery
                        viewModel.onEvent(MembersEvent.SearchMembers(newQuery))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )

                // Error message
                if (uiState.error != null) {
                    ErrorBanner(
                        message = uiState.error!!,
                        onDismiss = { viewModel.onEvent(MembersEvent.ClearError) },
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }

                // Members list or loading
                if (uiState.isLoading && uiState.members.isEmpty()) {
                    MembersLoadingSkeleton(modifier = Modifier.fillMaxSize())
                } else if (uiState.members.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(72.dp)
                                    .background(
                                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                        shape = RoundedCornerShape(20.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                                    modifier = Modifier.size(36.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Anggota Tidak Ditemukan",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Coba gunakan kata kunci pencarian lain atau periksa kembali ejaan Anda.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                } else {
                    MembersLazyList(
                        items = uiState.members,
                        isLoadingMore = uiState.refreshing,
                        hasMore = uiState.hasMore,
                        onLoadMore = { viewModel.onEvent(MembersEvent.LoadMore) },
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
private fun MembersLoadingSkeleton(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(6) {
            MemberCardSkeleton()
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
private fun SearchBar(
    searchQuery: String,
    onSearchChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    AppTextField(
        value = searchQuery,
        onValueChange = onSearchChanged,
        label = "",
        placeholder = "Cari nama atau NIS",
        leadingIcon = Icons.Default.Search,
        trailingIcon = if (searchQuery.isNotEmpty()) Icons.Default.Close else null,
        onTrailingIconClick = { onSearchChanged("") },
        modifier = modifier
    )
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
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Tutup",
                    tint = MaterialTheme.colorScheme.onErrorContainer
                )
            }
        }
    }
}

@Composable
private fun MembersLazyList(
    items: List<Member>,
    isLoadingMore: Boolean,
    hasMore: Boolean,
    onLoadMore: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(items, key = { it.id }) { member ->
            MemberCard(member = member)
            Spacer(modifier = Modifier.height(12.dp))
        }

        if (hasMore) {
            item {
                if (isLoadingMore) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Memuat lebih banyak...",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    onLoadMore()
                }
            }
        }
    }
}

@Composable
private fun MemberCard(
    member: Member,
    modifier: Modifier = Modifier
) {
    val kelasText = member.kelas.ifBlank { "-" }
    val isActive = member.statusSiswa.equals("Aktif", ignoreCase = true)
    val statusColor = if (isActive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
    val statusBg = if (isActive) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f) else MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.5f)

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile image
            AppCircleImage(
                url = member.profileImageUrl,
                contentDescription = member.namaLengkap,
                size = 64.dp,
                fallbackIcon = Hicon.ProfileCircleFilled,
                fallbackIconTint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Member info
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = member.namaLengkap,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "NIS: ${member.nis}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Kelas: $kelasText",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))
                
                // Status chip badge
                Box(
                    modifier = Modifier
                        .background(
                            color = statusBg,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = member.statusSiswa,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = statusColor
                    )
                }
            }
        }
    }
}

@Composable
private fun MemberCardSkeleton(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ShimmerCircle(size = 64.dp)
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                ShimmerBox(width = 180.dp, height = 20.dp)
                Spacer(modifier = Modifier.height(8.dp))
                ShimmerBox(width = 130.dp, height = 14.dp)
                Spacer(modifier = Modifier.height(4.dp))
                ShimmerBox(width = 100.dp, height = 14.dp)
                Spacer(modifier = Modifier.height(8.dp))
                ShimmerBox(width = 80.dp, height = 16.dp)
            }
        }
    }
}
