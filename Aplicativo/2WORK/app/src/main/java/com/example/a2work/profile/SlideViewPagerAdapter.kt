package com.example.a2work.profile

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.a2work.MainActivity
import com.example.a2work.R
import com.example.a2work.R.layout.slide_screen

class SlideViewPagerAdapter(var ctx: Context) : PagerAdapter() {
    override fun getCount(): Int {
        return 3
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(slide_screen, container, false)
        val logo = view.findViewById<ImageView>(R.id.logo)
        val ind1 = view.findViewById<ImageView>(R.id.ind1)
        val ind2 = view.findViewById<ImageView>(R.id.ind2)
        val ind3 = view.findViewById<ImageView>(R.id.ind3)
        val desc2 = view.findViewById<TextView>(R.id.tv_desc2)
        val texto = view.findViewById<TextView>(R.id.tv_contra)
        val desc = view.findViewById<TextView>(R.id.tv_desc)
        val desc3 = view.findViewById<TextView>(R.id.tv_desc3)
        val desc4 = view.findViewById<TextView>(R.id.tv_desc4)
        val next = view.findViewById<ImageView>(R.id.proximo)
        val back = view.findViewById<ImageView>(R.id.anterior)
        val btnIniciar = view.findViewById<Button>(R.id.btn_contratar)
        val titulo = view.findViewById<TextView>(R.id.titulo)
        btnIniciar.setOnClickListener {
            val intent = Intent(ctx, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            ctx.startActivity(intent)
        }
        next.setOnClickListener {
            SlideActivity.viewPager!!.currentItem = position + 1
        }
        back.setOnClickListener {
            SlideActivity.viewPager!!.currentItem = position - 1
        }
        when (position) {
            0 -> {
                logo.setImageResource(R.drawable.primeiro)
                ind1.setImageResource(R.drawable.selected)
                ind2.setImageResource(R.drawable.unselected)
                ind3.setImageResource(R.drawable.unselected)
                texto.text = "Plano Basic"
                desc3.text = ""
                desc4.text = ""
                //                desc.setText("Contratado");
                back.visibility = View.GONE
                next.visibility = View.VISIBLE
                btnIniciar.isEnabled = false
            }
            1 -> {
                logo.setImageResource(R.drawable.primeiro)
                ind1.setImageResource(R.drawable.unselected)
                ind2.setImageResource(R.drawable.selected)
                ind3.setImageResource(R.drawable.unselected)
                btnIniciar.text = "Assinar"
                texto.text = "Plano Pro"
                titulo.text = "PRO"
                desc.text = "1 - Recomendado para projetos independentes"
                desc2.text = "2 - Upload de até 3 projetos no mês"
                desc3.text = "3 - Visualizar todos os projetos na plataforma"
                back.visibility = View.VISIBLE
                next.visibility = View.VISIBLE
            }
            2 -> {
                logo.setImageResource(R.drawable.terceiro)
                ind1.setImageResource(R.drawable.unselected)
                ind2.setImageResource(R.drawable.unselected)
                ind3.setImageResource(R.drawable.selected)
                btnIniciar.text = "Assinar"
                texto.text = "Plano Premium"
                titulo.text = "Premium"
                desc.text = "1 - O plano mais economico da plataforma!"
                desc2.text = "2 - Recomendado para empresas"
                desc3.text = "3 - Upload de 5 projetos no mês"
                desc4.text = "4 - Visualizar todos os projetos na plataforma"
                back.visibility = View.VISIBLE
                next.visibility = View.GONE
            }
        }
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}