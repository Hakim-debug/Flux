package com.example.flux.users;

import android.content.ContentValues.TAG
import android.util.Log
import com.example.flux.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


public class FirebaseQuery{


    fun signIn(){


        val database = FirebaseDatabase.getInstance()
        println(database)
        val myRef = database.getReference("message")
        myRef.setValue("Hello, World!")
    }

    fun register(){

    }

}
