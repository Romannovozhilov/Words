package ru.moogen.words;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MyAdapter extends FragmentPagerAdapter {

    ArrayList<Word> wordsList;

    public MyAdapter(FragmentManager mgr, ArrayList<Word> list) {
        super(mgr);
        wordsList = list;
    }
    @Override
    public int getCount() {
        return wordsList.size();
    }
    @Override
    public Fragment getItem(int position) {
        return(PageFragment.newInstance(wordsList.get(position), wordsList.size(), position));
    }
}
