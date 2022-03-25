package com.example.realm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.realm.data.RealmRepository
import com.example.realm.data.model.User

class MainActivity : ComponentActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val realmRepository = RealmRepository()
        val mainViewModelFactory = MainViewModelFactory(realmRepository)

        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)

        setContent {
            var user by remember { mutableStateOf(listOf<User>()) }

            LaunchedEffect(key1 = Unit, block = {
                repeat(10){
                    mainViewModel.addUser(
                        User().apply {
                            username = "username$it"
                            age  = it
                        }
                    )
                }
            })

            mainViewModel.allUser()
            lifecycleScope.launchWhenStarted {
                mainViewModel.responseUser.collect{
                    user = it
                }
            }


            LazyColumn(content = {
                items(user){item ->
                    Text(
                        text = "${item.username} / ${item.age} / ${item.id}",
                        modifier = Modifier.padding(10.dp)
                    )
                }
            })
        }
    }
}