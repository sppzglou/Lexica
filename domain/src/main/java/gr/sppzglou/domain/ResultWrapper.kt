package gr.sppzglou.domain

sealed class ResultWrapper<out T> {
    data class Success<out T>(val data: T) : ResultWrapper<T>()
    data class Failure(val error: FailureWrapper) : ResultWrapper<Nothing>()
    data object Loading : ResultWrapper<Nothing>()
    data object Awaiting : ResultWrapper<Nothing>()

    companion object {
        fun <T, V> ResultWrapper<T>.dataConverter(block: (T) -> V): ResultWrapper<V> = when (this) {
            is Success -> Success(block(data))
            is Failure -> Failure(error)
            is Loading -> Loading
            is Awaiting -> Awaiting
        }
    }
}

fun <T> errorMessage(error: String): ResultWrapper<T> =
    ResultWrapper.Failure(FailureWrapper.Message(error))

fun <T> errorCode(error: ErrorCodes): ResultWrapper<T> =
    ResultWrapper.Failure(FailureWrapper.Code(error))

fun <T> inProgress(): ResultWrapper<T> = ResultWrapper.Loading

fun <T> success(data: T): ResultWrapper<T> = ResultWrapper.Success(data)

sealed class FailureWrapper {
    data class Code(val errorCode: ErrorCodes) : FailureWrapper()
    data class Message(val errorMessage: String) : FailureWrapper()
}

enum class ErrorCodes(val message: String) {
    NoInternet("No Internet"),
    NotFound("Not Found")
}
