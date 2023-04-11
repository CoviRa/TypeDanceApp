package com.example.typedanceapp.MainScreen.interesting

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.typedanceapp.R
import com.example.typedanceapp.databinding.FragmentHomeBinding
import com.example.typedanceapp.databinding.FragmentInterestingBinding
import com.google.firebase.database.*
class InterestingFragment : Fragment() {
//    private lateinit var binding: FragmentInterestingBinding

//    private lateinit var adapter: NewsAdapter
    private lateinit var binding: FragmentInterestingBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var newsList: ArrayList<Container>
    private lateinit var databaseReference: DatabaseReference
//    private lateinit var newsdetail: ArrayList<NewsDetail>

//    lateinit var image: Array<Int>
//    lateinit var title: Array<String>
//    lateinit var name_one: Array<String>
//    lateinit var name_two: Array<String>
//    lateinit var name_three: Array<String>
//    lateinit var news_one: Array<String>
//    lateinit var news_two: Array<String>
//    lateinit var news_three: Array<String>
//    lateinit var news_four: Array<String>
//    lateinit var gif_one: Array<Int>
//    lateinit var gif_two: Array<Int>
//    lateinit var gif_three: Array<Int>
//    lateinit var gif_four: Array<Int>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInterestingBinding.inflate(layoutInflater)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        super.onViewCreated(v, savedInstanceState)

//        dataInitialize()
        val layoutManager =
            LinearLayoutManager(activity)//установили параметры менеджера компоновки слоев просмотра
        recyclerView = v.findViewById(R.id.recV)//сослались на recycleview
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)//проверили, что фиксированный размер верен
//        adapter = NewsAdapter(newsList, this)//создали обьект адаптера и передали спсиок в качестве аргумента
//        recyclerView.adapter = adapter
        newsList = arrayListOf() //список массивов для новостей
//        newsdetail = arrayListOf()
        getUserData()
//        adapter.setOnItemClickListener(object : Adapter.OnItemClickListener {
//            override fun onItemClick(position: Int) {
//                val intent = Intent(this@InterestingFragment.requireContext(), NewsActivity::class.java)
//                intent.putExtra("name", newsList[position].title)//ключ heading
//                intent.putExtra("image", newsList[position].image)
//                intent.putExtra("news_one", news_one[position])
//                intent.putExtra("gif_one", gif_one[position])
//                intent.putExtra("name_one", name_one[position])
//                intent.putExtra("news_two", news_two[position])
//                intent.putExtra("gif_two", gif_two[position])
//                intent.putExtra("name_two", name_two[position])
//                intent.putExtra("news_three", news_three[position])
//                intent.putExtra("gif_three", gif_three[position])
//                intent.putExtra("name_three", name_three[position])
//                intent.putExtra("news_four", news_four[position])
//                intent.putExtra("gif_four", gif_four[position])
//                startActivity(intent)
//            }
//
//            override fun onItemClick(
//                parent: AdapterView<*>?,
//                view: View?,
//                position: Int,
//                id: Long
//            ) {
//            }
//        })
//        adapter.onItemClick = {
//            val intent = Intent(this@InterestingFragment.requireContext(), NewsActivity::class.java)
//            intent.putExtra("news", it)//ключ news
//            startActivity(intent)
//        }


    }

    //класс для реализации в firebase
    private fun getUserData(){
        databaseReference = FirebaseDatabase.getInstance().getReference("newspaper")
        databaseReference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(newsSnapShot in snapshot.children){
                        val list = newsSnapShot.getValue(Container::class.java)
                        newsList.add(list!!)
                    }
//                    for(detSnapShot in snapshot.children){
//                        val list = detSnapShot.getValue(NewsDetail::class.java)
//                        newsdetail.add(list!!)
//                    }
                    val mAdapter = NewsAdapter(newsList, this@InterestingFragment)
                    recyclerView.adapter = NewsAdapter(newsList, this@InterestingFragment)
                    recyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object: NewsAdapter.OnItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@InterestingFragment.requireContext(), NewsActivity::class.java)
                            intent.putExtra("title", newsList[position].title)//ключ title
                            intent.putExtra("image", newsList[position].image)
                            intent.putExtra("news_one", newsList[position].news_one)
                            intent.putExtra("gif_one", newsList[position].gif_one)
                            intent.putExtra("name_one", newsList[position].name_one)
                            intent.putExtra("news_two", newsList[position].news_two)
                            intent.putExtra("gif_two", newsList[position].gif_two)
                            intent.putExtra("name_two", newsList[position].name_two)
                            intent.putExtra("news_three", newsList[position].news_three)
                            intent.putExtra("gif_three", newsList[position].gif_three)
                            intent.putExtra("name_three", newsList[position].name_three)
                            intent.putExtra("news_four", newsList[position].news_four)
                            intent.putExtra("gif_four", newsList[position].gif_four)
                            startActivity(intent)
                        }

                        override fun onItemClick(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                        }

                    })
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }


//        private fun dataInitialize() {//метод для инициализации данных
//
//            newsList = arrayListOf() //список массивов для новостей
//
////            newsList.add(News(R.drawable.wensday_dance, "Wensday"))
//            image = arrayOf(
//                R.drawable.wensday_dance,
//                R.drawable.dancing_christopher_walken
//            )
//            name = arrayOf(
//                getString(R.string.wensday_news),
//                getString(R.string.dancing_christopher)
//            )
//            news_one = arrayOf(
//                getString(R.string.wensday_news_text),
//                getString(R.string.christofer_text)
//            )
//            gif_one = arrayOf(
//                R.drawable.wens_dance_g,
//                R.drawable.fatboy_slim
//            )
//            name_one = arrayOf(
//                getString(R.string.rain_text),
//                getString(R.string.the_origin_of_the_dance)
//            )
//            news_two = arrayOf(
//                getString(R.string.singin_in_the_rain),
//                getString(R.string.origin_text)
//            )
//            gif_two = arrayOf(
//                R.drawable.singin_in_the_rain_dancegif,
//                R.drawable.like_dance_christopher
//            )
//            name_two = arrayOf(
//                getString(R.string.pulp_name),
//                getString(R.string.awards_name)
//            )
//            news_three = arrayOf(
//                getString(R.string.pulp_text),
//                getString(R.string.awards_text)
//            )
//            gif_three = arrayOf(
//                R.drawable.pulp_fiction,
//                R.drawable.tik_tok_mem_christopher
//            )
//            name_three = arrayOf(
//                getString(R.string.jocker_name),
//                getString(R.string.mem_name)
//            )
//            news_four = arrayOf(
//                getString(R.string.text_jocker_dance),
//                getString(R.string.mem_text)
//            )
//            gif_four = arrayOf(
//                R.drawable.jocker_dance,
//                R.drawable.daniel_craig_dance
//            )
//
//            for (i in image.indices) {//обьект для новостей всех элементов, которые присутствуют внутри этого идентификатора изображения и текста
//                val news = News(image[i], name[i])
//                newsList.add(news)
//            }
//        }
}
//        replaceFragment(NewsFragment())
//        adapter.onBindViewHolder{
//            val intent = Intent(this@InterestingFragment.requireContext(), PostActivity::class.java)
//            intent.putExtra("heading", .heading)//ключ news
//            startActivity(intent)
//        }

//    override fun clickItemPosition(news: News, position: Int) {
//        val intent = Intent(context, NewsFragment::class.java)
//        intent.putExtra("class", "Adapter")
//        ContextCompat.startActivity(requireContext(), intent,null)
//    }

//    override fun onClick(position: Int) {
//        when(position){
//            0 -> startActivity(Intent(this@InterestingFragment.requireContext(), NewsActivity::class.java))
//            1 -> startActivity(Intent(this@InterestingFragment.requireContext(), TopNewsActivity::class.java))
//        }
//    }
//    private fun replaceFragment(fragment: Fragment){
//        val fragmentTransaction = requireFragmentManager().beginTransaction()
//        fragmentTransaction.replace(R.id.place_frag, fragment)
//        fragmentTransaction.addToBackStack(null)
//        fragmentTransaction.commit()
//    }

//    private fun replaceFragment(fragment: Fragment){
//        val fragmentTransaction = requireFragmentManager().beginTransaction()
//        fragmentTransaction.replace(
//            R.id.place_frag, fragment
//        )
//        fragmentTransaction.addToBackStack(null)//возвращаемся обратно
//        fragmentTransaction.commit()
//    }