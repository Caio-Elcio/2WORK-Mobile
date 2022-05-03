package com.example.a2work.profile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.a2work.MainActivity
import com.example.a2work.R

class SlideActivity : AppCompatActivity() {
    var adapter: SlideViewPagerAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide)
        viewPager = findViewById(R.id.viewpager)
        adapter = SlideViewPagerAdapter(this)
        viewPager!!.adapter = adapter
        if (isOpenAlread) {
            val intent = Intent(this@SlideActivity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        } else {
            val editor = getSharedPreferences("slide", MODE_PRIVATE).edit()
            editor.putBoolean("slide", true)
            editor.commit()
        }
    }

    private val isOpenAlread: Boolean
        private get() {
            val sharedPreferences =
                getSharedPreferences("Slide", MODE_PRIVATE)
            return sharedPreferences.getBoolean("slide", false)
        }

    companion object {
        var viewPager: ViewPager? = null
    }
}