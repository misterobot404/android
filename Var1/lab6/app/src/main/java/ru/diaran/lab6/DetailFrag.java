package ru.diaran.lab6;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailFrag extends Fragment {


    String number[] = new String[] { "88005553535", "+19568265848", "8914415879", "86249876315", "55-13-57", };
    String fio[] = new String[] { "Пукин ВА", "Саламандра", "Петров Петр Петрович", "Иванов Иван Иванович", "Я не знаю кто еще", };
    String dlzh[] = new String[] { "Директор", "Мифическое животное", "Стандарт №1", "Стандарт №2", "ну как бы работает", };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentdatail, container, false);
        return view;
    }

    // обновление текстового поля
    public void setText(int item) {
        TextView numberView = (TextView) getView().findViewById(R.id.numberValue);
        numberView.setText(number[item]);
        TextView fioView = (TextView) getView().findViewById(R.id.fioValue);
        fioView.setText(fio[item]);
        TextView dlzhView = (TextView) getView().findViewById(R.id.dlzhValue);
        dlzhView.setText(dlzh[item]);

    }
}
