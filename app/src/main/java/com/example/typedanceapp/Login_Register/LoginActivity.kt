package com.example.typedanceapp.Login_Register

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.typedanceapp.MainScreen.MainActivity
import com.example.typedanceapp.R
import com.example.typedanceapp.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {

    //получаем ссылки на все представления
//    var login = findViewById<TextInputEditText>(R.id.login)
//    var password = findViewById<TextInputEditText>(R.id.password1)
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    val TAG = "LoginFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)

        firebaseAuth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)) //идентификатор запроса
            .requestEmail()  //запрос email
            .build()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnRegister.setOnClickListener {
            Intent(this@LoginActivity, RegisterActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.btnForgotPassword.setOnClickListener{
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
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
            loginUser(email, password)
        }
    }

    private fun loginUser(email: String, password: String) {

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Intent(this@LoginActivity, MainActivity::class.java).also { intent ->
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }
                } else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun signInGoogle(){
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser != null) {
            Intent(this@LoginActivity, MainActivity::class.java).also { intent ->
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        findViewById<Button>(R.id.btnGoogle).setOnClickListener {
            signInGoogle()
        }
    }
    //инициализация процесса аутентификации
//        firebaseAuth = FirebaseAuth.getInstance()

//
//        googleSignInClient = GoogleSignIn.getClient(this, gso)
//

//        binding.SignIn.setOnClickListener {
//            val email = binding.login.text.toString()  //переменная для email
//            val password = binding.password1.text.toString()  //переменная для пароля
//
//            //проверка на то, что поля не пустые
//            if (email.isNotEmpty() && password.isNotEmpty()) {
//                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
//                    if (it.isSuccessful) {
//                        Log.i(TAG, "Успешный вход пользователя " + "${FirebaseAuth.getInstance().currentUser?.displayName}")
//                        val intent = Intent(this, MainActivity::class.java)
//                        startActivity(intent)
//                    }
//                    //исключение в случае неудачной регистрации
//                    else {
//                        Toast.makeText(this, getString(R.string.er_pas), Toast.LENGTH_SHORT).show()
//                    }
//                }
//            } else {
//                Toast.makeText(this, getString(R.string.not_empty), Toast.LENGTH_SHORT).show()
//            }
////            Toast.makeText(baseContext, login.text, Toast.LENGTH_SHORT).show()
//        }
//
//        binding.buttonForgot.setOnClickListener{
//            val intent = Intent(this, Forgot_Password::class.java)
//            startActivity(intent)
//        }
//
//        binding.butnotac.setOnClickListener{
//            val intent = Intent(this, RegisterActivity::class.java)
//            startActivity(intent)
//        }
//    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if(result.resultCode == Activity.RESULT_OK){
            val task = GoogleSignIn.getSignedInAccountFromIntent((result.data))
            handleResults(task)
        }
    }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful){
            val account: GoogleSignInAccount? = task.result
            if(account != null){
                updateUI(account)
            }
        }
        else{
            Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener{
            if (it.isSuccessful){
                val intent: Intent = Intent(this, MainActivity::class.java)
                intent.putExtra("email", account.email)
                intent.putExtra("name", account.displayName)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}