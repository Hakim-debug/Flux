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
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_completed_order.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.fab
import kotlinx.android.synthetic.main.content_main.*
import org.json.JSONArray
import org.json.JSONObject

//Handel the order prosess and Tracking
open class CompletedOrder: AppCompatActivity() {

    private var color = 0
    private var  storeDB =  JSONArray()
    private var backwardTemp= ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completed_order)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }


        backToMainPage()
        trackOrder()



    }

    private fun trackOrder(){
        track.setOnClickListener{
            val performIntent = Intent(this,TrackOrderActivity::class.java)
            startActivity(performIntent)

        }
    }
    private fun backToMainPage(){
        main_button.setOnClickListener{
           val performIntent = Intent(this,MainActivity::class.java)
            startActivity(performIntent)

        }
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
    // Inflate the menu; this adds items to the action bar if it is present.
    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)
        val register =menu.findItem(R.id.register)
        val registerText = SpannableString(register.title)
        registerText.setSpan( ForegroundColorSpan(getColor(R.color.colorAccentWhite)), 0, registerText.length, 0)
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


}









