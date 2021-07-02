package com.example.miwoklanguage;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Colors extends AppCompatActivity {

    int  focusRequest;
    AudioManager am;
 MediaPlayer mp;
AudioManager.OnAudioFocusChangeListener audioFocusChangeListener=new AudioManager.OnAudioFocusChangeListener() {
    @Override
    public void onAudioFocusChange(int focusChange) {
        if (focusChange==AudioManager.AUDIOFOCUS_GAIN){
            mp.start();
        }
            else if(focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT){
           if (mp!=null)mp.pause();
            mp.seekTo(0);
        }else if(focusChange==AudioManager.AUDIOFOCUS_LOSS){
               releaseMediaPlayer();
        }
    }
};
    private MediaPlayer.OnCompletionListener mo=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            Toast. makeText(getApplicationContext(),"play next",Toast. LENGTH_SHORT).show();
            releaseMediaPlayer();

        }
    };
    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }


    private void releaseMediaPlayer() {
        if(mp!=null){
            mp.release();
            mp=null;
            am.abandonAudioFocus(audioFocusChangeListener);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);
        ArrayList<Word> color=new ArrayList<>();
        am=(AudioManager)getSystemService(AUDIO_SERVICE);
        focusRequest=am.requestAudioFocus(audioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        color.add(new Word("red","wettiti",R.drawable.color_red,R.raw.color_red));
        color.add(new Word("green","chokokki",R.drawable.color_green,R.raw.color_green));
        color.add(new Word("brown","ṭakaakki",R.drawable.color_brown,R.raw.color_brown));
        color.add(new Word("gray","ṭopoppi",R.drawable.color_gray,R.raw.color_gray));
        color.add(new Word("black","kululli",R.drawable.color_black,R.raw.color_black));
        color.add(new Word("white","kelelli",R.drawable.color_white,R.raw.color_white));
        color.add(new Word("dusty yellow","ṭopiisә",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        color.add(new Word("mustard yellow","chiwiiṭә",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));
        customArrayAdapter colorAdapter=new customArrayAdapter(this,color,R.color.category_colors);
        ListView listView=(ListView)findViewById(R.id.text3);
        listView.setAdapter(colorAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();

                Word word=color.get(position);
                if(focusRequest==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mp=MediaPlayer.create(Colors.this,word.getAudioId());
                    mp.start();
                    mp.setOnCompletionListener(mo);
                }
            }
        });
    }
}