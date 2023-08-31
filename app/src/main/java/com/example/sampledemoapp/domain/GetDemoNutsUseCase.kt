package com.example.sampledemoapp.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sampledemoapp.communicator.Cancellable
import com.example.sampledemoapp.repository.DemoNutDispatcher
import com.example.sampledemoapp.uidatamodel.DemoNutDataModel
import com.example.sampledemoapp.repository.data.DemoNutResponse
import com.example.sampledemoapp.repository.DemoNutRepositoryRepo
import com.example.sampledemoapp.repository.DemoNutResult
import com.example.sampledemoapp.repository.UseCase
import com.example.sampledemoapp.utility.DemoNutHelper.filterNullOrBlank
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class GetDemoNutsUseCase(
    private val repo: DemoNutRepositoryRepo,
    private val dispatcher: DemoNutDispatcher
) : UseCase<LiveData<DemoNutResult<List<DemoNutDataModel>?>>>, CoroutineScope , Cancellable {
    override val coroutineContext: CoroutineContext
        get() = dispatcher.io
    private var job: Job? = null

    override fun execute(): LiveData<DemoNutResult<List<DemoNutDataModel>?>> {
        val result = MutableLiveData<DemoNutResult<List<DemoNutDataModel>?>>()
        result.postValue(DemoNutResult.Loading())
        job = launch {
            val toPost = when (val response = repo.getDemoNuts()) {
                is DemoNutResult.Success -> {
                    val data = response.data?.getDemoNuts()
                    DemoNutResult.Success(data)
                }
                else -> {
                    DemoNutResult.Error(response.message)
                }
            }
            result.postValue(toPost)
        }
        return result
    }

    private fun DemoNutResponse.getDemoNuts() = data?.map {
        DemoNutDataModel(
            city = it.city.filterNullOrBlank(),
            country = it.country.filterNullOrBlank(),
            imgURL = it.imgURL.filterNullOrBlank(),
            id = it.id.filterNullOrBlank(),
            name = it.name.filterNullOrBlank()
        )
    }

    override fun cancelJob() {
        if (coroutineContext.isActive)
            job?.cancel()
    }
}