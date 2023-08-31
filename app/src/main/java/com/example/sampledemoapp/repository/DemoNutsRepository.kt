package com.example.sampledemoapp.repository

import com.example.sampledemoapp.repository.data.DemoNutResponse

interface DemoNutsRepository {
  suspend fun getDemoNuts():DemoNutResult<DemoNutResponse>
}
