package com.example.a2work.profile;

import static com.example.a2work.R.layout.slide_screen;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.a2work.MainActivity;
import com.example.a2work.R;

public class SlideViewPagerAdapter extends PagerAdapter {

    Context ctx;

    public SlideViewPagerAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(slide_screen, container, false);

        ImageView logo = view.findViewById(R.id.logo);
        ImageView ind1 = view.findViewById(R.id.ind1);
        ImageView ind2 = view.findViewById(R.id.ind2);
        ImageView ind3 = view.findViewById(R.id.ind3);

        TextView desc2 = view.findViewById(R.id.tv_desc2);
        TextView texto = view.findViewById(R.id.tv_contra);
        TextView desc = view.findViewById(R.id.tv_desc);
        TextView desc3 = view.findViewById(R.id.tv_desc3);
        TextView desc4 = view.findViewById(R.id.tv_desc4);

        ImageView next = view.findViewById(R.id.proximo);
        ImageView back = view.findViewById(R.id.anterior);
        Button btnIniciar = view.findViewById(R.id.btn_contratar);

        TextView titulo = view.findViewById(R.id.titulo);
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(intent);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.example.a2work.profile.SlideActivity.viewPager.setCurrentItem(position + 1);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SlideActivity.viewPager.setCurrentItem(position - 1);
            }
        });

        switch (position) {
            case 0:
                logo.setImageResource(R.drawable.primeiro);
                ind1.setImageResource(R.drawable.selected);
                ind2.setImageResource(R.drawable.unselected);
                ind3.setImageResource(R.drawable.unselected);
                texto.setText("Plano Basic");
//                desc.setText("Contratado");
                back.setVisibility(View.GONE);
                next.setVisibility(View.VISIBLE);
                btnIniciar.setEnabled(false);
                break;

            case 1:
                logo.setImageResource(R.drawable.segundo);
                ind1.setImageResource(R.drawable.unselected);
                ind2.setImageResource(R.drawable.selected);
                ind3.setImageResource(R.drawable.unselected);

                btnIniciar.setText("Assinar");
                texto.setText("Plano Pro");
                desc.setText("1 - Recomendado para projetos independentes");
                desc2.setText("2 - Upload de até 3 projetos no mês");
                desc3.setText("3 - Visualizar todos os projetos na plataforma");
                titulo.setText("PRO");
                back.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                break;

            case 2:
                logo.setImageResource(R.drawable.terceiro);
                ind1.setImageResource(R.drawable.unselected);
                ind2.setImageResource(R.drawable.unselected);
                ind3.setImageResource(R.drawable.selected);

                desc2.setText("2 - Recomendado para empresas");
                desc3.setText("3 - Upload de 5 projetos no mês");
                texto.setText("Plano PREMIUM");
                desc.setText("1 - Economia de 130.00 (Anual)");
                desc4.setText("4 - Visualizar todos os projetos na plataforma");
                btnIniciar.setText("Assinar");
                titulo.setText("PREMIUM");
                back.setVisibility(View.VISIBLE);
                next.setVisibility(View.GONE);
                break;
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}