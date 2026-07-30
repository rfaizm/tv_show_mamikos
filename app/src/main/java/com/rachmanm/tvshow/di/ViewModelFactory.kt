package com.rachmanm.tvshow.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rachmanm.tvshow.domain.usecase.GetTvShowListUseCase
import com.rachmanm.tvshow.ui.screen.list.ListScreenViewModel

class ViewModelFactory private constructor(
    private val getTvShowUseCase: GetTvShowListUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ListScreenViewModel::class.java) -> {
                ListScreenViewModel(getTvShowUseCase) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }

    companion object {

        @Volatile
        private var instance: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideGetTvShowUseCase(context)
                )
            }.also { instance = it }
    }
}