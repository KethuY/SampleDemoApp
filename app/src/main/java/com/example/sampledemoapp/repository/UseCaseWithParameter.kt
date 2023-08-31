package com.example.sampledemoapp.repository

internal interface UseCaseWithParameter<P, R> {
    fun execute(parameter: P): R
}

internal interface UseCase<R> {
    fun execute(): R
}


