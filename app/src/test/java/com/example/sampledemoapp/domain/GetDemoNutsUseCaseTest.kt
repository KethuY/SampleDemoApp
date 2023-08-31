package com.example.sampledemoapp.domain

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.sampledemoapp.helper.MainDispatcherRule
import com.example.sampledemoapp.repository.DemoNutDispatcher
import com.example.sampledemoapp.repository.DemoNutRepositoryRepo
import com.example.sampledemoapp.repository.DemoNutResult
import com.example.sampledemoapp.repository.data.DemoNutResponse
import com.google.gson.Gson
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetDemoNutsUseCaseTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val repo = mockk<DemoNutRepositoryRepo>()
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainDispatcherRule()

    private lateinit var useCase: GetDemoNutsUseCase
    private val mockContext:Context= mockk()
    private lateinit var dispatcher: DemoNutDispatcher
    @ExperimentalCoroutinesApi
    private val testDispatcher = UnconfinedTestDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        dispatcher = DemoNutDispatcher()
        useCase = GetDemoNutsUseCase(repo, dispatcher)
    }

    @Test
    fun getDemoNutsSuccessTest() = runBlocking {
        val dempRes = mockContext.getMockDemoNutsResponse()
        coEvery { repo.getDemoNuts() } returns DemoNutResult.Success(dempRes)
        val data = useCase.execute()
        Assert.assertTrue(true)
    }

    @Test
    fun getDemoNutsFailTest() = runBlocking {
        val dempRes = mockContext.getMockDemoNutsFailResponse()
        coEvery { repo.getDemoNuts() } returns DemoNutResult.Success(dempRes)
        useCase.execute()
        Assert.assertTrue(true)
    }


    private fun Context.getMockDemoNutsResponse(): DemoNutResponse {
        return try {
            val jsonString = assets.open("demonuts.json")
                .bufferedReader()
                .use { it.readText() }
            Gson().fromJson(
                jsonString,
                DemoNutResponse::class.java
            )
        } catch (e: Exception) {
            DemoNutResponse()
        }
    }

    private fun Context.getMockDemoNutsFailResponse(): DemoNutResponse {
        return try {
            val jsonString = assets.open("demonuts1.json")
                .bufferedReader()
                .use { it.readText() }
            Gson().fromJson(
                jsonString,
                DemoNutResponse::class.java
            )
        } catch (e: Exception) {
            DemoNutResponse()
        }
    }
}