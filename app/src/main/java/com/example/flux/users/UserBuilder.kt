package com.example.flux.users

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.flux.LoginActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UserBuilder(): AppCompatActivity(){



    private   var users= ArrayList<Users>()
    private var database= FirebaseDatabase.getInstance()
    private var databaseReference=database.reference



    public fun addUser(product: Users) {
        this.users.add(product)

    }
    public fun pushUser(){


         val address= databaseReference.child("address")
         val firstName= databaseReference.child("firstName")
         val age= databaseReference.child("age")
        val dateOfBirth= databaseReference.child("dateOfBirth")
         val gender= databaseReference.child("gender")
         val email= databaseReference.child("email")
         val password= databaseReference.child("password")
         val lastName= databaseReference.child("lastName")
         val country= databaseReference.child("country")
         val state= databaseReference.child("state")
         val postcode= databaseReference.child("postcode")
         val telephone= databaseReference.child("telephone")


        for (item in this.users){

            address.setValue(item.getFirstName())
            dateOfBirth.setValue(item.getDateOfBirth())
            firstName.setValue(item.getFirstName())
            email.setValue(item.getEmail())
            password.setValue(item.getPassword())
            lastName.setValue(item.getLastName())
            country.setValue(item.getCountry())
            age.setValue(item.getAge())
            gender.setValue(item.getGender())
            state.setValue(item.getState())
            postcode.setValue(item.getPostcode())
            telephone.setValue(item.getTelephone())
        }

    }
    public fun pullUser(context:Activity){
        var userList=ArrayList<Any>()
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val value = dataSnapshot.value as Map<*, *>

                val user=Users()
                user.setEmail("${value["email"]}")
                user.setPassword("${value["password"]}")
                user.setGender("${value["gender"]}")
                user.setLastName("${value["lastName"]}")
                user.setFirstName("${value["firstName"]}")

                userList.add(user.getFirstName())
                userList.add(user.getEmail())
                userList.add(user.getPassword())
                val routeIntent= Intent(context, LoginActivity::class.java)
                routeIntent.putExtra("USERS",userList)


            }



            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })
    }

}