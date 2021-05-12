package com.example.flux.controller

import com.example.flux.users.Users

class Controller {

    lateinit var model:Users
    constructor( model:Users ) {


        this.model = model
    }

    fun controllerSetAge(age:Int){
        model.setAge(age)

    }
    fun controllerGetAge(): Int {
        return model.getAge()
    }

    fun  controllerSetName(name:String) {
        return model.setFirstName(name)
        

    }
    fun controllerGetName (): String{
        return model.getFirstName()
    }
    fun  controllerSetEmail(email:String) {
        return model.setEmail(email)


    }
    fun controllerSetLastName(LastName: String) {

        return model.setLastName(LastName)
    }


    fun controllerGetLastName(): String {

        return model.getLastName()
    }


    fun controllerGetEmail(): String {

        return model.getEmail()
    }

    fun controllerSetPassword (PassWord: String)  {
        return model.setPassword(PassWord)
    }

    fun controllerGetPassword(): String {
        return model.getPassword()
    }

    fun controllerSetAdress (Adress: String) {
        return model.setAddress(Adress)
    }

    fun controllerGetAdress(): String {
        return model.getAddress()
    }

    fun controllerSetGender (Gender:String)  {
        return model.setGender(Gender)
    }

    fun controllerGetGender ():String{
        return model.getGender()
    }

    fun controllerSetDateOfBirth(DateOfBirth:String)  {
        return model.setDateOfBirth(DateOfBirth)
    }

    fun controllerGetDateOfBirth():String {
        return model.getDateOfBirth()
    }

    fun controllerSetPhoneNr(PhoneNr: Long){
        return model.setTelephone(PhoneNr)
    }

    fun controllerGetPhoneNr ():Long {
        return model.getTelephone()
    }

    fun controllerSetCountry(Country:String)   {
        return model.setCountry(Country)
    }

    fun controllerGetCountry(): String {
        return model.getCountry()
    }

    fun controllerSetPostCode(PostCode: Int){
        return model.setPostCode(PostCode)
    }

    fun controllerGetPostCode():Int {
        return model.getPostcode()
    }

    fun controllerSetState(State:String) {
        return model.setState(State)
    }

    fun controllerGetState(): String {
        return model.getState()
    }
}

