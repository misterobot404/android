package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.ListPopupWindow;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String[] cats = {"Барсик", "Мурзик", "Рыжик", "Васька", "Пушок"};
    static ListPopupWindow mListPopupWindow;
    Button btnOpenMenu;
    static TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        btnOpenMenu = (Button) findViewById(R.id.button);
        List<Food> foods = getListData();

        mListPopupWindow = new ListPopupWindow(this);


        mListPopupWindow.setAnchorView(btnOpenMenu);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mListPopupWindow.setWidth(metrics.widthPixels); //sets width as per the screen.
        mListPopupWindow.setHeight(metrics.heightPixels);
        mListPopupWindow.setAdapter(new CustomListAdapter(this, foods));
        mListPopupWindow.setModal(true);

        // Отображение всплывающего окна
        btnOpenMenu.setOnClickListener(v -> mListPopupWindow.show());
    }

    private List<Food> getListData() {
        // создаем пустой список
        List<Food> list = new ArrayList<Food>();

        Food food1 = new Food(R.drawable.item1, "Сет вечеринка","4500 грамм","4599 руб.");
        Food food2 = new Food(R.drawable.item2, "Сет для всех","2400 грамм","2500 руб.");
        Food food3 = new Food(R.drawable.item3, "Сет море","1200 грамм","1400 руб.");
        Food food4 = new Food(R.drawable.item4, "Сет на двоих","400 грамм","500 руб.");

        list.add(food1);
        list.add(food2);
        list.add(food3);
        list.add(food4);

        // возвращаем заполненный список студентов с фамилиями и рейтингом
        return list;
    }

    public static void updateTextViewInfo(String item) {
        textView.setText(item);
        mListPopupWindow.dismiss();
    }
}