package com.example.flux

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.flux.controller.Controller
import com.example.flux.users.UserBuilder
import com.example.flux.users.Users


class RegisterActivity : AppCompatActivity() {

    private lateinit var userModel: Users
    private lateinit var  userController:Controller
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.title = "Register"
        setContentView(R.layout.activity_register)
        questionMarkButtonClick()
        radioButtonOnchange()

        signUp()

    }
    private  fun questionMarkButtonClick(){

        val qMark=findViewById<Button>(R.id.q_mark)
        qMark.setOnClickListener{
            val toast = Toast.makeText(applicationContext,R.string.message
                , Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER,0,0)
            toast.view.background.setTint(getColor(R.color.colorAccent))
            val text =  toast.view.findViewById<TextView>(android.R.id.message);
            text.setTextColor(getColor(R.color.colorAccentWhite));
            toast.show()
        }
    }

  private  fun radioButtonOnchange(){

      val man = findViewById<RadioButton>(R.id.man)
      val woman = findViewById<RadioButton>(R.id.woman)
      val wheelchair = findViewById<RadioButton>(R.id.wheelchair)
      val id= arrayOf<RadioButton>(man,woman,wheelchair)


         for (radioButton in id){

          radioButton.setOnClickListener{view ->
              for (i in id.indices){
                  id[i].isChecked= false;
              }

             if(radioButton.id.toString() == view.id.toString()){
                 radioButton.isChecked= true;
                 userController.controllerSetGender(radioButton.text.toString())
             }

         }
         }







    }

    private fun signUp(){




          val month= findViewById<EditText>(R.id.month)
          val day= findViewById<EditText>(R.id.day)
          val register= findViewById<Button>(R.id.sign_up)
          val year= findViewById<EditText>(R.id.year)
          val firstName=findViewById<EditText>(R.id.first_name)
          val email=findViewById<EditText>(R.id.email)
          val password=findViewById<EditText>(R.id.password)
          val lastName=findViewById<EditText>(R.id.sure_name)
          val queryProvider= UserBuilder()

           register.setOnClickListener{
            val dateOfBirth= "${day.text}/${month.text}/${year.text}"


               userController.controllerSetCountry("Sweden")
               userController.controllerSetPostCode(14143)
               userController.controllerSetAdress("attundagrand")
               userController.controllerSetAge(48)

               userController.controllerSetState("Accra")
               password.inputType = InputType.TYPE_TEXT_VARIATION_NORMAL
            userController.controllerSetName(firstName.text.toString())
            userController.controllerSetEmail(email.text.toString())
            userController.controllerSetPassword(password.text.toString())
            userController.controllerSetDateOfBirth(dateOfBirth)
            userController.controllerSetLastName(lastName.text.toString())
              /* queryProvider.addUser(user)*/
               queryProvider.pushUser()
               password.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD

             val routeIntent= Intent(this,ProfileActivity::class.java)
               startActivity(routeIntent)

        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
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
            performEventOn= Intent(this, HelpActivity::class.java)
            startActivity(performEventOn)
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
