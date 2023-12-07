package com.example.mrpotato;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView imHat = (ImageView) findViewById(R.id.im_hat);
        final ImageView imEyebrows = (ImageView) findViewById(R.id.im_eyebrows);
        final ImageView imNose = (ImageView) findViewById(R.id.im_nose);
        final ImageView imMustache = (ImageView) findViewById(R.id.im_mustache);
        final ImageView imArms = (ImageView) findViewById(R.id.im_arms);
        final ImageView imEyes = (ImageView) findViewById(R.id.im_eyes);
        final ImageView imGlasses = (ImageView) findViewById(R.id.im_glasses);
        final ImageView imMouth = (ImageView) findViewById(R.id.im_mouth);
        final ImageView imEars = (ImageView) findViewById(R.id.im_ears);
        final ImageView imShoes = (ImageView) findViewById(R.id.im_shoes);

        final CheckBox cbHat =(CheckBox) findViewById(R.id.checkBox_Hat);
        final CheckBox cbEyebrows =(CheckBox) findViewById(R.id.checkBox_Eyebrows);
        final CheckBox cbNose =(CheckBox) findViewById(R.id.checkBox_Nose);
        final CheckBox cbMustache =(CheckBox) findViewById(R.id.checkBox_Mustache);
        final CheckBox cbArms =(CheckBox) findViewById(R.id.checkBox_Arms);
        final CheckBox cbEyes =(CheckBox) findViewById(R.id.checkBox_Eyes);
        final CheckBox cbGlasses =(CheckBox) findViewById(R.id.checkBox_Glasses);
        final CheckBox cbMouth =(CheckBox) findViewById(R.id.checkBox_Mouth);
        final CheckBox cbEars =(CheckBox) findViewById(R.id.checkBox_Ears);
        final CheckBox cbShoes =(CheckBox) findViewById(R.id.checkBox_Shoes);

        cbHat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbHat.isChecked())
                  imHat.setVisibility(View.VISIBLE);
                else
                    imHat.setVisibility(View.INVISIBLE);
            }
        });

        cbEyebrows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbEyebrows.isChecked())
                    imEyebrows.setVisibility(View.VISIBLE);
                else
                    imEyebrows.setVisibility(View.INVISIBLE);
            }
        });

        cbNose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbNose.isChecked())
                    imNose.setVisibility(View.VISIBLE);
                else
                    imNose.setVisibility(View.INVISIBLE);
            }
        });

        cbMustache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbMustache.isChecked())
                    imMustache.setVisibility(View.VISIBLE);
                else
                    imMustache.setVisibility(View.INVISIBLE);
            }
        });

        cbArms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbArms.isChecked())
                    imArms.setVisibility(View.VISIBLE);
                else
                    imArms.setVisibility(View.INVISIBLE);
            }
        });

        cbEyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbEyes.isChecked())
                    imEyes.setVisibility(View.VISIBLE);
                else
                    imEyes.setVisibility(View.INVISIBLE);
            }
        });

        cbGlasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbGlasses.isChecked())
                    imGlasses.setVisibility(View.VISIBLE);
                else
                    imGlasses.setVisibility(View.INVISIBLE);
            }
        });

        cbMouth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbMouth.isChecked())
                    imMouth.setVisibility(View.VISIBLE);
                else
                    imMouth.setVisibility(View.INVISIBLE);
            }
        });

        cbEars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbEars.isChecked())
                    imEars.setVisibility(View.VISIBLE);
                else
                    imEars.setVisibility(View.INVISIBLE);
            }
        });

        cbShoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbShoes.isChecked())
                    imShoes.setVisibility(View.VISIBLE);
                else
                    imShoes.setVisibility(View.INVISIBLE);
            }
        });
    }
}
