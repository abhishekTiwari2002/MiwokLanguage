package com.example.miwoklanguage;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1=(Button)findViewById(R.id.button1);
        Button b2=(Button)findViewById(R.id.button2);
        Button b3=(Button)findViewById(R.id.button3);
        Button b4=(Button)findViewById(R.id.button4);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);

    }
    

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:Intent intent=new Intent(this,Numbers.class);
                startActivity(intent);
                break;
            case R.id.button2:Intent intent2=new Intent(this,FamilyMembers.class);
                startActivity(intent2);

                break;
            case R.id.button3:Intent intent3=new Intent(this,Colors.class);
                startActivity(intent3);
                break;
            case R.id.button4:Intent intent4=new Intent(this,Phrases.class);
                startActivity(intent4);
                break;



        }
    }
}