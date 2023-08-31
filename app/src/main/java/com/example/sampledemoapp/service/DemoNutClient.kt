package com.example.sampledemoapp.service

import com.example.sampledemoapp.utility.DemoNutConstants.BASE_URL
import retrofit2.Retrofit

object DemoNutClient {
    fun getDemoNutRepoInstance(): DemoNutsApis = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(DemoNutClientProperties.gsonConverter)
        .client(DemoNutClientProperties.client)
        .build()
        .create(DemoNutsApis::class.java)
}