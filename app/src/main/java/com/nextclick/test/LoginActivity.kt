package com.nextclick.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.nextclick.test.customerUtils.ProductsActivity
import com.nextclick.test.databinding.ActivityLoginBinding
import com.nextclick.test.vendorUtils.AddProductActivity
import com.nextclick.test.vendorUtils.HomeActivity

class LoginActivity : AppCompatActivity() {
    lateinit var binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);

        val email=binding.editEmail.text
        val pass=binding.editPassword.text

        binding.btnLogin.setOnClickListener(View.OnClickListener {
            Log.d("TAG1", "onCreate: "+email)
            Log.d("TAG", "onCreate: "+pass)
            Toast.makeText(this@LoginActivity,"email="+binding.editEmail.text ,Toast.LENGTH_LONG).show()

            if (TextUtils.isEmpty(binding.editEmail.text.toString()) && TextUtils.isEmpty(binding.editPassword.text.toString())) {
                // this method will call when email and password fields are empty.
                Toast.makeText(this@LoginActivity, "Please Enter Email and Password", Toast.LENGTH_SHORT).show()
            } else {

                if(binding.editEmail.text.trim().toString().equals("user@gmail.com")
                    && binding.editPassword.text.trim().toString().equals("123456") ){
                    Toast.makeText(this@LoginActivity, "User", Toast.LENGTH_SHORT).show()
                    val i = Intent(this@LoginActivity, ProductsActivity::class.java)
                    startActivity(i)
                    finish()
                }else if (binding.editEmail.text.trim().toString().equals("vendor@gmail.com")
                    && binding.editPassword.text.trim().toString().equals("123456") ){
                    Toast.makeText(this@LoginActivity, "Vendor", Toast.LENGTH_SHORT).show()
                    val i = Intent(this@LoginActivity, HomeActivity::class.java)
                    startActivity(i)
                    finish()
                }else{
                    Toast.makeText(this@LoginActivity, "Please enter correct email and password", Toast.LENGTH_SHORT).show()
                }
            }
        })




    }

    fun login(email:String,pass:String){

        if(email.equals("user@gmail.com")&&pass.equals("123456")){

        }

    }
}