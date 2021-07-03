package com.example.miwoklanguage;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FamilyMembers extends AppCompatActivity {
 MediaPlayer mp;
AudioManager am;
int focusRequest;


AudioManager.OnAudioFocusChangeListener audioFocusChangeListener=new AudioManager.OnAudioFocusChangeListener() {
    @Override
    public void onAudioFocusChange(int focusChange) {
   if(focusChange==AudioManager.AUDIOFOCUS_GAIN) && mp!=null{
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
        setContentView(R.layout.activity_family_members);
        ArrayList<Word> family=new ArrayList<>();
        am=(AudioManager)getSystemService(AUDIO_SERVICE);
        focusRequest=am.requestAudioFocus(audioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        family.add(new Word("father","әpә",R.drawable.family_father,R.raw.family_father));
        family.add(new Word("mother","әṭa",R.drawable.family_mother,R.raw.family_mother));
        family.add(new Word("son","angsi",R.drawable.family_son,R.raw.family_son));
        family.add(new Word("daughter","tune",R.drawable.family_daughter,R.raw.family_daughter));
        family.add(new Word("older brother","taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        family.add(new Word("younger brother","chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        family.add(new Word("older sister","tete",R.drawable.family_older_sister,R.raw.family_older_sister));
        family.add(new Word("younger sister","kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        family.add(new Word("grand mother","ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        family.add(new Word(" grand father","paapa",R.drawable.family_grandfather,R.raw.family_grandfather));
        customArrayAdapter newAdapter=new customArrayAdapter(this,family,R.color.category_family);
       ListView listView=(ListView)findViewById(R.id.list1);
       listView.setAdapter(newAdapter);
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               releaseMediaPlayer();
               Word word=family.get(position);
               if(focusRequest==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                   mp=MediaPlayer.create(FamilyMembers.this,word.getAudioId());
                   mp.start();
                   mp.setOnCompletionListener(mo);
               }
           }
       });
    }
}
