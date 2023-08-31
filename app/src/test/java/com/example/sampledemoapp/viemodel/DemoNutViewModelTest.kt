package com.example.sampledemoapp.viemodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.sampledemoapp.domain.GetDemoNutsUseCase
import com.example.sampledemoapp.repository.DemoNutResult
import com.example.sampledemoapp.uidatamodel.DemoNutDataModel
import com.example.sampledemoapp.viewmodel.DemoNutViewModel
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.spyk
import io.mockk.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DemoNutViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private lateinit var mDemoNutViewModel: DemoNutViewModel
    private val useCase: GetDemoNutsUseCase = mockk()
    private val mockContext: Context = mockk()

    @Before
    fun setup() {
        mDemoNutViewModel = DemoNutViewModel(useCase)
    }

    @Test
    fun updateDemoNutUiModelTest() {
        mDemoNutViewModel.updateDemoNutUiModel(null)
        Assert.assertEquals(0, mDemoNutViewModel.demoNutUIModel.value?.size)
        mDemoNutViewModel.updateDemoNutUiModel(arrayListOf())
        Assert.assertEquals(0, mDemoNutViewModel.demoNutUIModel.value?.size)
        mDemoNutViewModel.updateDemoNutUiModel(
            arrayListOf(
                DemoNutDataModel(
                    id = "1233",
                    city = "Srikalam",
                    country = "Country",
                    imgURL = "dsjfjd",
                    name = "Satya"
                )
            )
        )
        Assert.assertEquals(1, mDemoNutViewModel.demoNutUIModel.value?.size)
        val objectData = mDemoNutViewModel.demoNutUIModel.value?.get(0)
        Assert.assertEquals("1233", objectData?.id)
        Assert.assertEquals("Srikalam, Country", objectData?.desc)
        Assert.assertEquals("dsjfjd", objectData?.imgURL)
        Assert.assertEquals("Satya", objectData?.name)
    }

    @Test
    fun getDemoNutsTest(){
        val mockObserver = spyk(object: Observer<DemoNutResult<List<DemoNutDataModel>?>> {
            override fun onChanged(p0: DemoNutResult<List<DemoNutDataModel>?>) {
            }
        })
        // Given
        val useCaseResult = MutableLiveData<DemoNutResult<List<DemoNutDataModel>?>>()
        useCaseResult.postValue(DemoNutResult.Loading())
        mDemoNutViewModel.getDemoNuts().observeForever(mockObserver)
        every { useCase.execute() } returns useCaseResult
        //When
        mDemoNutViewModel.getDemoNuts()
        //Then
        val slot = slot<DemoNutResult<List<DemoNutDataModel>?>>()
        verify { mockObserver.onChanged(capture(slot)) }
        verify { useCase.execute() }
    }
}