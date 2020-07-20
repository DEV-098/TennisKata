package com.tenniskata.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tenniskata.data.repository.TennisRepository

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val repository: TennisRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}