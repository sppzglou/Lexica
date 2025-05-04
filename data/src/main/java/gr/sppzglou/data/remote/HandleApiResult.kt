package gr.sppzglou.data.remote

import gr.sppzglou.data.di.NoInternetException
import gr.sppzglou.domain.ErrorCodes
import gr.sppzglou.domain.ResultWrapper
import gr.sppzglou.domain.errorCode
import gr.sppzglou.domain.errorMessage
import gr.sppzglou.domain.success
import retrofit2.HttpException

inline fun <reified T> handleApiRes(apiCall: () -> T): ResultWrapper<T> {
    return try {
        val res = apiCall()
        success(res)
    } catch (e: NoInternetException) {
        errorCode(ErrorCodes.NoInternet)
    } catch (e: Exception) {
        if (e is HttpException && e.code() == 404) errorCode(ErrorCodes.NotFound)
        else errorMessage(e.message ?: "Something went wrong!")
    }
}