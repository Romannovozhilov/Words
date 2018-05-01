package ru.moogen.words;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PageFragment extends Fragment {

    private Word mWord;
    public FloatingActionButton mFloatingActionButton;
    private int mSize;
    private int mPosition;

    public Word getWord() {
        return mWord;
    }

    public void setWord(Word word) {
        mWord = word;
    }

    public static PageFragment newInstance(Word word, int size, int position){
        PageFragment fragment = new PageFragment();
        fragment.setWord(word);
        fragment.mSize = size;
        fragment.mPosition = position;
        return fragment;
    }

    public PageFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        View result=inflater.inflate(R.layout.fragment_page, container, false);
        TextView pageHeader=result.findViewById(R.id.nameText);
        pageHeader.setText(mWord.getName());
        TextView pageDesc=result.findViewById(R.id.descText);
        pageDesc.setText(mWord.getDescription());
        mFloatingActionButton = result.findViewById(R.id.fab);
        if (mPosition == mSize - 1){
            mFloatingActionButton.setVisibility(View.INVISIBLE);
        } else {
            if (mWord.isFavourite()) {
                mFloatingActionButton.setImageResource(R.drawable.star_on);
            }
            mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mWord.setFavourite(!mWord.isFavourite());
                    if (mWord.isFavourite()) {
                        mFloatingActionButton.setImageResource(R.drawable.star_on);
                    } else {
                        mFloatingActionButton.setImageResource(R.drawable.star_off);
                    }
                }
            });
        }
        return result;
    }

}
