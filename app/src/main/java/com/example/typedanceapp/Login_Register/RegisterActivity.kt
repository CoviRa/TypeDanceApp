package com.example.typedanceapp.Login_Register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.typedanceapp.MainScreen.MainActivity
import com.example.typedanceapp.R
import com.example.typedanceapp.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class RegisterActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val email: String = binding.etEmail.text.toString().trim()
            val password: String = binding.etPassword.text.toString().trim()
            if (email.isEmpty()) {
                binding.etEmail.error = "Email не введен"
                binding.etEmail.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.etEmail.error = "Email введен неправильно"
                binding.etEmail.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty() || password.length < 6) {
                binding.etPassword.error = "В пароле меньше 6 символов"
                binding.etPassword.requestFocus()
                return@setOnClickListener
            }
            registerUser(email, password)
        }

    }

//    override fun onStart() {
//        super.onStart()
//        if (auth.currentUser != null) {
//            Intent(this@RegisterActivity, MainActivity::class.java).also { intent ->
//                intent.flags =
//                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                startActivity(intent)
//            }
//        }
//    }
//        firebaseAuth = FirebaseAuth.getInstance() //инициализируем


//        binding.button6.setOnClickListener {
//            val username = binding.login1.text.toString()
//            val email = binding.logIn.text.toString()  //переменная для email
//            val password = binding.pasIn.text.toString()  //переменная для пароля
//            val confirmPass = binding.pasIn2.text.toString()  //переменная для подтверждения пароля
//
//            database = FirebaseDatabase.getInstance().getReference("Users")
//            val user = User(username)
//            database.child(username).setValue(user).addOnSuccessListener {
//                binding.login1.text?.clear()
//
//                Toast.makeText(this, "Данные успешно сохранились", Toast.LENGTH_SHORT).show()
//            }
//
//            //проверка на то, что поля не пустые
//            if (email.isNotEmpty() && password.isNotEmpty() && confirmPass.isNotEmpty())
//            {
//                if(password == confirmPass){
//                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
//                        if(it.isSuccessful){
//                            val intent = Intent(this, LoginActivity::class.java)
//                            startActivity(intent)
//                        }
//                        //исключение в случае неудачной регистрации
//                        else{
//                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                }
//                else{
//                    Toast.makeText(this, getString(R.string.pas_confpass), Toast.LENGTH_SHORT).show()
//                }
//            }
//            else{
//                Toast.makeText(this, getString(R.string.not_empty), Toast.LENGTH_SHORT).show()
//            }
////            Toast.makeText(baseContext, login.text, Toast.LENGTH_SHORT).show()
//        }
//    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Intent(this@RegisterActivity, MainActivity::class.java).also {
                        it.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                    }
                } else {
                    Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }
}