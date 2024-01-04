package com.example.data.exceptions

import java.io.IOException

class NoConnectivityException(message: String?) : IOException(message) {
    override val message: String?
        get() = super.message
}