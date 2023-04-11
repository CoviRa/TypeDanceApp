package com.example.typedanceapp.Login_Register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.typedanceapp.R
import com.example.typedanceapp.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding
    private lateinit var ed_password: EditText
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        ed_password = findViewById(R.id.etEmail)
        binding.buttonReset.setOnClickListener {
            val s_pas = ed_password.text.toString()
            firebaseAuth.sendPasswordResetEmail(s_pas)
                .addOnSuccessListener {
                    Toast.makeText(this, getString(R.string.letter_pas), Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener{
                    Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                }
        }
    }
}