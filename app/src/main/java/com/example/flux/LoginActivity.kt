package com.example.flux

import android.content.ClipData
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText

import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.flux.cache.Cache
import com.example.flux.controller.Controller
import com.example.flux.users.UserBuilder
import com.example.flux.users.Users
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.view.*


class LoginActivity : AppCompatActivity() {
    private lateinit var userModel: Users
    private lateinit var  userController:Controller

    private  var userCache=Cache()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        this.title = getString(R.string.login)
         this.userModel=Users()
         this.userController=Controller(userModel)
        signIn()

    }


//Handel new user in Database


    private fun signIn(){

        val sign =findViewById<Button>(R.id.sign_in)
        val email=findViewById<EditText>(R.id.email)
        val password=findViewById<EditText>(R.id.password)
        val database= FirebaseDatabase.getInstance()
        val databaseReference=database.reference
        userController.controllerSetEmail("${email.text}")
        userController.controllerSetPassword("${sign.text}")
         val queryProvider= UserBuilder()
         queryProvider.pullUser(this)

        val signUpFromRegister=findViewById<TextView>(R.id.sign_up_login)
        signUpFromRegister.setOnClickListener { event ->
            val intent= Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        var performIntent:Intent?
        sign.setOnClickListener{event ->
         password.inputType=InputType.TYPE_TEXT_VARIATION_NORMAL
            userCache.setCache("email",email.text)
            userCache.setCache("password",email.text)
            println(userCache.getCache("email"))

            var userList=ArrayList<Any>()
            databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    val value = dataSnapshot.value as Map<*, *>
                    userController.controllerSetEmail("${value["email"]}")
                    userController.controllerSetPassword("${value["password"]}")

                    if((email.text.toString() ==userController.controllerGetEmail() )&&(password.text.toString()==userController.controllerGetPassword())){

                        performIntent = Intent(this@LoginActivity,ProfileActivity::class.java)
                        startActivity(performIntent)
                    }else{
                        val toast=Toast.makeText(this@LoginActivity,"The account does not exist in out database",Toast.LENGTH_LONG)
                        toast.setGravity(Gravity.CENTER,0,0)
                        toast.show()

                    }
                    password.inputType=InputType.TYPE_TEXT_VARIATION_PASSWORD

                }



                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                }
            })





        }
    }

    //Inflate the menu; this adds items to the action bar if it is present.
    override fun onCreateOptionsMenu(menu: Menu): Boolean {



        menuInflater.inflate(R.menu.menu_main, menu)

        val register =menu.findItem(R.id.register)
        val registerText = SpannableString(register.title);
        registerText.setSpan( ForegroundColorSpan(getColor(R.color.colorAccentWhite)), 0, registerText.length, 0);
        register.title = registerText



        val signIn=menu.findItem(R.id.sign_in)
        val signText = SpannableString(signIn.title);
        signText.setSpan( ForegroundColorSpan(getColor(R.color.colorAccentWhite)), 0, signText.length, 0);
        signIn.title = signText
        signIn.isVisible = false

        val help=menu.findItem(R.id.help_item)
        val helpText = SpannableString(help.title);
        helpText.setSpan( ForegroundColorSpan(getColor(R.color.colorAccentWhite)), 0, helpText.length, 0);
        help.title = helpText
        help.isVisible = false

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
            item.setVisible(false)
            performEventOn= Intent(this, LoginActivity::class.java)
            //startActivity(performEventOn)

        }
        if (itemId == R.id.register){
           performEventOn= Intent(this, RegisterActivity::class.java)
           startActivity(performEventOn)
        }

        if (itemId == R.id.help_item){
            //performEventOn= Intent(this, HelpActivity::class.java)
            //startActivity(performEventOn)
        }

        if (itemId == R.id.about_item){
            val toast = Toast.makeText(applicationContext,R.string.about_message
                , Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER,0,0)
            toast.view.background.setTint(getColor(R.color.colorAccent))
            var text =  toast.view.findViewById<TextView>(android.R.id.message);
            text.setTextColor(getColor(R.color.colorAccentWhite));
            toast.show()
        }
        return super.onOptionsItemSelected(item)


    }

}
