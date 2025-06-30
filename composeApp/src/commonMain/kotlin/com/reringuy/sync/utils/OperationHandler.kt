package com.reringuy.sync.utils

sealed class OperationHandler<out T> {

    data object Waiting:OperationHandler<Nothing>()

    data object Loading:OperationHandler<Nothing>()

    data class Success<out T>(val data:T):OperationHandler<T>()

    data class Failure(val message:String):OperationHandler<Nothing>()
}