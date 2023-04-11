package com.example.typedanceapp.Login_Register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.typedanceapp.MainScreen.MainActivity
import com.example.typedanceapp.R
import com.example.typedanceapp.databinding.ActivityPreviewScreenBinding
import com.google.firebase.auth.FirebaseAuth

class PreviewScreenActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityPreviewScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview_screen)

        binding = ActivityPreviewScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance() //инициализируем

        binding.button2.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.button4.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onStart(){
        super.onStart()
        //если пользователь уже ранее входил в приложение, то он будет сразу перенесен к опросу
        if(firebaseAuth.currentUser != null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}