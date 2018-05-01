package ru.moogen.words;

import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    public static DataHelperFromCSV mDataHelperFromCSV;
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

        Date todayDate = new Date();


        for (int i = 0; i < mList.size(); i++) {
            Date wordDate = new Date(mList.get(i).getDate());

            GregorianCalendar todayCal = new GregorianCalendar();
            todayCal.setTime(todayDate);

            GregorianCalendar wordCal = new GregorianCalendar();
            wordCal.setTime(wordDate);


            if (wordDate.getYear() == todayDate.getYear() && wordDate.getMonth() == todayDate.getMonth()
                    && todayCal.get(Calendar.DAY_OF_MONTH) == wordCal.get(Calendar.DAY_OF_MONTH)){
                todayPosition = i;
                break;
            }
          }


        mPager = findViewById(R.id.pager);
        mPager.setAdapter(new MyAdapter(getSupportFragmentManager(), mList));
        mPager.setCurrentItem(todayPosition);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ab_menu, menu);
        return true;
    }

    public void randomWord(MenuItem m){
        int randPosition = (int)(Math.random() * (mList.size() - 1));
        mPager.setCurrentItem(randPosition);
    }


}
