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

public class Phrases extends AppCompatActivity {

    MediaPlayer mp;
    AudioManager am;
    int focusRequest;
    AudioManager.OnAudioFocusChangeListener audioFocusChangeListener=new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange==AudioManager.AUDIOFOCUS_GAIN){
                mp.start();
            }else if (focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT){
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
        setContentView(R.layout.activity_phrases);
    am=(AudioManager)getSystemService(AUDIO_SERVICE);
    focusRequest=am.requestAudioFocus(audioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
    ArrayList<Word> words=new ArrayList<>();

        words.add(new Word("Where are you going?","minto wuksus",R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?","tinnә oyaase'nә",R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...","oyaaset...",R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?","michәksәs?",R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I’m feeling good.","kuchi achit",R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?","әәnәs'aa?",R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I’m coming.","hәә’ әәnәm",R.raw.phrase_yes_im_coming));
        words.add(new Word("I’m coming.","әәnәm",R.raw.phrase_im_coming));
        words.add(new Word("Let’s go.","yoowutis",R.raw.phrase_lets_go));
        words.add(new Word("Come here.","әnni'nem",R.raw.phrase_come_here));

    customArrayAdapter cs=new customArrayAdapter(this,words,R.color.category_phrases);
    ListView ls=(ListView)findViewById(R.id.text4);
    ls.setAdapter(cs);
    ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
         releaseMediaPlayer();
            Word word=words.get(position);
            if(focusRequest==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                mp=MediaPlayer.create(Phrases.this,word.getAudioId());
                mp.seekTo(position);
                mp.start();
                mp.setOnCompletionListener(mo);
            }
        }
    });
    }
}