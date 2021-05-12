package com.example.flux.users;
abstract class TShirt: IProduct {

    private  var price:Double=0.0
    private var productQuantity:Double=0.0
    private lateinit var productColor:String
    private var productSize:Int = 0
    private lateinit var productManufacture:String
    private lateinit var productMaterialDetails:String

    private lateinit var productId:String
    private lateinit var productName:String
    private lateinit var state:String

    override fun getPrice(): Double {
        return price
    }

    public fun setProductName(productName:String){
        this.productName=productName
    }
    public fun setPrice(price:Double){
        this.price=price
    }

    public fun setProductId(productId:String){
        this.productId=productId
    }
    public fun setProductManufacture(productManufacture:String){
        this.productManufacture=productManufacture
    }

    public fun setProductColor(productColor:String){
        this.productColor=productColor
    }

    override fun getProductQuantity(): Double {
        return this.productQuantity
    }



    override fun getProductName(): String {
        return productName
    }

    override fun getProductId(): String {
        return productId
    }

    override fun productType(): String {
        return productName
    }

    override fun getPackage() {

    }

    override fun getProductColor(): String {
        return productColor
    }

    override fun getProductSize(): Int {
        return productSize
    }

    override fun getProductManufacture(): String {
        return this.productManufacture
    }

    override fun getProductMaterialDetails(): String {
        return productMaterialDetails
    }

}

