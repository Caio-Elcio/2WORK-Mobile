package com.example.a2work

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener

class ProjectsFull : AppCompatActivity() {

    var imageArray: ArrayList<Int> = ArrayList()
    var carouselView: CarouselView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_projects_full)

        imageArray.add(R.drawable.image_1)
        imageArray.add(R.drawable.image_2)
        imageArray.add(R.drawable.image_3)
        imageArray.add(R.drawable.image_4)

        carouselView = findViewById(R.id.carouselView)

        carouselView!!.pageCount = imageArray.size

        carouselView!!.setImageListener(imageListener)

    }


    var imageListener =
        ImageListener { position, imageView -> imageView.setImageResource(imageArray[position]) }
}