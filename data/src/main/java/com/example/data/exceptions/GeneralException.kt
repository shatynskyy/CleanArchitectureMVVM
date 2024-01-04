package com.example.data.exceptions

import java.io.IOException

class GeneralException(message: String?): IOException(message) {
    override val message: String?
        get() = super.message
}