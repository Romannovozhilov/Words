package ru.moogen.words;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class DataHelperFromCSV extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 9;

    public static final String DATABASE_NAME = "words.db";

    public static final String TABLE_WORDS_NAME = "words";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_FAVOURITE = "favourite";



    private Context mContext;

    public DataHelperFromCSV(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }








    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE ").append(TABLE_WORDS_NAME).append("(")
                .append(COLUMN_ID).append(" INTEGER PRIMARY KEY, ")
                .append(COLUMN_NAME).append(" TEXT, ")
                .append(COLUMN_DESCRIPTION).append(" TEXT, ")
                .append(COLUMN_DATE).append(" INTEGER, ")
                .append(COLUMN_FAVOURITE).append(" INTEGER);");
        db.execSQL(sql.toString());

        ArrayList<String> fromFileList = loadFromFile();
        loadToDB(db, fromFileList);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORDS_NAME);
        onCreate(db);
        }

    public static void main(String[] args) {

    }

    public ArrayList<Word> getWordList (SQLiteDatabase db){
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(Calendar.MILLISECOND, 0);
        gregorianCalendar.set(Calendar.HOUR, 0);
        gregorianCalendar.set(Calendar.MINUTE, 0);
        gregorianCalendar.set(Calendar.SECOND, 0);
        System.out.println("gregcal - " + gregorianCalendar.getTime().getTime());
        ArrayList<Word> resultList = new ArrayList<>();
        String[] sel = {COLUMN_ID, COLUMN_NAME, COLUMN_DESCRIPTION, COLUMN_DATE, COLUMN_FAVOURITE};
        Cursor cursor = db.query(TABLE_WORDS_NAME, sel, null, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){

            long date = cursor.getLong(cursor.getColumnIndex(COLUMN_DATE));
            if (date <= gregorianCalendar.getTime().getTime()) {
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                int fav = cursor.getInt(cursor.getColumnIndex(COLUMN_FAVOURITE));
                boolean favourite = fav == 1;

                Word word = new Word(id, date, name, description, favourite);
                resultList.add(word);
            }
            cursor.moveToNext();
        }
        Collections.sort(resultList);
        resultList.add(new Word(-10, -1, "Новое слово появится здесь завтра", "", false));
        return resultList;
    }

    public ArrayList<String> loadFromFile(){
        Scanner scanner = new Scanner(mContext.getResources()
                .openRawResource(R.raw.words), "utf-8");

        scanner.useDelimiter("\n");
        ArrayList<String> result = new ArrayList<>();
        scanner.next();
        while (scanner.hasNext()){
            result.add(scanner.next());
        }
        scanner.close();
        return result;
    }

    public void loadToDB(SQLiteDatabase db, ArrayList<String> fromFileList){
        System.out.println(fromFileList.size());
        StringBuilder sqlInsert = new StringBuilder();
        sqlInsert.append("INSERT INTO ").append(TABLE_WORDS_NAME).append("(")
                .append(COLUMN_ID).append(", ")
                .append(COLUMN_NAME).append(", ")
                .append(COLUMN_DESCRIPTION).append(", ")
                .append(COLUMN_DATE).append(", ")
                .append(COLUMN_FAVOURITE).append(") VALUES");
        for (int i = 0; i < fromFileList.size(); i++) {
            String[] array = fromFileList.get(i).split(";");
//            System.out.println(array.length);
//            System.out.println(Arrays.toString(array));
            sqlInsert.append("(").append(i).append(", '").append(array[0])
                    .append("', '").append(array[1]).append("', ").append(array[2]).append(", ")
            .append(0).append(")");
            if (i == fromFileList.size() -1){
                break;
            }
            sqlInsert.append(", ");
        }

        sqlInsert.append(";");
        System.out.println(sqlInsert.toString());
        db.execSQL(sqlInsert.toString());
        }

}
