package ru.moogen.words;

import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    DataHelperFromCSV mDataHelperFromCSV;
    int todayPosition;
    long today;
    ViewPager mPager;
    ArrayList<Word> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDataHelperFromCSV = new DataHelperFromCSV(this);
        SQLiteDatabase sqLiteDatabase = mDataHelperFromCSV.getWritableDatabase();
        mList = mDataHelperFromCSV.getWordList(sqLiteDatabase);

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(Calendar.MILLISECOND, 0);
        gregorianCalendar.set(Calendar.HOUR_OF_DAY, 0);
        gregorianCalendar.set(Calendar.MINUTE, 0);
        gregorianCalendar.set(Calendar.SECOND, 0);


        System.out.println(gregorianCalendar.getTime().getTime());
        today = gregorianCalendar.getTime().getTime();
        System.out.println(today);
        for (int i = 0; i < mList.size(); i++) {
//            System.out.println("list date - " + mList.get(i).getDate());
            if (today == mList.get(i).getDate()){
                todayPosition = i;
                break;
            }
          }


        mPager = findViewById(R.id.pager);
        mPager.setAdapter(new MyAdapter(getSupportFragmentManager(), mList));
        mPager.setCurrentItem(todayPosition);



    }

    private void randomWord(){
        int randPosition = (int)(Math.random() * (mList.size()));
        mPager.setCurrentItem(randPosition);
    }

}
