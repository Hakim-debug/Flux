package com.example.flux.users;

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

// This class is the  product builder
public class ProductBuilder {
    private  var product= ArrayList<IProduct>()
    private var database=FirebaseDatabase.getInstance()
    private var databaseReference=database.reference





    public fun addProduct(product:IProduct) {
        this.product.add(product)

    }
    public fun pushProduct(){



        val productName= databaseReference.child("productName")
        val productColor= databaseReference.child("productColor")
        val productId= databaseReference.child("productId")
        val productSize= databaseReference.child("productSize")
        val productManufacture= databaseReference.child("productManufacture")

        println(this.product.size)
        for (item in this.product){
            println(item.getProductColor())
            productName.setValue(item.getProductName())
            productColor.setValue(item.getProductColor())
            productSize.setValue(item.getProductSize())
            productId.setValue(item.getProductId())
            productManufacture.setValue(item.getProductManufacture())
        }

    }
    public fun pullProduct(){
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value =
                    dataSnapshot.value as Map<*, *>
                Log.d(TAG, "Value is: ${value["message"]}")
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
    }
}
