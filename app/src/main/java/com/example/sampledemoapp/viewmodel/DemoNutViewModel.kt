package com.example.sampledemoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sampledemoapp.domain.GetDemoNutsUseCase
import com.example.sampledemoapp.uidatamodel.DemoNutDataModel
import com.example.sampledemoapp.uidatamodel.DemoNutUIModel
import com.example.sampledemoapp.utility.DemoNutHelper.getFormattedText

class DemoNutViewModel(private val getDemoNutsUseCase: GetDemoNutsUseCase) : ViewModel() {
    private val _demoNutUIModel: MutableLiveData<List<DemoNutUIModel>> = MutableLiveData()
    val demoNutUIModel: LiveData<List<DemoNutUIModel>>
        get() = _demoNutUIModel

    fun updateDemoNutUiModel(dataModel: List<DemoNutDataModel>?) {
        _demoNutUIModel.value = dataModel?.map {
            DemoNutUIModel(
                id = it.id,
                name = it.name,
                desc = getFormattedText(it.city, it.country),
                imgURL = it.imgURL
            )
        }
    }

    fun getDemoNuts() = getDemoNutsUseCase.execute()

    override fun onCleared() {
        getDemoNutsUseCase.cancelJob()
        super.onCleared()
    }
}