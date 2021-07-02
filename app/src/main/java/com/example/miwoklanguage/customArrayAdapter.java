package com.example.miwoklanguage;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class customArrayAdapter extends ArrayAdapter<Word>  {

    private Context mContext;
    private List<Word> words = new ArrayList<>();
    private int mColorResourceId;

    public customArrayAdapter(@NonNull Context context, List<Word> words,int mColorResourceId) {
        super(context, 0, words);
        mContext = context;
        this.words = words;
        this.mColorResourceId=mColorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem = convertView;

        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        Word word = words.get(position);


        ImageView imageView = (ImageView) listItem.findViewById(R.id.image);
        if(word.hasImage()){
            imageView.setImageResource(word.getImageId());
            imageView.setVisibility(View.VISIBLE);
        }else {
            imageView.setVisibility(View.GONE);
        }

        TextView textView = (TextView) listItem.findViewById(R.id.text_view);
        textView.setText(word.getMiwokTranslation());
        TextView textView1 = (TextView) listItem.findViewById(R.id.text_view1);
        textView1.setText(word.getDefaultTranslation());
        View textContainer = listItem.findViewById(R.id.llout);
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        // Set the background color of the text container View

        textContainer.setBackgroundColor(color);

        return listItem;
    }



}
