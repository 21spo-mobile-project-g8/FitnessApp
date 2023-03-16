package com.example.fitnessapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val registerButton = findViewById<View>(R.id.buRegister)
        registerButton.setOnClickListener(this)

        val loginButton = findViewById<View>(R.id.buLogin)
        loginButton.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        // Check which button was clicked
        when (v.id) {
            R.id.buRegister -> {
                // Start RegisterActivity
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
            R.id.buLogin -> {
                // Start LoginActivity
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
