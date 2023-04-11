package com.example.typedanceapp.MainScreen.interesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.typedanceapp.R
import com.example.typedanceapp.databinding.ActivityNewsBinding
import com.squareup.picasso.Picasso

class NewsActivity : AppCompatActivity() {
    private lateinit var newsList: ArrayList<Container>

    private lateinit var binding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        Picasso.get().load(intent.getStringExtra("image"))
//        val bundle: Bundle?= intent.extras
//        val imageId = bundle!!.getInt("image")
//        image.setImageResource(imageId)

        val title: TextView = findViewById(R.id.wensd)
        val image: ImageView = findViewById(R.id.wensday_post)
        val news_one: TextView = findViewById(R.id.text_wens)
        val gif_one: ImageView = findViewById(R.id.gif_wensday)
        val name_one: TextView = findViewById(R.id.rain)
        val news_two: TextView = findViewById(R.id.text_singin)
        val gif_two: ImageView = findViewById(R.id.gif_singin)
        val name_two: TextView = findViewById(R.id.pulp)
        val news_three: TextView = findViewById(R.id.text_pulp)
        val gif_three: ImageView = findViewById(R.id.gif_pulp)
        val name_three: TextView = findViewById(R.id.jocker)
        val news_four: TextView = findViewById(R.id.text_jocker)
        val gif_four: ImageView = findViewById(R.id.gif_jocker)


//        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageview)

        title.text = intent.getStringExtra("title")
//        val images: Int = intent.extras!!.getInt("image")
//        image.setImageResource(images)
//        image.setImageResource(intent.getIntExtra("image", 0))
//        val f: String = intent.getStringExtra("gif_one")!!
        Glide.with(this).load(intent.getStringExtra("image")).into(image)
//        Glide.with(this).asGif().load(intent.getStringExtra("gif_one")).into(gif_one)
        Picasso.get().load(intent.getStringExtra("gif_one")).into(gif_one)
        Picasso.get().load(intent.getStringExtra("gif_two")).into(gif_two)
        Picasso.get().load(intent.getStringExtra("gif_three")).into(gif_three)
        Picasso.get().load(intent.getStringExtra("gif_four")).into(gif_four)
//        Glide.with(this).asGif().load(intent.getStringExtra("gif_two")).into(gif_two)
//        Glide.with(this).asGif().load(intent.getStringExtra("gif_three")).into(gif_three)
//        Glide.with(this).asGif().load(intent.getStringExtra("gif_four")).into(gif_four)
//        Picasso.get().load(intent.getStringExtra("image")).into(image)
        news_one.text = intent.getStringExtra("news_one")
//        Picasso.get().load(intent.getStringExtra("gif_one")).into(gif_one)
        name_one.text = intent.getStringExtra("name_one")
        news_two.text = intent.getStringExtra("news_two")
        name_two.text = intent.getStringExtra("name_two")
        news_three.text = intent.getStringExtra("news_three")
        name_three.text = intent.getStringExtra("name_three")
        news_four.text = intent.getStringExtra("news_four")

//        val bundle: Bundle?= intent.extras
//        val bmp: Bitmap = bundle!!.getParcelable("image")!!
//        news_two.text = intent.getStringExtra("news_two")
//        val news_one: TextView = findViewById(R.id.text_wens)
//        val gif_one: GifImageView = findViewById(R.id.gif_wensday)
//        val name_one: TextView = findViewById(R.id.rain)
//        val news_two: TextView = findViewById(R.id.text_singin)
//        val gif_two: GifImageView = findViewById(R.id.gif_singin)
//        val name_two: TextView = findViewById(R.id.pulp)
//        val news_three: TextView = findViewById(R.id.text_pulp)
//        val gif_three: GifImageView = findViewById(R.id.gif_pulp)
//        val name_three: TextView = findViewById(R.id.jocker)
//        val news_four: TextView = findViewById(R.id.text_jocker)
//        val gif_four: GifImageView = findViewById(R.id.gif_jocker)

//        val bundle: Bundle?= intent.extras
//        val heading = bundle!!.getString("title")
//        val imageId = bundle.getInt("image")
//        val news1 = bundle.getString("news_one")
//        val gif1 = bundle.getInt("gif_one")
//        val name1 = bundle.getString("name_one")
//        val news2 = bundle.getString("news_two")
//        val gif2 = bundle.getInt("gif_two")
//        val name2 = bundle.getString("name_two")
//        val news3 = bundle.getString("news_three")
//        val gif3 = bundle.getInt("gif_three")
//        val name3 = bundle.getString("name_three")
//        val news4 = bundle.getString("news_four")
//        val gif4 = bundle.getInt("gif_four")

//        title.text = heading
//        name_one.text = name1
//        name_two.text = name2
//        name_three.text = name3
//        image_one.setImageResource(imageId)
//        news_one.text = news1
//        news_two.text = news2
//        news_three.text = news3
//        news_four.text = news4
//        gif_one.setImageResource(gif1)
//        gif_two.setImageResource(gif2)
//        gif_three.setImageResource(gif3)
//        gif_four.setImageResource(gif4)
    }
}