package com.example.flux.users

interface IProduct  {



    public fun getPrice(): Double
    public fun getProductName():String
    public fun getProductQuantity():Double
    public fun getProductId():String
    public fun productType():String
    public fun getPackage()
   public fun  getProductColor():String
   public fun  getProductSize():Int
   public fun  getProductManufacture():String
   public fun  getProductMaterialDetails():String






}
interface IPerson{
    public fun getFirstName(): String
    public fun getLastName():String
    public fun getPassword():String
    public fun getAge():Int
    public fun getDateOfBirth():String
    public fun getPostcode():Int
    public fun getGender():String
    public fun getAddress():String
    public fun getCountry():String
    public fun getState():String
    public fun getTelephone():Long
    public fun getEmail():String
}