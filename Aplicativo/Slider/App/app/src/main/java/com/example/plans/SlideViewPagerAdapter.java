package com.example.plans;

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
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater= (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slide_screen,container,false);

        ImageView logo=view.findViewById(R.id.logo);

        ImageView ind1=view.findViewById(R.id.ind1);
        ImageView ind2=view.findViewById(R.id.ind2);
        ImageView ind3=view.findViewById(R.id.ind3);

        TextView texto=view.findViewById(R.id.tv_contra);
        TextView desc=view.findViewById(R.id.tv_desc);

        ImageView next=view.findViewById(R.id.proximo);
        ImageView back=view.findViewById(R.id.anterior);
        Button btnIniciar=view.findViewById(R.id.btn_contratar);
        btnIniciar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx,MainActivity.class);

            }
        });

        switch (position){
            case 0:
                logo.setImageResource(R.drawable.primeiro);
                ind1.setImageResource(R.drawable.selected);
                ind2.setImageResource(R.drawable.unselected);
                ind3.setImageResource(R.drawable.unselected);

                texto.setText("Contratar");
                desc.setText("Contratado");
                back.setVisibility(View.GONE);
                next.setVisibility(View.VISIBLE);
                break;

            case 1:
                logo.setImageResource(R.drawable.segundo);
                ind1.setImageResource(R.drawable.unselected);
                ind2.setImageResource(R.drawable.selected);
                ind3.setImageResource(R.drawable.unselected);

                texto.setText("Contratar");
                desc.setText("Texto aleatório");
                back.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                break;

            case 2:
                logo.setImageResource(R.drawable.terceiro);
                ind1.setImageResource(R.drawable.unselected);
                ind2.setImageResource(R.drawable.unselected);
                ind3.setImageResource(R.drawable.selected);

                texto.setText("Contratar 2 ");
                desc.setText("Texto aleatório");
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
