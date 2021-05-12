package com.example.flux.users

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Users:RealmObject(){


    private lateinit var  address: String
    private var  age: Int =0
    private lateinit var  gender: String
    private lateinit var firstName:String
    private lateinit var email:String
    private lateinit var password:String
    private lateinit var lastName:String
    private lateinit var country:String
    private lateinit var productId:String
    private lateinit var productName:String
    private lateinit var dateOfBirth:String



    private lateinit var state:String
    private var postcode:Int = 0
    private var telephone:Long = 0


    public fun setFirstName(firstName:String){
        this.firstName=firstName
    }


    public fun setDateOfBirth(dateOfBirth:String){
        this.dateOfBirth=dateOfBirth
    }
    public fun setAge(age:Int){
        this.age=age
    }
    public fun setGender(gender:String){
        this.gender=gender
    }
    public fun setPassword(password:String){
        this.password=password
    }
    public fun setLastName(lastName: String){

        this.lastName=lastName
    }
    public fun setAddress(address:String){
        this.address=address
    }
    public fun setEmail(email:String){
        this.email=email
    }  public fun setCountry(country:String){
        this.country=country
    }


    public fun setPostCode(postcode:Int){
        this.postcode=postcode
    }
    public fun setState(state:String){
        this.state=state
    }  public fun setTelephone(telephone: Long){
        this.telephone=telephone
    }


     fun getFirstName(): String {
        return firstName
    }

     fun getLastName(): String {
        return  lastName
    }

     fun getPassword(): String {
        return password
    }

     fun getAge(): Int {
        return  this.age
    }


     fun getGender(): String {
        return gender
    }
     fun getAddress(): String {
        return  address
    }

     fun getPostcode(): Int {
        return  postcode
    }



     fun getCountry(): String {
        return country
    }

     fun getState(): String {
        return state
    }

     fun getTelephone(): Long {
        return  telephone
    }

     fun getEmail(): String {
        return email
    }

     fun getDateOfBirth(): String {
        return  this.dateOfBirth
    }





    }