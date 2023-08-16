package com.example.samplerickmorty.utils

sealed class NetworkResult<T>(val data: T? = null, val message: String? = null) {

    class Success<T>(data: T) : NetworkResult<T>(data)
    class Error<T>(message: String?, data: T? = null) : NetworkResult<T>(data, message)
<<<<<<< HEAD
    class Loading<T>(val isLoading: Boolean = true): NetworkResult<T>(null)

}


=======
    class Loading<T> : NetworkResult<T>()

}
>>>>>>> 475730e98537abdb31f66e1508b5f7de6bb64aeb
