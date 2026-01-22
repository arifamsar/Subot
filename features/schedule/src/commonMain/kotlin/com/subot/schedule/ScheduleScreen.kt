package com.subot.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.subot.core.common.utils.durationUntil
import com.subot.core.common.utils.getCurrentWeekDates
import com.subot.core.common.utils.nowAsLocalDate
import com.subot.core.common.utils.to12HourFormat
import com.subot.core.common.utils.toDayName
import com.subot.core.common.utils.toDayOfMonthString
import com.subot.core.common.utils.toDurationString
import com.subot.core.common.utils.toMonthYearString
import com.subot.core.ui.components.AppPullToRefresh
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlin.time.Clock

data class DateItem(
    val date: LocalDate,
    val isSelected: Boolean = false
)

data class ScheduleEvent(
    val title: String,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val color: Color
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreen(
    modifier: Modifier = Modifier
) {
    var isRefreshing by remember { mutableStateOf(false) }
    var selectedDateIndex by remember { mutableStateOf(2) }
    
    // Get current date and week dates
    val currentDate = remember { Clock.System.nowAsLocalDate() }
    val weekDates = remember { currentDate.getCurrentWeekDates() }

    val dates = remember(weekDates) {
        weekDates.mapIndexed { index, date ->
            DateItem(
                date = date,
                isSelected = index == selectedDateIndex
            )
        }
    }

    val events = remember {
        listOf(
            ScheduleEvent(
                title = "Team Meeting",
                startTime = LocalTime(9, 0),
                endTime = LocalTime(10, 0),
                color = Color(0xFF1565C0)
            ),
            ScheduleEvent(
                title = "Project Review",
                startTime = LocalTime(11, 0),
                endTime = LocalTime(11, 30),
                color = Color(0xFF2E7D32)
            ),
            ScheduleEvent(
                title = "Lunch Break",
                startTime = LocalTime(12, 30),
                endTime = LocalTime(13, 30),
                color = Color(0xFFED6C02)
            ),
            ScheduleEvent(
                title = "Client Call",
                startTime = LocalTime(15, 0),
                endTime = LocalTime(15, 45),
                color = Color(0xFF9C27B0)
            )
        )
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        // Content
        AppPullToRefresh(
            isRefreshing = isRefreshing,
            onRefresh = {
                isRefreshing = true
                isRefreshing = false
            }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                // Header
                item {
                    Text(
                        text = "Schedule",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                }
                // Date info
                item {
                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                        Text(
                            text = currentDate.toMonthYearString(),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }

                // Date selector
                item {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(dates.size) { index ->
                            DateCard(
                                date = dates[index],
                                isSelected = index == selectedDateIndex,
                                onClick = { selectedDateIndex = index }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }

                // Today's schedule header
                item {
                    Text(
                        text = "Today's Schedule",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                // Events
                items(events) { event ->
                    ScheduleEventCard(
                        event = event,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun DateCard(
    date: DateItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.size(width = 56.dp, height = 72.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) 
                MaterialTheme.colorScheme.primary 
            else 
                MaterialTheme.colorScheme.surfaceContainerLow
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = date.date.toDayName().take(3),
                style = MaterialTheme.typography.labelSmall,
                color = if (isSelected) 
                    MaterialTheme.colorScheme.onPrimary 
                else 
                    MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = date.date.toDayOfMonthString(),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) 
                    MaterialTheme.colorScheme.onPrimary 
                else 
                    MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun ScheduleEventCard(
    event: ScheduleEvent,
    modifier: Modifier = Modifier
) {
    val timeRange = "${event.startTime.to12HourFormat()} - ${event.endTime.to12HourFormat()}"
    val duration = event.startTime.durationUntil(event.endTime).toDurationString()

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Color indicator
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(48.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(event.color)
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = event.title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "$timeRange • $duration",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}