package com.reringuy.sync.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.reringuy.sync.model.entity.BasicData
import com.reringuy.sync.utils.formatToString
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.Clock

@Composable
@Preview
fun App() {
    MaterialTheme {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppDataTable()
            Column(
                modifier = Modifier.fillMaxSize().padding(0.dp, 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                AppAddData()
                AppSyncData()
            }
        }
    }
}

@Composable
fun AppDataTable() {
    val auxList = listOf(
        BasicData(
            id = 1,
            firstName = "joao",
            lastName = "ribeiro",
            createdAt = Clock.System.now(),
            synced = false
        ),
        BasicData(
            id = 3,
            firstName = "maria",
            lastName = "bruce",
            createdAt = Clock.System.now(),
            synced = false
        ),
        BasicData(
            id = 3,
            firstName = "gabriel",
            lastName = "san diego",
            createdAt = Clock.System.now(),
            synced = false
        ),
        BasicData(
            id = 3,
            firstName = "maria",
            lastName = "bruce",
            createdAt = Clock.System.now(),
            synced = false
        ),
        BasicData(
            id = 3,
            firstName = "gabriel",
            lastName = "san diego",
            createdAt = Clock.System.now(),
            synced = false
        ),
        BasicData(
            id = 3,
            firstName = "gabriel",
            lastName = "san diego",
            createdAt = Clock.System.now(),
            synced = false
        ),
        BasicData(
            id = 3,
            firstName = "maria",
            lastName = "bruce",
            createdAt = Clock.System.now(),
            synced = false
        ),
        BasicData(
            id = 3,
            firstName = "gabriel",
            lastName = "san diego",
            createdAt = Clock.System.now(),
            synced = false
        )
    )
    LazyColumn(
        modifier = Modifier.fillMaxWidth().heightIn(max = 300.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth().padding(0.dp, 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Id")
                Text(text = "First Name")
                Text(text = "Last Name")
                Text(text = "Date Created")
                Text(text = "Synced")
            }
        }
        items(auxList) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(0.dp, 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "${it.id}")
                Text(text = "${it.firstName}")
                Text(text = "${it.lastName}")
                Text(text = it.createdAt.formatToString())
                Text(text = "${it.synced}")
            }
        }
    }
}

@Composable
fun AppAddData() {
    Button(onClick = {}) {
        Text(text = "Add Data")
    }
}

@Composable
fun AppSyncData() {
    Button(onClick = {}) {
        Text(text = "Sync Data")
    }
}