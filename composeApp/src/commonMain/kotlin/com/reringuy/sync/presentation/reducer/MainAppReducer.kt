package com.reringuy.sync.presentation.reducer

import com.reringuy.sync.model.entity.BasicData
import com.reringuy.sync.presentation.reducer.MainAppReducer.MainAppEffects.*
import com.reringuy.sync.utils.OperationHandler

class MainAppReducer() :
    Reducer<MainAppReducer.MainAppState, MainAppReducer.MainAppEvents, MainAppReducer.MainAppEffects> {
    sealed class MainAppEvents() : Reducer.ViewEvent {
        data class SetBasicData(val basicData: OperationHandler<MutableList<BasicData>>) :
            MainAppEvents()

        data class SetRandomData(val randomData: OperationHandler<BasicData>) : MainAppEvents()
    }


    sealed class MainAppEffects() : Reducer.ViewEffect {
        data class ShowError(val message: String) : MainAppEffects()
    }


    data class MainAppState(
        val loading: Boolean,
        val basicData: OperationHandler<MutableList<BasicData>>,
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
    ): Pair<MainAppState, MainAppEffects?> {
        return when (event) {
            is MainAppEvents.SetBasicData -> {
                when (event.basicData) {
                    is OperationHandler.Failure ->
                        previousState.copy(
                            basicData = event.basicData,
                            loading = false
                        ) to ShowError(
                            event.basicData.message
                        )

                    is OperationHandler.Success<*> -> previousState.copy(
                        basicData = event.basicData,
                        loading = false
                    ) to null

                    else -> previousState.copy(loading = true) to null
                }
            }

            is MainAppEvents.SetRandomData -> {
                val previousData = previousState.basicData
                if (previousData !is OperationHandler.Success)
                    previousState.copy(basicData = previousData) to null
                else {
                    if (event.randomData is OperationHandler.Failure)
                        previousState.copy(basicData = previousData) to ShowError(
                            event.randomData.message
                        )
                    else if (event.randomData is OperationHandler.Success) {
                        val newData = (previousData.data + event.randomData.data).toMutableList()
                        previousState.copy(basicData = OperationHandler.Success(newData)) to null
                    } else
                        previousState.copy(basicData = previousData) to null
                }
            }
        }
    }
}