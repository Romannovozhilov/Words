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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDataHelperFromCSV = new DataHelperFromCSV(this);
        SQLiteDatabase sqLiteDatabase = mDataHelperFromCSV.getWritableDatabase();
        ArrayList<Word> list = mDataHelperFromCSV.getWordList(sqLiteDatabase);

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(Calendar.MILLISECOND, 0);
        gregorianCalendar.set(Calendar.HOUR, 0);
        gregorianCalendar.set(Calendar.MINUTE, 0);
        gregorianCalendar.set(Calendar.SECOND, 0);


        System.out.println(gregorianCalendar.getTime().getTime());
        today = gregorianCalendar.getTime().getTime();
        System.out.println(today);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("list date - " + list.get(i).getDate());
            if (today == list.get(i).getDate()){
                todayPosition = i;
                break;
            }
          }


        ViewPager pager=findViewById(R.id.pager);
        pager.setAdapter(new MyAdapter(getSupportFragmentManager(), list));
        pager.setCurrentItem(todayPosition);



//        ArrayList<String> list2 = new DataHelperFromCSV(this).loadFromFile();
//        for (int i = 0; i < list2.size(); i++) {
//            System.out.println("fdfsdf    " + list2.get(i));
//        }

    }
}
