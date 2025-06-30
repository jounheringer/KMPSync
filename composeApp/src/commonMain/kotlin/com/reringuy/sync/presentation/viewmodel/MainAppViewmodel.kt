package com.reringuy.sync.presentation.viewmodel

import com.reringuy.sync.domain.repositories.BasicDataRepository
import com.reringuy.sync.presentation.reducer.MainAppReducer
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

}