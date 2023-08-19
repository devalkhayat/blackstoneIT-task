package com.blackstoneit.weatherforecasting.util.models

sealed class FinalResponse<out T> {
    data class Error(val exception: Exception) : FinalResponse<Nothing>()
    data class Success<T>(val data:T):FinalResponse<T>()
}
