package com.example.typedanceapp.MainScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.example.typedanceapp.MainScreen.home.HomeFragment
import com.example.typedanceapp.MainScreen.interesting.InterestingFragment
import com.example.typedanceapp.MainScreen.plan.PlanFragment
import com.example.typedanceapp.MainScreen.settings.SettingsFragment
import com.example.typedanceapp.R
import com.example.typedanceapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)//подгружаем в активити

        replaceFragment(PlanFragment())//показывает этот фрагмент
        binding.navView.selectedItemId = R.id.navigation_plan//выбирает его в butnavig


//        navController = Navigation.findNavController(this, R.id.navigation_plan)
//        binding.navView.setupWithNavController(navController)

        binding.navView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navigation_home -> {
                    replaceFragment(HomeFragment())
                }
                R.id.navigation_plan -> {
                    replaceFragment(PlanFragment())
                }
                R.id.navigation_interesting -> {
                    replaceFragment(InterestingFragment())
                }
                R.id.navigation_settings -> {
                    replaceFragment(SettingsFragment())
                }
            }
            true
        }



//        RecyclerView = findViewById(R.id.recView)
//        RecyclerView.setHasFixedSize(true)
//        RecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) //перевести в горизонтальный вид RecView
//
//        postList = ArrayList()
//
//        postList.add(Post(R.drawable.wensday_dance))
//        postList.add(Post(R.drawable.shuffle_dance_top))
//
//        postAdapter = PostAdapter(postList)
//        RecyclerView.adapter = postAdapter
//
//        postAdapter.onItemClick = {
//            val intent = Intent(this, PostActivity::class.java)
//            intent.putExtra("post", it)
//            startActivity(intent)
//        }
    }

    private fun replaceFragment(fragment: Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.place_frag, fragment)
        fragmentTransaction.commit()
    }
}