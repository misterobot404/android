package ru.diaran.lab5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String yourLogin;
    String yourPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        yourLogin = "";
        yourPassword = "";

    }

    public void buttonClick(final View views) {

        // Создем builder для конструирования AlertDialog'a
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Формируем View для вставки в содержимое диалога
        final View view = getLayoutInflater().inflate(R.layout.dlglogin, null);

        EditText txtLoginValue = (EditText) view.findViewById(R.id.txtLoginValue);
        txtLoginValue.setText(yourLogin);
        // Конструируем диалог
        builder.setTitle("Форма авторизации");
        builder.setCancelable(true);

        // Устанавливаем слушатель события "Нажали кнопку No"
        builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        builder.setView(view);
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                EditText edLogin = (EditText) view.findViewById(R.id.txtLoginValue);
                EditText edPassword = (EditText) view.findViewById(R.id.txtPasswordValue);
                yourLogin = edLogin.getText().toString();
                yourPassword = edPassword.getText().toString();


                setContentView(R.layout.activity_main);
                TextView goodT =(TextView) findViewById(R.id.goodText);

                goodT.setVisibility(View.VISIBLE);
                TextView textNameLogin =(TextView) findViewById(R.id.textLoginName);
                textNameLogin.setVisibility(View.VISIBLE);
                TextView textValueLogin =(TextView) findViewById(R.id.textLoginValue);
                textValueLogin.setVisibility(View.VISIBLE);
                textValueLogin.setText(yourLogin);
                Toast.makeText(view.getContext(), "Данные изменены", Toast.LENGTH_SHORT).show();

            }
        });
        // Создали объект AlertDialog, готовый к выводу на экран
        AlertDialog alert = builder.create();
        alert.show();
    }
}
