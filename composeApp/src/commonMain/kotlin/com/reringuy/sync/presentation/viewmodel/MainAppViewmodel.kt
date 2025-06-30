package com.reringuy.sync.presentation.viewmodel

import com.reringuy.sync.presentation.reducer.MainAppReducer
import org.koin.core.annotation.Factory

@Factory
class MainAppViewmodel :
    BaseViewModel<MainAppReducer.MainAppState, MainAppReducer.MainAppEvents, MainAppReducer.MainAppEffects>(
        initialState = MainAppReducer.MainAppState.init(),
        reducer = MainAppReducer()
    ) {


}