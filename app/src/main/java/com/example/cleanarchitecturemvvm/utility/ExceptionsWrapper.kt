package com.example.cleanarchitecturemvvm.utility

import android.content.Context
import com.example.cleanarchitecturemvvm.R
import com.example.data.exceptions.GeneralException
import com.example.data.exceptions.NoConnectivityException

class ExceptionsWrapper {
    companion object{
        fun checkResponseError(exception: Throwable, context: Context) : String{
            return when (exception){
                is NoConnectivityException -> context.getString(R.string.no_connection_error)
                is GeneralException -> context.getString(R.string.something_went_wrong)
                else -> context.getString(R.string.something_went_wrong)
            }
        }
    }
}