package com.reringuy.sync.presentation.reducer

import com.reringuy.sync.model.entity.BasicData
import com.reringuy.sync.utils.OperationHandler

class MainAppReducer() :
    Reducer<MainAppReducer.MainAppState, MainAppReducer.MainAppEvents, MainAppReducer.MainAppEffects> {
    sealed class MainAppEvents() : Reducer.ViewEvent {
        data class SetLoading(val loading: Boolean): MainAppEvents()
        data class SetBasicData(val basicData: List<BasicData>): MainAppEvents()
    }


    sealed class MainAppEffects() : Reducer.ViewEffect {
    }


    data class MainAppState(
        val loading: Boolean,
        val basicData: OperationHandler<List<BasicData>>
    ) : Reducer.ViewState {
        companion object {
            fun init() = MainAppState(
                loading = false,
                basicData = OperationHandler.Waiting
            )
        }
    }

    override fun reduce(
        previousState: MainAppState,
        event: MainAppEvents,
    ): Pair<MainAppState, MainAppEffects?> = when(event) {
        is MainAppEvents.SetLoading -> previousState.copy(loading = event.loading) to null
        is MainAppEvents.SetBasicData -> TODO()
    }
}