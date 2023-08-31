package com.example.sampledemoapp.koin

import com.example.sampledemoapp.domain.GetDemoNutsUseCase
import com.example.sampledemoapp.repository.DemoNutDispatcher
import com.example.sampledemoapp.repository.DemoNutRepositoryRepo
import com.example.sampledemoapp.service.DemoNutClient
import com.example.sampledemoapp.viewmodel.DemoNutViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { GetDemoNutsUseCase(get(),get()) }
    single { getDemoNutRepo() }
    single { DemoNutDispatcher() }
    viewModel { DemoNutViewModel(get()) }
}

fun getDemoNutRepo() =
    DemoNutRepositoryRepo(DemoNutClient.getDemoNutRepoInstance())

