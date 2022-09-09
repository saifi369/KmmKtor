package com.example.kmmktor.data

enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1),
    BadRequest(400),
    NotFound(404),
    Conflict(409),
    InternalServerError(500),
    Forbidden(403),
    NotAcceptable(406),
    ServiceUnavailable(503),
    UnAuthorized(401),
}

open class ResponseHandler {
    fun <T : Any> handleSuccess(data: T?): DataResponse<T> {
        return DataResponse.Success(data)
    }

    fun <T : Any> handleException(e: Exception): DataResponse<T> {

//        when(e){
//
//           is TimeoutException -> DataResponse.Message.Timeout
//            // is ConnectivityInterceptor.NoNetworkException -> Resource.CustomMessages.NoInternet
//            is UnknownHostException -> DataResponse.Message.ServerBusy
//            is ConnectException -> DataResponse.Message.NoInternet
//            is SocketTimeoutException -> DataResponse.Message.SocketTimeOutException
//            else -> DataResponse.CustomMessages.GenericMessage(getErrorMessage(throwable.cause))
//        }

        return DataResponse.Error(
            DataResponse.Message.GenericMessage(
                e.message ?: "Something went wrong"
            )
        )
    }

    fun <T : Any> handleException(message: DataResponse.Message): DataResponse<T> {


        return DataResponse.Error(message)
    }


    private fun getErrorMessage(cause: Throwable): String {
        return cause.message?.let {
            if (it.contains("api")) {
                "No Internet"
            } else {
                it.ifEmpty {
                    "No Internet"
                }
            }

        } ?: "No Internet"
    }


    fun <T : Any> handleException(statusCode: Int): DataResponse.Message {
        return getErrorType(statusCode)
    }
}

private fun getErrorType(code: Int): DataResponse.Message {
    return when (code) {
        ErrorCodes.SocketTimeOut.code -> DataResponse.Message.Timeout
        ErrorCodes.UnAuthorized.code -> DataResponse.Message.Unauthorized
        ErrorCodes.InternalServerError.code -> DataResponse.Message.InternalServerError
        ErrorCodes.BadRequest.code -> DataResponse.Message.BadRequest
        ErrorCodes.Conflict.code -> DataResponse.Message.Conflict
        ErrorCodes.InternalServerError.code -> DataResponse.Message.InternalServerError
        ErrorCodes.NotFound.code -> DataResponse.Message.NotFound
        ErrorCodes.NotAcceptable.code -> DataResponse.Message.NotAcceptable
        ErrorCodes.ServiceUnavailable.code -> DataResponse.Message.ServiceUnavailable
        ErrorCodes.Forbidden.code -> DataResponse.Message.Forbidden
        else -> DataResponse.Message.GenericMessage("An error Occurred with code $code")
    }
}


