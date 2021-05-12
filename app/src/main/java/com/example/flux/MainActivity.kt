package com.example.flux

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import android.view.*
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.example.flux.users.*
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.json.JSONArray
import org.json.JSONObject


open class MainActivity: AppCompatActivity() {

    private var color = 0
    private var  storeDB =  JSONArray()
    private var backwardTemp= ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent= Intent(this, LoginActivity::class.java)
        startActivity(intent)

   /*
       val realm = RealmIOQuery();
         realm.initializeRealm(this)
      val query = realm.query()
             query.beginTransaction();
       var currentIdNum: Number? = query.where(Users::class.java).max("pKey")
       print(currentIdNum)
         val user=query.createObject(Users::class.java)
         user.setFirstName("Dan");
        query.commitTransaction();*/

        /*
       title="Duronshop"
       setSupportActionBar(toolbar)
       fab.setOnClickListener { view ->
           Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                   .setAction("Action", null).show()
       }
       fetchItems(this)
       val category = CategoryAdaptor(this, storeDB)
       wears_category_items_view.adapter= category
       if (intent.getStringArrayListExtra("CART-R") !=null){
           val numberOfItems=findViewById<TextView>(R.id.number_of_items)
           val dataTemp=intent.getStringArrayListExtra("CART-R")
           category.setBucket(dataTemp)
           numberOfItems.text =dataTemp.size.toString()
       }
       invalidateOptionsMenu()
       wearCategoryListener()
       val queryProvider=UserBuilder()

          // queryProvider.addUser(Users())
           //queryProvider.pullUser(this)





*/

   }

//Handels the thread...
   override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
       val SDK_INT = Build.VERSION.SDK_INT
       if (SDK_INT > 8) {
           val policy = StrictMode.ThreadPolicy.Builder()
               .permitAll().build()
           StrictMode.setThreadPolicy(policy)
       }
       return super.onCreateView(name, context, attrs)
   }
    // Inflate the menu; this adds items to the action bar if it is present.
   override fun onCreateOptionsMenu(menu: Menu): Boolean {

       menuInflater.inflate(R.menu.menu_main, menu)
        menu.getItem(1).isVisible = false

        val register =menu.findItem(R.id.register)
       val registerText = SpannableString(register.title);
       registerText.setSpan(
           ForegroundColorSpan(getColor(R.color.colorAccentWhite)),
           0,
           registerText.length,
           0
       );
       register.title = registerText
       val signIn=menu.findItem(R.id.sign_in)
       val signText = SpannableString(signIn.title);
       signText.setSpan(
           ForegroundColorSpan(getColor(R.color.colorAccentWhite)),
           0,
           signText.length,
           0
       );
       signIn.title = signText
       val help=menu.findItem(R.id.help_item)
        help.isVisible = false
       val helpText = SpannableString(help.title);
       helpText.setSpan(
           ForegroundColorSpan(getColor(R.color.colorAccentWhite)),
           0,
           helpText.length,
           0
       );
       help.title = helpText
       val about=menu.findItem(R.id.about_item)
       val aboutText = SpannableString(about.title);
       aboutText.setSpan(
           ForegroundColorSpan(getColor(R.color.colorAccentWhite)),
           0,
           aboutText.length,
           0
       );
       about.title = aboutText
       return true
   }
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
   override fun onOptionsItemSelected(item: MenuItem): Boolean {

       var performEventOn : Intent?
       val itemId= item.itemId


       if ( itemId== R.id.sign_in){

           performEventOn= Intent(this, LoginActivity::class.java)
           startActivity(performEventOn)

       }
       if (itemId == R.id.register){

           performEventOn= Intent(this, RegisterActivity::class.java)
           startActivity(performEventOn)
       }

       if (itemId == R.id.help_item){
           performEventOn= Intent(this, LoginActivity::class.java)
           startActivity(performEventOn)
       }

       if (itemId == R.id.about_item){
           val toast = Toast.makeText(
               applicationContext, R.string.about_message, Toast.LENGTH_LONG
           )
           toast.setGravity(Gravity.CENTER, 0, 0)
           toast.view.background.setTint(getColor(R.color.colorAccent))
           val text =  toast.view.findViewById<TextView>(android.R.id.message);
           text.setTextColor(getColor(R.color.colorAccentWhite));
           toast.show()
       }
       return super.onOptionsItemSelected(item)


   }

   private fun wearCategoryListener(){

       val  menVisibility =  findViewById<ConstraintLayout>(R.id.wear_category_option)

       val category = arrayOf(
           findViewById<Button>(R.id.man), findViewById<Button>(R.id.women),
           findViewById<Button>(R.id.children), findViewById<Button>(R.id.sort_label)
       )

       category.forEach { element->
           val text  =  element as TextView
           color = text.textColors.defaultColor

           element.setOnClickListener{
               val fadeOut: Animation = AlphaAnimation(1.0f, 0.0f)
               val fadeIn: Animation = AlphaAnimation(0.0f, 0.1f)
               category.forEach { element->
                   element.setTextColor(color)
                   element.text= element.text.toString().replace(
                       getString(R.string.after_symbol), getString(
                           R.string.init_symbol
                       )
                   )

               }

               if(menVisibility.isVisible){

                   when (text.text) {
                       getString(R.string.men_triangle_up) -> {
                           text.text = getString(R.string.men)
                       }
                       getString(R.string.women_triangle_up) -> {
                           text.text = getString(R.string.women)
                       }
                       getString(R.string.children_triangle_up) -> {
                           text.text = getString(R.string.children)
                       }
                       getString(R.string.sort_triangle_up) -> {
                           text.text = getString(R.string.sort_by)
                       }
                   }

                   menVisibility.animation=fadeOut

                   menVisibility.animation.duration = 200
                    menVisibility.isVisible = false


               }else {

                   when (text.text) {
                       getString(R.string.men) -> {
                           text.text = getString(R.string.men_triangle_up)

                       }
                       getString(R.string.women) -> {
                           text.text = getString(R.string.women_triangle_up)

                       }
                       getString(R.string.children) -> {
                           text.text = getString(R.string.children_triangle_up)
                       }
                       getString(R.string.sort_by) -> {
                           text.text = getString(R.string.sort_triangle_up)
                       }
                   }
                   color = text.textColors.defaultColor
                   text.setTextColor(getColor(R.color.colorPrimaryDark))
                   menVisibility.animation=fadeIn

                   menVisibility.animation.duration = 200
                   menVisibility.isVisible = true
               }
           }
       }


   }
   private fun wearCategoryListenerWomen(){
       val men = findViewById<Button>(R.id.women)
       val  menVisibility =  findViewById<ConstraintLayout>(R.id.wear_category_option)
       val text  =  men as TextView

       men.setOnClickListener{
           if(menVisibility.isVisible){
               menVisibility.isVisible = false
               text.text = getString(R.string.women)
               text.setTextColor(color)

           }else    if(!menVisibility.isVisible){

               text.text = getString(R.string.women_triangle_up)
               color = text.textColors.defaultColor
               text.setTextColor(getColor(R.color.colorPrimaryDark))
               menVisibility.isVisible = true
           }
       }

   }
   private fun wearCategoryListenerChildren(){
       val men = findViewById<Button>(R.id.children)
       val  menVisibility =  findViewById<ConstraintLayout>(R.id.wear_category_option)
       val text  =  men as TextView

       men.setOnClickListener{
           if(menVisibility.isVisible){
               menVisibility.isVisible = false
               text.text = getString(R.string.children)
               text.setTextColor(color)

           }else    if(!menVisibility.isVisible){

               text.text = getString(R.string.children_triangle_up)
               color = text.textColors.defaultColor
               text.setTextColor(getColor(R.color.colorPrimaryDark))
               menVisibility.isVisible = true
           }
       }

   }

   private fun wearCategoryListenerSortBy(){
       val men = findViewById<Button>(R.id.sort_label)
       val  menVisibility =  findViewById<ConstraintLayout>(R.id.wear_category_option)
       val text  =  men as TextView

       men.setOnClickListener{
           if(menVisibility.isVisible){
               menVisibility.isVisible = false
               text.text = getString(R.string.sort_by)
               text.setTextColor(color)

           }else    if(!menVisibility.isVisible){

               text.text = getString(R.string.sort_triangle_up)
               color = text.textColors.defaultColor
               text.setTextColor(getColor(R.color.colorPrimaryDark))
               menVisibility.isVisible = true
           }
       }

   }
   private fun wearCategoryListenerAddToCart(){
       val addToCart = findViewById<Button>(R.id.grid_add_to_cart)
       val  menVisibility =  findViewById<ConstraintLayout>(R.id.wear_category_option)
   }

   private fun fetchItems(context: Activity): Boolean{
       val parser=FileParser()
       storeDB= parser.parseFile(context, R.raw.new_mvc)
       return true

   }
   class CategoryAdaptor(private var context: Activity, var storeDb: JSONArray) :
       BaseAdapter() {

       var counter =0
       var temp = ArrayList<String>()

      public fun setBucket(temp: ArrayList<String>){
          this.temp=temp

       }
       private fun getBucket():ArrayList<String>{
           return this.temp

       }
       private var routeIntent : Intent= Intent()
       override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

          return renderStock(position, parent)
       }

       override fun getItem(position: Int): Any {
           return  cartImagesUrl[position]
       }


       private val cart = ArrayList<String>()
       private val cartImagesUrl = ArrayList<String>()
       private fun renderStock(
           position: Int,
           parent: ViewGroup?
       ):View{

           val layout = context.layoutInflater
           val view =layout.inflate(R.layout.grid_resource, null, true)
           val dialogLayoutView=layout.inflate(R.layout.dialog_item_layout, null, true)
           val mainContent=layout.inflate(R.layout.content_main, null, true)
           val progressBar = context.findViewById<ProgressBar>(R.id.progress_bar_stock)
           val dialogImgView = dialogLayoutView.findViewById<ImageView>(R.id.dialog_zoom_img)
           val zoomInOutSeekBar = dialogLayoutView.findViewById<SeekBar>(R.id.zoom_in_and_out)
           val gridItemView = view.findViewById<ImageView>(R.id.grid_item)
           val gridItemCartButton = view.findViewById<Button>(R.id.grid_add_to_cart)
           val gridItemColorButton = view.findViewById<Button>(R.id.grid_item_color)
           val gridItemZoomButton = view.findViewById<Button>(R.id.grid_zoom_item)
           val numberOfItems=context.findViewById<TextView>(R.id.number_of_items)

           val url=JSONObject(storeDb[position].toString()).getString("image_url_4x").replace(
               "http",
               "https"
           )
            progressBar.visibility = View.VISIBLE

           val cartHolder=context.findViewById<Button>(R.id.cart_layout_holder)

           cartHolder.setOnClickListener{

              routeIntent= Intent(context, CartActivity::class.java)
               val urls= getBucket()
               for (value in urls){
                   cart.add(value)

               }
               routeIntent.putExtra("CART", cart)
               context.startActivity(routeIntent)

           }



           gridItemCartButton.setOnClickListener {

               val queryProvider =  ProductBuilder()
               val buying= Jeans()
               queryProvider.addProduct(buying)
               buying.setProductColor("Red")
               buying.setPrice(300.0)
               buying.setProductName("Oracle TShirt ")
               buying.setProductId("Y0958848883")
               buying.setProductManufacture("Dan Damur")
               queryProvider.pushProduct()
               queryProvider.pullProduct()
               counter=++counter
               cartHolder.setBackgroundResource(R.drawable.ic_add_shopping_cart_black_24dp)
               numberOfItems.text = counter.toString()
                   cart.add(cartImagesUrl[position])

           }
           for(i in 0..storeDb.length()){


               if(i<storeDb.length()){

                   val urls=JSONObject(storeDb[i].toString()).getString("image_url_4x").replace(
                       "http",
                       "https"
                   )

                       cartImagesUrl.add(i, urls)



               }

           }

           Picasso.with(context).load(url).fetch(object : Callback {

               override fun onSuccess() {

                   Picasso.with(context).load(url).into(dialogImgView)
                   Picasso.with(context).load(url).into(gridItemView, object : Callback {
                       override fun onSuccess() {
                           progressBar.visibility = View.GONE
                       }

                       override fun onError() {
                           Toast.makeText(
                               context,
                               "Please wait, retrieving info",
                               Toast.LENGTH_LONG
                           ).show()
                       }

                   })
               }

               override fun onError() {
                   Toast.makeText(context, "Please wait, retrieving info", Toast.LENGTH_LONG)
                       .show()

               }
           })


           dialogEvent(context, dialogLayoutView, gridItemZoomButton)
           gridItemCartButton.setBackgroundResource(R.drawable.ic_shopping_cart_black_afterdp)
           gridItemColorButton.setBackgroundResource(R.drawable.ic_color_lens_black_24dp)
           gridItemZoomButton.setBackgroundResource(R.drawable.ic_zoom_in_black_24dp)
           zoomInAndOut(zoomInOutSeekBar, dialogImgView)

       return view


       }



       override fun getItemId(position: Int): Long {
           return 0
       }

       override fun getCount(): Int {
           return storeDb.length()
       }
       private fun dialogEvent(context: Activity, inflateLayout: View, zoomButton: Button){
           val dialog = Dialog(context)
           dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)

           zoomButton.setOnClickListener{



               if(inflateLayout.isVisible){
                   dialog.setContentView(inflateLayout)
                   dialog.show()

               }




           }

       }

       private fun zoomInAndOut(seekBar: SeekBar, view: View){
           seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
               override fun onProgressChanged(
                   seekBar: SeekBar,
                   progress: Int,
                   fromUser: Boolean
               ) {
                   view.scaleX = seekBar.progress.toFloat()
                   view.scaleY = seekBar.progress.toFloat()
                   Toast.makeText(
                       context,
                       "Progress is: " + seekBar.progress + "%",
                       Toast.LENGTH_SHORT
                   ).show()
               }

               override fun onStartTrackingTouch(seekBar: SeekBar?) {

               }

               override fun onStopTrackingTouch(seekBar: SeekBar?) {
                   Toast.makeText(
                       context,
                       "Progress is: " + seekBar?.progress + "%",
                       Toast.LENGTH_SHORT
                   ).show()
               }

           })
       }

   }

}









