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

public class Numbers extends AppCompatActivity  {
    AudioManager am;
   MediaPlayer mp;
  int focusRequest;

  AudioManager.OnAudioFocusChangeListener audioFocusChangeListener=new AudioManager.OnAudioFocusChangeListener() {
      @Override
      public void onAudioFocusChange(int focusChange) {
          if (focusChange==AudioManager.AUDIOFOCUS_GAIN && mp!=null){
              mp.start();
          }else if(focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT){
             if (mp!=null){mp.pause();
              mp.seekTo(0);}
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
        setContentView(R.layout.activity_numbers);

        ArrayList<Word> words=new ArrayList<Word>();
        am=(AudioManager)getSystemService(AUDIO_SERVICE);
        focusRequest=am.requestAudioFocus(audioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        words.add(new Word("one","lutti",R.drawable.number_one,R.raw.number_one));
        words.add(new Word("two","ottiko",R.drawable.number_two,R.raw.number_two));
        words.add(new Word("three","tolookosu",R.drawable.number_three,R.raw.number_three));
        words.add(new Word("four","oyyisa",R.drawable.number_four,R.raw.number_four));
        words.add(new Word("five","massokka",R.drawable.number_five,R.raw.number_five));
        words.add(new Word("six","temmokka",R.drawable.number_six,R.raw.number_six));
        words.add(new Word("seven","kenekaku",R.drawable.number_seven,R.raw.number_seven));
        words.add(new Word("eight","kawinta",R.drawable.number_eight,R.raw.number_eight));
        words.add(new Word("nine","wo'e",R.drawable.number_nine,R.raw.number_nine));
        words.add(new Word("ten","na'aacha",R.drawable.number_ten,R.raw.number_ten));
        customArrayAdapter newArrayAdapter=new customArrayAdapter(this,words,R.color.category_numbers);
        ListView listView=(ListView)findViewById(R.id.list);
        listView.setAdapter(newArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override

            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
              releaseMediaPlayer();
                Word word=words.get(position);

                if (focusRequest==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mp=MediaPlayer.create(Numbers.this,word.getAudioId());
                    mp.start();
                    mp.setOnCompletionListener(mo);

                }
            }
        });

    }




}
