package com.example.sampledemoapp.repository

import android.util.Log
import com.example.sampledemoapp.repository.data.DemoNutResponse
import com.example.sampledemoapp.service.DemoNutsApis
import com.example.sampledemoapp.utility.DemoNutHelper
import retrofit2.await

class DemoNutRepositoryRepo(private val demoNutsApis: DemoNutsApis) : DemoNutsRepository {
    override suspend fun getDemoNuts(): DemoNutResult<DemoNutResponse> {
        return try {
            val result = demoNutsApis.getDemoNuts().await()
            Log.d("Kethu", "getDemoNuts: $result")
            with(result) {
                if (DemoNutHelper.isApiSuccessful(status)) {
                    DemoNutResult.Success(this)
                } else {
                    DemoNutResult.Error(message, this)
                }
            }
        } catch (e: Exception) {
            DemoNutResult.Error(e.message)
        }
    }
}
