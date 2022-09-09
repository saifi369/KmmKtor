package com.example.kmmktor.data

sealed class DataResponse<T>(
    val data: T? = null,
    val error: Message = Message.GenericMessage("Something Went Wrong")
) {
    class Success<T>(data: T?) : DataResponse<T>(data)
    class Loading<T> : DataResponse<T>()
    class Error<T>(message: Message) : DataResponse<T>(error = message)
    sealed class Message(val message: String = "") {
        object Timeout : Message()
        object ServerResponseException : Message()
        object ClientRequestException : Message()
        object RedirectResponseException : Message()
        object EmptyData : Message()
        object ServerBusy : Message()
        object HttpException : Message()
        object SocketTimeOutException : Message()
        object NoInternet : Message()
        object Unauthorized : Message()
        object InternalServerError : Message()
        object BadRequest : Message()
        object Conflict : Message()
        object NotFound : Message()
        object NotAcceptable : Message()
        object ServiceUnavailable : Message()
        object Forbidden : Message()
        data class GenericMessage(val error: String) : Message(message = error)
    }
}

