package ru.moogen.words;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataHelper testDataHelper = new TestDataHelper();
        ArrayList<Word> list = testDataHelper.getWords();

        ViewPager pager=findViewById(R.id.pager);
        pager.setAdapter(new MyAdapter(getSupportFragmentManager(), list));

    }
}
