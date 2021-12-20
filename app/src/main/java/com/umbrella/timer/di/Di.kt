package com.umbrella.timer.di

import com.umbrella.timer.data.TimerRepositoryImpl
import com.umbrella.timer.domain.TimerRepository
import com.umbrella.timer.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Di {

    val viewModelModule = module {
        viewModel {
            MainViewModel(timerRepository = get())
        }
    }

    val repositoryModule = module {
        single<TimerRepository> {
            TimerRepositoryImpl()
        }
    }
}