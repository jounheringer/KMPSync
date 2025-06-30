package com.reringuy.sync.presentation.reducer

class MainAppReducer() :
    Reducer<MainAppReducer.MainAppState, MainAppReducer.MainAppEvents, MainAppReducer.MainAppEffects> {
    sealed class MainAppEvents() : Reducer.ViewEvent {
    }


    sealed class MainAppEffects() : Reducer.ViewEffect {
    }


    data class MainAppState(
        val loading: Boolean,
    ) : Reducer.ViewState {
        companion object {
            fun init() = MainAppState(
                loading = false
            )
        }
    }

    override fun reduce(
        previousState: MainAppState,
        event: MainAppEvents,
    ): Pair<MainAppState, MainAppEffects?> {
        TODO("Not yet implemented")
    }
}