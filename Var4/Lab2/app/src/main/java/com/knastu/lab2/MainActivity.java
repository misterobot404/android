package com.knastu.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ListView;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Random rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rand = new Random();

        List<Student> students = getListData();

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new CustomListAdapter(this, students));
    }

    private List<Student> getListData() {
        // создаем пустой список студентов
        List<Student> list = new ArrayList<Student>();

        // получаем студентов в виде массива строк из ресурсов
        String[] names = getResources().getStringArray(R.array.name);

        Student student;
        // добавляем по одному студенту в список
        for (String name : names) {
            if (name.equals("Закусило А.")) {
                student = new Student(name, 5);
            } else student = new Student(name, rand.nextFloat()*5);
            list.add(student);
        }

        // возвращаем заполненный список студентов с фамилиями и рейтингом
        return list;
    }

    // Переназначаем рейтинг студентов
    public void onButton(View view) {
        List<Student> students = getListData();
        listView.setAdapter(new CustomListAdapter(this, students));
    }
}

