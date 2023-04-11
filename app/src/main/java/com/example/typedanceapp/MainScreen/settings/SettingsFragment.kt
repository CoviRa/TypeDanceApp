package com.example.typedanceapp.MainScreen.settings

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import com.example.typedanceapp.Login_Register.LoginActivity
import com.example.typedanceapp.R
import com.example.typedanceapp.databinding.FragmentSettingsBinding
import com.google.firebase.auth.FirebaseAuth
import de.hdodenhof.circleimageview.CircleImageView
class SettingsFragment : Fragment() {

    private lateinit var circleView: CircleImageView
    private lateinit var profile: Button
    private lateinit var binding: FragmentSettingsBinding

    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fm: FragmentManager? = fragmentManager
        auth = FirebaseAuth.getInstance()
//        circleView = view.findViewById(R.id.profile_image)
        profile = view.findViewById(R.id.profile)
        profile.setOnClickListener {
            val frag = ProfileFragment()//перемещение на 1 фрагмент
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.place_frag, frag)?.commit()

//            val intent = Intent(this@SettingsFragment.requireContext(), ProfileFragment::class.java)
//            startActivity(intent)
        }
        binding.signOut.setOnClickListener {
            auth.signOut()
            Intent(this@SettingsFragment.requireContext(), LoginActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }

//        butpick = view.findViewById(R.id.but_pick_image)
//        butpick.setOnClickListener {
//            pickImageGallery()
//        }
    }

}