package com.example.sampledemoapp.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

open class DemoNutDispatcher {
    open val main: CoroutineDispatcher by lazy { Dispatchers.Main }
    open val io: CoroutineDispatcher by lazy { Dispatchers.IO }
}