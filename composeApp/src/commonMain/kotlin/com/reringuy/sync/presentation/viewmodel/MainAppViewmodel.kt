package com.reringuy.sync.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.reringuy.sync.domain.repositories.BasicDataRepository
import com.reringuy.sync.model.entity.BasicData
import com.reringuy.sync.presentation.reducer.MainAppReducer
import com.reringuy.sync.presentation.reducer.MainAppReducer.MainAppEvents
import com.reringuy.sync.presentation.reducer.MainAppReducer.MainAppEvents.SetBasicData
import com.reringuy.sync.presentation.reducer.MainAppReducer.MainAppEvents.SetRandomData
import com.reringuy.sync.presentation.reducer.MainAppReducer.MainAppState
import com.reringuy.sync.presentation.reducer.MainAppReducer.MainAppEffects
import com.reringuy.sync.utils.OperationHandler
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory

@Factory
class MainAppViewmodel(
    private val repository: BasicDataRepository,
) : BaseViewModel<MainAppState, MainAppEvents, MainAppEffects>(
    initialState = MainAppState.init(),
    reducer = MainAppReducer()
) {
    private val firstNames = listOf(
        "James",
        "Mary",
        "John",
        "Patricia",
        "Robert",
        "Jennifer",
        "Michael",
        "Linda"
    )

    private val lastNames = listOf(
        "Smith",
        "Johnson",
        "Williams",
        "Brown",
        "Jones",
        "Garcia",
        "Miller",
        "Davis"
    )
    
    init {
        loadBasicData()
    }

    private fun loadBasicData() {
        sendEvent(SetBasicData(OperationHandler.Loading))
        viewModelScope.launch {
            repository.getAllBasicData().collect {
                try {
                    sendEvent(SetBasicData(OperationHandler.Success(it.toMutableList())))
                } catch (e: Exception) {
                    sendEvent(SetBasicData(OperationHandler.Failure(e.message ?: "Unknown error")))
                }
            }
        }
    }

    fun generateRandomData() {
        sendEvent(SetRandomData(OperationHandler.Loading))
        viewModelScope.launch { 
            val basicData = BasicData(
                firstName = firstNames.random(),
                lastName = lastNames.random()
            )
            repository.saveData(basicData).collect {
                sendEvent(SetRandomData(it))
            }
        }
    }

}