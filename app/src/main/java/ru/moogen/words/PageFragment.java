package ru.moogen.words;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PageFragment extends Fragment {

    private Word mWord;

    public Word getWord() {
        return mWord;
    }

    public void setWord(Word word) {
        mWord = word;
    }

    public static PageFragment newInstance(Word word){
        PageFragment fragment = new PageFragment();
        fragment.setWord(word);
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
        View result=inflater.inflate(R.layout.fragment_page, container, false);
        TextView pageHeader=result.findViewById(R.id.nameText);
        pageHeader.setText(mWord.getName());
        TextView pageDesc=result.findViewById(R.id.descText);
        pageDesc.setText(mWord.getDescription());
        return result;
    }

}
