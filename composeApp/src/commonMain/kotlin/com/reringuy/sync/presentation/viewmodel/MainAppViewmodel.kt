package com.reringuy.sync.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.reringuy.sync.domain.repositories.BasicDataRepository
import com.reringuy.sync.presentation.reducer.MainAppReducer
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory

@Factory
class MainAppViewmodel(
    private val repository: BasicDataRepository,
) : BaseViewModel<MainAppReducer.MainAppState, MainAppReducer.MainAppEvents, MainAppReducer.MainAppEffects>(
    initialState = MainAppReducer.MainAppState.init(),
    reducer = MainAppReducer()
) {

    init {

    }

    private fun loadBasicData() {
        sendEvent(MainAppReducer.MainAppEvents.SetLoading(true))
        viewModelScope.launch {
            repository.getAllBasicData().collect {
//                sendEvent()
            }
            sendEvent(MainAppReducer.MainAppEvents.SetLoading(true))
        }
    }

}