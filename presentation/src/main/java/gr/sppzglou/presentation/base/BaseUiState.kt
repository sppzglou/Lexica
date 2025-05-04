package gr.sppzglou.presentation.base

import gr.sppzglou.domain.FailureWrapper
import gr.sppzglou.domain.ResultWrapper

interface BaseUiState<T> {
    val result: ResultWrapper<T>

    val isLoading: Boolean
        get() = result is ResultWrapper.Loading

    val isAwaiting: Boolean
        get() = result is ResultWrapper.Awaiting

    val isSuccess: Boolean
        get() = result is ResultWrapper.Success

    val isFailure: Boolean
        get() = result is ResultWrapper.Failure

    fun getDataOrNull(): T? = (result as? ResultWrapper.Success)?.data

    fun getErrorOrNull(): FailureWrapper? = (result as? ResultWrapper.Failure)?.error

    fun getErrorMessage(): String? = when (val err = getErrorOrNull()) {
        is FailureWrapper.Message -> err.errorMessage
        is FailureWrapper.Code -> err.errorCode.message
        else -> null
    }
}