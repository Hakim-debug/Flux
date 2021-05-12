package com.example.flux.users

import android.content.Context
import io.realm.Realm

class RealmIOQuery{
    private lateinit var realm :Realm
    fun initializeRealm(context: Context){
        Realm.init(context);
        realm=Realm.getDefaultInstance();
    }

    fun query (): Realm {
        return realm;
    }
}

