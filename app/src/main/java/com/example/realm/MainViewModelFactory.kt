package com.example.realm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.realm.data.RealmRepository

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val realmRepository: RealmRepository
):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(realmRepository) as T
    }
}