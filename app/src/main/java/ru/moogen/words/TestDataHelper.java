package ru.moogen.words;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class TestDataHelper implements DataHelper {
    @Override
    public ArrayList<Word> getWords() {
        ArrayList<Word> resultList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Word word = new Word(i, (new Date().getTime() - 49*86400000L) + i*86400000L
                    , "name " + i, "description description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description  description " + i, false );
            resultList.add(word);
        }
        Collections.sort(resultList);
        return resultList;
    }

//    public static void main(String[] args) {
//        TestDataHelper testDataHelper = new TestDataHelper();
//        ArrayList<Word> list = testDataHelper.getWords();
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i).toString());
//        }
//    }
}
