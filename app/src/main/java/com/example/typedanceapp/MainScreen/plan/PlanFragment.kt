package com.example.typedanceapp.MainScreen.plan

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.typedanceapp.MainScreen.plan.hiphop.Beg_HipActivity
import com.example.typedanceapp.MainScreen.plan.kpop.BegKpopActivity
import com.example.typedanceapp.MainScreen.plan.zumba.BegZumbaActivity
import com.example.typedanceapp.R
import com.example.typedanceapp.databinding.FragmentPlanBinding

class PlanFragment : Fragment() {

    //    private lateinit var tabLayout: TabLayout
//    private lateinit var viewPager2: ViewPager2
    private lateinit var binding: FragmentPlanBinding
//    private val tabTitles = arrayListOf("Новичок", "Любитель", "Специалист")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlanBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {

        val nextBtn1: Button = v.findViewById(R.id.dance_one_but)
        nextBtn1.setOnClickListener {
            val intent = Intent(this@PlanFragment.requireContext(), Beg_HipActivity::class.java)
            startActivity(intent)
        }
        val nextBtn2: Button = v.findViewById(R.id.dance_two_but)
        nextBtn2.setOnClickListener {
            val intent = Intent(this@PlanFragment.requireContext(), BegKpopActivity::class.java)
            startActivity(intent)
        }
        val nextBtn3: Button = v.findViewById(R.id.dance_three_but)
        nextBtn3.setOnClickListener {
            val intent = Intent(this@PlanFragment.requireContext(), BegZumbaActivity::class.java)
            startActivity(intent)
        }


//        tabLayout = v.findViewById(R.id.tabL)
//        viewPager2 = v.findViewById(R.id.viewP2)
//        binding.viewP2.adapter = PageAdapter(this)
//        TabLayoutMediator(binding.tabL, binding.viewP2) { tab, position ->
//            tab.text = tabTitles[position]
//        }.attach()
    }
}
//        tabLayout.addTab(tabLayout.newTab().setText("Новичок"))
//        tabLayout.addTab(tabLayout.newTab().setText("Любитель"))
//        tabLayout.addTab(tabLayout.newTab().setText("Профессионал"))
//        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

//        adapter = PageAdapter(this) //установили адаптер
//        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                if(tab != null){
//                    viewPager2.currentItem = tab.position
//                }
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//
//            }
//        })
//        viewPager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                tabLayout.selectTab(tabLayout.getTabAt(position))
//            }
//        })
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
////        viewModel = ViewModelProvider(this).get(PlanViewModel::class.java)
//    }