package com.example.flux

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
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
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_main.fab

//Handel the the paymangent and the checkout function
open class CheckoutActivity: AppCompatActivity() {

    private var color = 0
    private var count =0
    private var  storeDB =  kotlin.collections.ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_cart)
        //setSupportActionBar(toolbar)
        title="Durunshop"
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        invalidateOptionsMenu()

        //wearCategoryListener()


    }

    override fun onBackPressed() {

        val intent= Intent(this, MainActivity::class.java)
        intent.putExtra("CART-R",storeDB)
        startActivity(intent)
        //finish()

    }
    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        val SDK_INT = Build.VERSION.SDK_INT
        if (SDK_INT > 8) {
            val policy = StrictMode.ThreadPolicy.Builder()
                .permitAll().build()
            StrictMode.setThreadPolicy(policy)

        }
        return super.onCreateView(name, context, attrs)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        val isMeant=fetchItems(this)
        if(isMeant){
            //val category = CategoryAdaptor(this,storeDB,menu)
//            wears_category_items_view.adapter= category
        }
        pay()
        val register =menu.findItem(R.id.register)
        val registerText = SpannableString(register.title);
        registerText.setSpan( ForegroundColorSpan(getColor(R.color.colorAccentWhite)), 0, registerText.length, 0);
        register.title = registerText
        val signIn=menu.findItem(R.id.sign_in)
        val signText = SpannableString(signIn.title);
        signText.setSpan( ForegroundColorSpan(getColor(R.color.colorAccentWhite)), 0, signText.length, 0);
        signIn.title = signText
        val help=menu.findItem(R.id.help_item)
        val helpText = SpannableString(help.title);
        helpText.setSpan( ForegroundColorSpan(getColor(R.color.colorAccentWhite)), 0, helpText.length, 0);
        help.title = helpText
        val about=menu.findItem(R.id.about_item)
        val aboutText = SpannableString(about.title);
        aboutText.setSpan( ForegroundColorSpan(getColor(R.color.colorAccentWhite)), 0, aboutText.length, 0);
        about.title = aboutText
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        var performEventOn : Intent?
        val itemId= item.itemId


        if ( itemId== R.id.sign_in){

            performEventOn= Intent(this, LoginActivity::class.java)
            startActivity(performEventOn)

        }
        if (itemId == R.id.sign_in){
            performEventOn= Intent(this, RegisterActivity::class.java)
            startActivity(performEventOn)
        }

        if (itemId == R.id.help_item){
            performEventOn= Intent(this, HelpActivity::class.java)
            startActivity(performEventOn)
        }

        if (itemId == R.id.about_item){
            val toast = Toast.makeText(applicationContext,R.string.about_message
                , Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER,0,0)
            toast.view.background.setTint(getColor(R.color.colorAccent))
            val text =  toast.view.findViewById<TextView>(android.R.id.message);
            text.setTextColor(getColor(R.color.colorAccentWhite));
            toast.show()
        }
        return super.onOptionsItemSelected(item)


    }

    private fun wearCategoryListener(){

        val  menVisibility =  findViewById<ConstraintLayout>(R.id.wear_category_option)

        val category = arrayOf(findViewById<Button>(R.id.man),findViewById<Button>(R.id.women),
            findViewById<Button>(R.id.children),findViewById<Button>(R.id.sort_label))

        category.forEach { element->
            val text  =  element as TextView
            color = text.textColors.defaultColor

            element.setOnClickListener{
                val fadeOut: Animation = AlphaAnimation(1.0f, 0.0f)
                val fadeIn: Animation = AlphaAnimation(0.0f, 0.1f)
                category.forEach {
                        element->
                    element.setTextColor(color)
                    element.text= element.text.toString().replace(getString(R.string.after_symbol),getString(R.string.init_symbol))

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
        val women = findViewById<Button>(R.id.women)
        val  menVisibility =  findViewById<ConstraintLayout>(R.id.wear_category_option)
        val text  =  women as TextView

        women.setOnClickListener{
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
        storeDB= intent.getStringArrayListExtra("CART")
        return true

    }
    private fun pay(){
       val pay= findViewById<Button>(R.id.pay)
        var routeIntent= Intent()

        pay.setOnClickListener{
            val dialog=Dialog(this)
            dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
            dialog.show()
            dialog.setContentView(R.layout.activity_process_order)
            val  cancelOrder =dialog.findViewById<Button>(R.id.cancel_order_in_progress)
            val  orderProgressBar =dialog.findViewById<ProgressBar>(R.id.order_process_bar)
            val handler = Handler();
            count=0
            Thread(Runnable {
                while (count < 100) {
                    count += 1
                    handler.post {
                        orderProgressBar.progress = count

                        if(count==100){

                            orderProgressBar.progress = 100
                            routeIntent= Intent(this, CompletedOrder::class.java)
                            this.startActivity(routeIntent)
                        }

                    }
                    try {
                        // Sleep for 60 milliseconds.
                        Thread.sleep(60)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            }).start()


            cancelOrder?.setOnClickListener{
                card_details.performClick()
                println(cancelOrder)
                //setContentView(R.layout.activity_checkout)
                dialog.dismiss()

            }




        }
    }
    class CategoryAdaptor(private var context:Activity,
                          var storeDb: ArrayList<String>, private var menu:Menu) :
        BaseAdapter() {

        var counter =0
        private var routeIntent : Intent= Intent()
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            return renderStock(position,parent)
        }

        override fun getItem(position: Int): Any {
            return 0
        }

        private val cart = ArrayList<String>()
        private val cartImagesUrl = ArrayList<String>()
        private fun renderStock(position: Int, parent: ViewGroup?):View{

            val layout = context.layoutInflater
            val view =layout.inflate(R.layout.grid_resource,null ,true)
            val dialogLayoutView=layout.inflate(R.layout.dialog_item_layout,null,true)
            val gridContent=layout.inflate(R.layout.content_main,null,true)
            val progressBar = context.findViewById<ProgressBar>(R.id.progress_bar_stock)
            val dialogImgView = dialogLayoutView.findViewById<ImageView>(R.id.dialog_zoom_img)
            val zoomInOutSeekBar = dialogLayoutView.findViewById<SeekBar>(R.id.zoom_in_and_out)
            val gridItemView = view.findViewById<ImageView>(R.id.grid_item)
            val gridItemCartButton = view.findViewById<Button>(R.id.grid_add_to_cart)
            val gridItemColorButton = view.findViewById<Button>(R.id.grid_item_color)
            val gridItemZoomButton = view.findViewById<Button>(R.id.grid_zoom_item)
            val removeItem= gridContent.findViewById<GridView>(R.id.wears_category_items_view)
            val numberOfItems=view.findViewById<TextView>(R.id.number_of_items)
            progressBar.visibility = View.VISIBLE
            val cartHolder = context.findViewById<Button>(R.id.cart_layout_holder)
            cartHolder.setOnClickListener{

                routeIntent= Intent(context, CheckoutActivity::class.java)
                routeIntent.putExtra("CART",cart)
                context.startActivity(routeIntent)

            }

            routeIntent.putExtra("CART-R",storeDb)
            //  context.startActivity(routeIntent)
            gridItemCartButton.setOnClickListener {
                parent?.removeViewInLayout(view)
                storeDb.removeAt(position)
                removeItem.run{
                    notifyDataSetChanged()
                }
            }


            Picasso.with(context).load( storeDb[position]).fetch(object : Callback {

                override fun onSuccess() {
                    removeItem.deferNotifyDataSetChanged();
                    Picasso.with(context).load(storeDb[position]).into(dialogImgView)
                    Picasso.with(context).load(storeDb[position])
                        .into(gridItemView, object : Callback {
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
                    Toast.makeText(
                        context,
                        "Please wait, retrieving info",
                        Toast.LENGTH_LONG
                    )
                        .show()

                }
            })



            dialogEvent(context,dialogLayoutView,gridItemZoomButton)
            gridItemCartButton.setBackgroundResource(R.drawable.ic_remove_shopping_cart_black_24dp)
            gridItemColorButton.setBackgroundResource(R.drawable.ic_color_lens_black_24dp)
            gridItemZoomButton.setBackgroundResource(R.drawable.ic_zoom_in_black_24dp)
            zoomInAndOut(zoomInOutSeekBar,dialogImgView)

            return view


        }


        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getCount(): Int {
            return storeDb.size
        }
        private fun dialogEvent(context:Activity, inflateLayout:View  ,zoomButton:Button){
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
            seekBar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(
                    seekBar: SeekBar,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    view.scaleX =seekBar.progress.toFloat()
                    view.scaleY = seekBar.progress.toFloat()
                    Toast.makeText(context,
                        "Progress is: " + seekBar.progress + "%",
                        Toast.LENGTH_SHORT).show()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {

                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    Toast.makeText(context,
                        "Progress is: " + seekBar?.progress + "%",
                        Toast.LENGTH_SHORT).show()
                }

            })
        }

    }

}









