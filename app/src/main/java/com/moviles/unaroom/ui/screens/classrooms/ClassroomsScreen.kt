package com.moviles.unaroom.ui.screens.classrooms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.People
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moviles.unaroom.data.Classroom
import com.moviles.unaroom.ui.theme.AppBorder
import com.moviles.unaroom.ui.theme.AppIconTint
import com.moviles.unaroom.ui.theme.AppPrimary
import com.moviles.unaroom.ui.theme.AppSecondaryText
import com.moviles.unaroom.ui.theme.AppSurfaceVariant
import com.moviles.unaroom.ui.theme.UnaRoomTheme

private val mockClassrooms = listOf(
    Classroom(name = "Aula-101", capacity = 30, location = "Building A"),
    Classroom(name = "Aula-102", capacity = 25, location = "Building A"),
    Classroom(name = "Aula-201", capacity = 45, location = "Building B"),
    Classroom(name = "Aula-204", capacity = 40, location = "Building B"),
    Classroom(name = "Aula-301", capacity = 60, location = "Building C"),
    Classroom(name = "Aula-305", capacity = 55, location = "Building C"),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassroomsScreen(
    onLogoutClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(
                            text = "Available Classrooms",
                            style = MaterialTheme.typography.headlineLarge,
                            color = AppPrimary
                        )
                    },
                    actions = {
                        IconButton(onClick = onLogoutClick) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.Logout,
                                contentDescription = "Logout",
                                tint = AppIconTint
                            )
                        }
                    }
                )
                HorizontalDivider(color = AppBorder)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
        ) {
            Text(
                text = "${mockClassrooms.size} rooms available",
                style = MaterialTheme.typography.bodyMedium,
                color = AppSecondaryText
            )
            Spacer(modifier = Modifier.height(16.dp))
            ClassroomList(classrooms = mockClassrooms)
        }
    }
}

@Composable
private fun ClassroomList(
    classrooms: List<Classroom>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        items(classrooms) { classroom ->
            ClassroomCard(classroom = classroom)
        }
    }
}

@Composable
private fun ClassroomCard(
    classroom: Classroom,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = AppSurfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = classroom.name,
                style = MaterialTheme.typography.titleMedium,
                color = AppPrimary
            )
            ClassroomInfoRow(
                icon = Icons.Outlined.LocationOn,
                text = classroom.location,
                contentDescription = "Location"
            )
            ClassroomInfoRow(
                icon = Icons.Outlined.People,
                text = "Capacity: ${classroom.capacity}",
                contentDescription = "Capacity"
            )
        }
    }
}

@Composable
private fun ClassroomInfoRow(
    icon: ImageVector,
    text: String,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = AppIconTint,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = AppSecondaryText
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ClassroomsScreenPreview() {
    UnaRoomTheme {
        ClassroomsScreen()
    }
}
