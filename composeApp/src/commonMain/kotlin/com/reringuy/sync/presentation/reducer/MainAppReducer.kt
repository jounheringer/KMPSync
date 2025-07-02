package com.reringuy.sync.presentation.reducer

import com.reringuy.sync.model.entity.BasicData
import com.reringuy.sync.presentation.reducer.MainAppReducer.MainAppEffects.*
import com.reringuy.sync.utils.OperationHandler
import com.reringuy.sync.utils.OperationHandler.*

class MainAppReducer() :
    Reducer<MainAppReducer.MainAppState, MainAppReducer.MainAppEvents, MainAppReducer.MainAppEffects> {
    sealed class MainAppEvents() : Reducer.ViewEvent {
        data class SetBasicData(val basicData: OperationHandler<List<BasicData>>) :
            MainAppEvents()

        data class SetRandomData(val randomData: OperationHandler<BasicData>) : MainAppEvents()
        data class SyncData(val randomData: OperationHandler<List<BasicData>>) : MainAppEvents()

        data object ResetBasicData : MainAppEvents()
    }


    sealed class MainAppEffects() : Reducer.ViewEffect {
        data class ShowError(val message: String) : MainAppEffects()
    }


    data class MainAppState(
        val loading: Boolean,
        val basicData: OperationHandler<List<BasicData>>,
    ) : Reducer.ViewState {
        companion object {
            fun init() = MainAppState(
                loading = false,
                basicData = Waiting
            )
        }
    }

    override fun reduce(
        previousState: MainAppState,
        event: MainAppEvents,
    ): Pair<MainAppState, MainAppEffects?> {
        return when (event) {
            is MainAppEvents.ResetBasicData -> previousState.copy(basicData = Waiting) to null
            is MainAppEvents.SetBasicData -> {
                when (event.basicData) {
                    is Failure ->
                        previousState.copy(
                            basicData = event.basicData,
                            loading = false
                        ) to ShowError(
                            event.basicData.message
                        )

                    is Success -> {
                        if (previousState.basicData is Success) {
                            val newDataList = previousState.basicData.data + event.basicData.data
                            previousState.copy(
                                basicData = Success(newDataList),
                                loading = false
                            ) to null
                        } else {
                            previousState.copy(
                                basicData = event.basicData,
                                loading = false
                            ) to null
                        }
                    }

                    else -> previousState.copy(loading = true) to null
                }
            }

            is MainAppEvents.SetRandomData -> {
                val previousData = previousState.basicData
                if (previousData !is Success)
                    previousState.copy(basicData = previousData) to null
                else {
                    if (event.randomData is Failure)
                        previousState.copy(basicData = previousData) to ShowError(
                            event.randomData.message
                        )
                    else if (event.randomData is Success) {
                        val newData = (previousData.data + event.randomData.data)
                        previousState.copy(basicData = Success(newData)) to null
                    } else
                        previousState.copy(basicData = previousData) to null
                }
            }

            is MainAppEvents.SyncData -> {
                when (event.randomData) {
                    is Failure -> previousState.copy(loading = false) to ShowError(event.randomData.message)
                    is Success<*> -> previousState.copy(
                        loading = false,
                        basicData = event.randomData
                    ) to null

                    else -> previousState.copy(loading = true) to null
                }
            }
        }
    }
}