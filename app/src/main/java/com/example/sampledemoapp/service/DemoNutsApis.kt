package com.example.sampledemoapp.service

import com.example.sampledemoapp.repository.data.DemoNutResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface DemoNutsApis {
   @Headers("Content-Type: application/json")
   @GET("Demonuts/JsonTest/Tennis/json_parsing.php")
  suspend fun getDemoNuts(): Call<DemoNutResponse>
}
