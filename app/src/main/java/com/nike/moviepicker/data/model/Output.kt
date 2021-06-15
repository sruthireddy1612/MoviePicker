package com.nike.moviepicker.data.model

sealed class Output<out R> {

    data class Success<out T>(val data: T) : Output<T>()
    data class Error(
        val errorCode: Int,
        val errorMsg: String? = null,
        val exception: Throwable? = null
    ) :
        Output<Nothing>()

    object Loading : Output<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[errorCode=$errorCode][errorMsg=$errorMsg][exception=$exception]"
            Loading -> "Loading"
        }
    }
}

val Output<*>.succeeded
    get() = this is Output.Success && data != null