package com.example.realm.data

import com.example.realm.data.model.User
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.query

class RealmRepository {
    private val configuration = RealmConfiguration.with(setOf(User::class))
    val realm = Realm.open(configuration)

    suspend fun addUser(user: User){
        realm.write {
            copyToRealm(user)
        }
    }

    suspend fun allUser():List<User>{
        var users = listOf<User>()
        realm.query<User>().find()
            .asFlow()
            .collect{ result ->
                users = result.list
            }

        return users
    }

    suspend fun updateUser(username:String, idUser:Int){
        realm.query<User>("")
            .first()
            .find()
            ?.also {
                realm.write {
                    findLatest(it)?.username = username
                }
            }
    }

    suspend fun deleteUsers(){
        realm.write {
            delete(this.query<User>())
        }
    }
}