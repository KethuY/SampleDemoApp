package com.example.sampledemoapp.utility

import com.example.sampledemoapp.utility.DemoNutConstants.STATUS_TRUE

object DemoNutHelper {
    fun isApiSuccessful(status: String) = STATUS_TRUE == status

    fun String?.filterNullOrBlank() = if (isNullOrBlank() || isNullOrEmpty()) ""
    else this

    fun getFormattedText(value1: String?, value2: String?) = if (value1.isNullOrBlank()) {
        value2.filterNullOrBlank()
    } else {
        if (value2.isNullOrBlank()) value1
        else "$value1, $value2"
    }
}