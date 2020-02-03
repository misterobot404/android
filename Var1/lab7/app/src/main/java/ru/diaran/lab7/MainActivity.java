package ru.diaran.lab7;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;



public class MainActivity extends AppCompatActivity  {

    private SharedPreferences sharedPrefs;

    private ToggleButton tgBtn1;
    private ToggleButton tgBtn2;
    private ToggleButton tgBtn3;

    private Switch swt1;
    private Switch swt2;
    private Switch swt3;
    private Switch swt4;

    public static final String myPrefs = "prefs";
    public static final String[] nameKey = {"tgBtn1pr","tgBtn2pr","tgBtn3pr","swt1pr","swt2pr","swt3pr","swt4pr"};

    private final static String FILE_NAME = "lab7.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = (EditText) findViewById(R.id.editText);
        editText.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

        tgBtn1 = (ToggleButton) findViewById(R.id.toggleButton);
        tgBtn2 = (ToggleButton) findViewById(R.id.toggleButton2);
        tgBtn3 = (ToggleButton) findViewById(R.id.toggleButton3);

        swt1 = (Switch) findViewById(R.id.switch1);
        swt2 = (Switch) findViewById(R.id.switch2);
        swt3 = (Switch) findViewById(R.id.switch3);
        swt4 = (Switch) findViewById(R.id.switch4);

        // при старте проверяем есть ли в файлах настроек
        // данные по ключу nameKey
        sharedPrefs = getSharedPreferences(myPrefs, Context.MODE_PRIVATE);
        if (sharedPrefs.contains(nameKey[0])) {
            tgBtn1.setChecked(sharedPrefs.getBoolean(nameKey[0], false));
        }
        if (sharedPrefs.contains(nameKey[1])) {
            tgBtn2.setChecked(sharedPrefs.getBoolean(nameKey[1], false));
        }
        if (sharedPrefs.contains(nameKey[2])) {
            tgBtn3.setChecked(sharedPrefs.getBoolean(nameKey[2], false));
        }
        if (sharedPrefs.contains(nameKey[3])) {
            swt1.setChecked(sharedPrefs.getBoolean(nameKey[3], false));
        }
        if (sharedPrefs.contains(nameKey[4])) {
            swt2.setChecked(sharedPrefs.getBoolean(nameKey[4], false));
        }
        if (sharedPrefs.contains(nameKey[5])) {
            swt3.setChecked(sharedPrefs.getBoolean(nameKey[5], false));
        }
        if (sharedPrefs.contains(nameKey[6])) {
            swt4.setChecked(sharedPrefs.getBoolean(nameKey[6], false));
        }

    }

    @Override
    protected void onStop() {
        // получаем текст
        tgBtn1 = (ToggleButton) findViewById(R.id.toggleButton);
        tgBtn2 = (ToggleButton) findViewById(R.id.toggleButton2);
        tgBtn3 = (ToggleButton) findViewById(R.id.toggleButton3);

        swt1 = (Switch) findViewById(R.id.switch1);
        swt2 = (Switch) findViewById(R.id.switch2);
        swt3 = (Switch) findViewById(R.id.switch3);
        swt4 = (Switch) findViewById(R.id.switch4);

        // получаем доступ к файлу
        SharedPreferences.Editor editor = sharedPrefs.edit();
        // сохраняем по текст из EditText по ключу nameKey
        editor.putBoolean(nameKey[0], tgBtn1.isChecked());
        editor.putBoolean(nameKey[1], tgBtn2.isChecked());
        editor.putBoolean(nameKey[2], tgBtn3.isChecked());
        editor.putBoolean(nameKey[3], swt1.isChecked());
        editor.putBoolean(nameKey[4], swt2.isChecked());
        editor.putBoolean(nameKey[5], swt3.isChecked());
        editor.putBoolean(nameKey[6], swt4.isChecked());
        editor.apply();
        super.onStop();

    }

//    public void saveText(View view){
//        Toast.makeText(this, "сохранен", Toast.LENGTH_SHORT).show();
//        FileOutputStream fos = null;
//        try {
//            EditText textBox = (EditText) findViewById(R.id.editText);
//            String text = textBox.getText().toString();
//            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
//
//            fos = openFileOutput(FILE_NAME, MODE_WORLD_READABLE);
//            fos.write(text.getBytes());
//            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
//        }
//        catch(IOException ex) {
//
//            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//        finally{
//            try{
//                if(fos!=null)
//                    fos.close();
//            }
//            catch(IOException ex){
//
//                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
    // открытие файла
    public void openText(View view){

        FileInputStream fin = null;
        EditText textView = (EditText) findViewById(R.id.editText);
        try {
            fin = openFileInput(FILE_NAME);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String (bytes);
            textView.setText(text);
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{

            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void saveText1(View view) {
        Toast.makeText(this, "сохранен", Toast.LENGTH_SHORT).show();
        FileOutputStream fos = null;
        try {
            EditText textBox = (EditText) findViewById(R.id.editText);
            String text = textBox.getText().toString();
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();

            fos = openFileOutput(FILE_NAME, MODE_WORLD_READABLE);
            fos.write(text.getBytes());
            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fos!=null)
                    fos.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
