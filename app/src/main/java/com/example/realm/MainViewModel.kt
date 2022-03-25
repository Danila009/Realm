package com.example.realm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realm.data.RealmRepository
import com.example.realm.data.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val realmRepository: RealmRepository
):ViewModel() {

    private val _responseUser:MutableStateFlow<List<User>> = MutableStateFlow(listOf())
    val responseUser:StateFlow<List<User>> = _responseUser.asStateFlow()

    fun addUser(user: User){
        viewModelScope.launch {
            realmRepository.addUser(user)
        }
    }

    fun allUser(){
        viewModelScope.launch {
            _responseUser.value = realmRepository.allUser()
        }
    }

    fun updateUser(username:String, idUser:Int){
        viewModelScope.launch {
            realmRepository.updateUser(username, idUser)
        }
    }

    fun deleteUsers(){
        viewModelScope.launch {
            realmRepository.deleteUsers()
        }
    }
}