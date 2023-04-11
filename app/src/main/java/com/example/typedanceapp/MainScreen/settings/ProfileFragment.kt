package com.example.typedanceapp.MainScreen.settings

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.typedanceapp.R
import com.example.typedanceapp.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var imageUri: Uri
    private lateinit var auth: FirebaseAuth

    companion object{
        const val REQUEST_CAMERA = - 100
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        val user: FirebaseUser? = auth.currentUser
        if(user != null){
            if(user.photoUrl != null){
                Picasso.get().load(user.photoUrl).into(binding.ivProfile)
            } else{
                Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/shorts-bb865.appspot.com/o/zivert.jpg?alt=media&token=66552cd0-755b-4700-931d-e0ef5dea6056")
            }
            binding.etNameOne.setText(user.displayName)
            binding.etEmail.setText(user.email)

            if(user.isEmailVerified){
                binding.icVerified.visibility = View.VISIBLE
            } else{
                binding.icUnverified.visibility = View.VISIBLE
            }

        }

        binding.ivProfile.setOnClickListener {
            intentCamera()
        }
        binding.btnUpdate.setOnClickListener {
            val image = when{
                ::imageUri.isInitialized -> imageUri
                user?.photoUrl == null -> Uri.parse("https://firebasestorage.googleapis.com/v0/b/shorts-bb865.appspot.com/o/zivert.jpg?alt=media&token=66552cd0-755b-4700-931d-e0ef5dea6056")
                else -> user.photoUrl
            }

            val name = binding.etNameOne.text.toString().trim()

            if(name.isEmpty()){
                binding.etNameOne.error = "Поле имя пустое"
                binding.etNameOne.requestFocus()
                return@setOnClickListener
            }

            UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .setPhotoUri(image)
                .build().also {
                    user?.updateProfile(it)?.addOnCompleteListener {
                        if(it.isSuccessful){
                            Toast.makeText(activity, "Данные сохранены", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(activity, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        }
        binding.icVerified.setOnClickListener { //придет сообщение на почту для подтверждения
            user?.sendEmailVerification()?.addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(activity, "Email подтвержден", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(activity, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun intentCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also{ intent ->
            activity?.packageManager?.let {
                intent.resolveActivity(it).also {
                    startActivityForResult(intent, REQUEST_CAMERA)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CAMERA && resultCode == Activity.RESULT_OK){
            val imgBitmap = data?.extras?.get("data") as Bitmap
            uploadImage(imgBitmap)
        }
    }

    private fun uploadImage(imgBitmap: Bitmap) {
        val baos = ByteArrayOutputStream()
        val ref: StorageReference = FirebaseStorage.getInstance().reference.child("img/${FirebaseAuth.getInstance().currentUser?.uid}")

        imgBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val image = baos.toByteArray()

        ref.putBytes(image)
            .addOnCompleteListener{
                if(it.isSuccessful){
                    ref.downloadUrl.addOnCompleteListener{
                        it.result?.let{
                            imageUri = it
                            binding.ivProfile.setImageBitmap(imgBitmap)
                        }
                    }

                }
            }
    }

}