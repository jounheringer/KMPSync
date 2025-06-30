package com.reringuy.sync.presentation.reducer

interface Reducer<State : Reducer.ViewState, Event : Reducer.ViewEvent, Effect : Reducer.ViewEffect> {
    interface ViewState
    interface ViewEvent
    interface ViewEffect

    fun reduce(previousState: State, event: Event): Pair<State, Effect?>
}