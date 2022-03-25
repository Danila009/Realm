package com.example.realm.data.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

class User:RealmObject {
    @PrimaryKey
    val id:Int = 0
    var username:String = ""
    var age:Int = 0
}