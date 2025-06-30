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
import com.reringuy.sync.presentation.components.Loading
import com.reringuy.sync.presentation.components.LoadingComponent
import com.reringuy.sync.presentation.reducer.MainAppReducer
import com.reringuy.sync.presentation.reducer.MainAppReducer.MainAppState
import com.reringuy.sync.presentation.viewmodel.MainAppViewmodel
import com.reringuy.sync.utils.OperationHandler
import com.reringuy.sync.utils.rememberFlowWithLifecycle
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
fun AppWrapper(
    viewmodel: MainAppViewmodel = koinInject<MainAppViewmodel>()
) {
    val state by viewmodel.state.collectAsState()
    val effects = rememberFlowWithLifecycle(viewmodel.effect)

    LaunchedEffect(effects) {
        effects.collect {
            when(it) {
                is MainAppReducer.MainAppEffects.ShowError -> {
                    println(it.message)
                }
            }
        }
    }

    LoadingComponent(state.loading) {
        App(state, viewmodel::generateRandomData)
    }
}
@Composable
@Preview
fun App(
    state: MainAppState,
    onAddNewData: () -> Unit
) {
    MaterialTheme {
        Column(
            modifier = Modifier.padding(16.dp, 32.dp).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppDataTable(state.basicData)
            Column(
                modifier = Modifier.fillMaxSize().padding(0.dp, 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                AppAddData(onAddNewData)
                AppSyncData()
            }
        }
    }
}

@Composable
fun AppDataTable(
    data: OperationHandler<MutableList<BasicData>>
) {
    when(data) {
        is OperationHandler.Failure -> {
            Text(text = data.message)
        }
        is OperationHandler.Success<*> -> {
            val dataList = (data as OperationHandler.Success<MutableList<BasicData>>).data
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
                        Text(text = "UID")
                        Text(text = "First Name")
                        Text(text = "Last Name")
                        Text(text = "Synced")
                    }
                }
                items(dataList) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(0.dp, 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "${it.id}")
                        Text(text = "${it.uid}")
                        Text(text = "${it.firstName}")
                        Text(text = "${it.lastName}")
                        Text(text = "${it.synced}")
                    }
                }
            }
        }
        else -> Loading()
    }

}

@Composable
fun AppAddData(onAddNewData: () -> Unit) {
    Button(onClick = onAddNewData) {
        Text(text = "Add Data")
    }
}

@Composable
fun AppSyncData() {
    Button(onClick = {}) {
        Text(text = "Sync Data")
    }
}